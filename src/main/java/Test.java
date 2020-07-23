import de.westnordost.osmapi.OsmConnection;
import de.westnordost.osmapi.map.MapDataDao;
import de.westnordost.osmapi.map.data.BoundingBox;
import de.westnordost.osmapi.map.data.Node;
import de.westnordost.osmapi.map.data.Relation;
import de.westnordost.osmapi.map.data.Way;
import de.westnordost.osmapi.map.handler.MapDataHandler;

public class Test {
    OsmConnection osmConnection = new OsmConnection("https://api.openstreetmap.org/api/0.6/",
            "User-Agent; App: MyGame; ver:0.1; email: Mastero995@wp.pl", null);
    MapDataDao dataDao = new MapDataDao(osmConnection);

    public static void main(String[] args) {
        Test test = new Test();
        BoundingBox boundingBox = new BoundingBox(14.50,50.13,14.503,50.134);
        test.dataDao.getMap(boundingBox, new MapDataHandler() {
            @Override
            public void handle(BoundingBox boundingBox) {
                System.out.println("boundingbox: "+boundingBox.getAsLeftBottomRightTopString());
            }

            @Override
            public void handle(Node node) {
                System.out.println("Node: "+node);
            }

            @Override
            public void handle(Way way) {
                System.out.println("Way: "+way);
            }

            @Override
            public void handle(Relation relation) {
                System.out.println("Relation: "+relation);
            }
        });
    }
}
