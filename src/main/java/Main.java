
import com.sun.net.httpserver.BasicAuthenticator;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Main {
    private static final String OVERPASS_API = "http://www.overpass-api.de/api/interpreter";
    private static final String OPENSTREETMAP_API_06 = "https://openstreetmap.org/api/0.6/";
    private static final String TILEURL = "https://tiles.wmflabs.org/osm-no-labels/";
    public static OSMNode getNode(String nodeId) throws IOException, ParserConfigurationException, SAXException {
        String string = "http://www.openstreetmap.org/api/0.6/node/" + nodeId;
        URL osm = new URL(string);
        HttpURLConnection connection = (HttpURLConnection) osm.openConnection();

        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
        Document document = docBuilder.parse(connection.getInputStream());
        List<OSMNode> nodes = getNodes(document);
        if (!nodes.isEmpty()) {
            return nodes.iterator().next();
        }
        return null;
    }
    private InputStream inputStream;
    @SuppressWarnings("nls")
    private static Document getXML(double lon, double lat, double vicinityRange) throws IOException, SAXException,
            ParserConfigurationException, TransformerConfigurationException, TransformerException {

        DecimalFormat format = new DecimalFormat("##0.0000000", DecimalFormatSymbols.getInstance(Locale.ENGLISH)); //$NON-NLS-1$
        String left = format.format(lat - vicinityRange);
        String bottom = format.format(lon - vicinityRange);
        String right = format.format(lat + vicinityRange);
        String top = format.format(lon + vicinityRange);

        String string = OPENSTREETMAP_API_06 + "map?bbox=" + left + "," + bottom + "," + right + ","
                + top;
        URL osm = new URL(string);
        HttpURLConnection connection = (HttpURLConnection) osm.openConnection();

        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("./filetest.xml"));

        transformer.transform(source, result);
        return docBuilder.parse(connection.getInputStream());
    }

    public static Document getXMLFile(String location) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
        return docBuilder.parse(location);
    }

    /**
     *
     * @param xmlDocument
     * @return a list of openseamap nodes extracted from xml
     */
    @SuppressWarnings("nls")
    public static List<OSMNode> getNodes(Document xmlDocument) {
        List<OSMNode> osmNodes = new ArrayList<OSMNode>();

        // Document xml = getXML(8.32, 49.001);
        Node osmRoot = xmlDocument.getFirstChild();
        NodeList osmXMLNodes = osmRoot.getChildNodes();
        for (int i = 1; i < osmXMLNodes.getLength(); i++) {
            Node item = osmXMLNodes.item(i);
            if (item.getNodeName().equals("node")) {
                NamedNodeMap attributes = item.getAttributes();
                NodeList tagXMLNodes = item.getChildNodes();
                Map<String, String> tags = new HashMap<String, String>();
                for (int j = 1; j < tagXMLNodes.getLength(); j++) {
                    Node tagItem = tagXMLNodes.item(j);
                    NamedNodeMap tagAttributes = tagItem.getAttributes();
                    if (tagAttributes != null) {
                        tags.put(tagAttributes.getNamedItem("k").getNodeValue(), tagAttributes.getNamedItem("v")
                                .getNodeValue());
                    }
                }
                Node namedItemID = attributes.getNamedItem("id");
                Node namedItemLat = attributes.getNamedItem("lat");
                Node namedItemLon = attributes.getNamedItem("lon");
                Node namedItemVersion = attributes.getNamedItem("version");

                String id = namedItemID.getNodeValue();
                String latitude = namedItemLat.getNodeValue();
                String longitude = namedItemLon.getNodeValue();
                String version = "0";
                if (namedItemVersion != null) {
                    version = namedItemVersion.getNodeValue();
                }

                osmNodes.add(new OSMNode(id, latitude, longitude, version, tags));
            }

        }
        return osmNodes;
    }

    public static List<OSMNode> getOSMNodesInVicinity(double lat, double lon, double vicinityRange) throws IOException,
            SAXException, ParserConfigurationException,TransformerException,TransformerConfigurationException {
        return Main.getNodes(getXML(lon, lat, vicinityRange));
    }

    /**
     *
     * @param query the overpass query
     * @return the nodes in the formulated query
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public static Document getNodesViaOverpass(String query) throws IOException, ParserConfigurationException, SAXException {
        String hostname = OVERPASS_API;
        String queryString = readFileAsString(query);

        URL osm = new URL(hostname);
        HttpURLConnection connection = (HttpURLConnection) osm.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        DataOutputStream printout = new DataOutputStream(connection.getOutputStream());
        printout.writeBytes("data=" + URLEncoder.encode(queryString, "utf-8"));
        printout.flush();
        printout.close();

        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
        return docBuilder.parse(connection.getInputStream());
    }

    /**
     *
     * @param filePath
     * @return
     * @throws java.io.IOException
     */
    private static String readFileAsString(String filePath) throws java.io.IOException {
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString();
    }

    private int getTileX(double lon,int zoom){
        int result = (int) Math.floor(((lon+180)/360)*Math.pow(2,zoom));
        return result;
    }
    private int getTileY(double lat,int zoom){
        int result = (int)Math.floor( (1 - Math.log(Math.tan(Math.toRadians(lat)) + 1 / Math.cos(Math.toRadians(lat))) / Math.PI) / 2 * (1<<zoom) );
        return result;
    }
    private String getTile(int z,int x,int y){
        String url = TILEURL+z+"/"+x+"/"+y+".png";
        return url;
    }
    private void downloadTile(Main main)throws MalformedURLException,IOException{
        URL url = new URL(main.getTile(16,main.getTileX(16.19062,16),main.getTileY(54.19918,16)));
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent","App: MyGameTest; ver:0.1; email: Mastero995@wp.pl");
        inputStream = connection.getInputStream();
        ImageIO.read(inputStream);
    }
    private void getImage(BufferedImage image){

    }
    /**
     * main method that simply reads some nodes
     *
     * @param args
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException, TransformerConfigurationException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        //Authenticator.setDefault(new BasicAuthenticator("youruser", "yourpassword"));
        /*List<OSMNode> osmNodesInVicinity = getOSMNodesInVicinity(49, 8.3, 0.005);
        for (OSMNode osmNode : osmNodesInVicinity) {
            System.out.println(osmNode.getId() + ":" + osmNode.getLat() + ":" + osmNode.getLon());
        }
        */
       /* Main main = new Main();
        System.out.println(main.getTile(16,main.getTileX(16.19064,16),main.getTileY(54.19918,16)));
        int a = 1;
        int b = 1;
        if(b-a >0){
            System.out.println("dunno");
        }else{
            System.out.println("dunno 2");
        }*/
        //po connection dodaj: con.setRequestProperty("User-Agent","MyGame; ver:0.1; email: Mastero995@wp.pl")
        Test2 test2 = new Test2();
        test2.test();
        System.out.println("test2: "+test2.test3.getInteger());
        Test4 test4 = new Test4(test2.getClass());
        System.out.println("test4: "+test4.getA());

    }

}
