public class Tiles {
    public int getTileX(double lon, int zoom){
        int result = (int) Math.floor(((lon+180)/360)*Math.pow(2,zoom));
        return result;
    }
    public int getTileY(double lat,int zoom){
        int result = (int)Math.floor( (1 - Math.log(Math.tan(Math.toRadians(lat)) + 1 / Math.cos(Math.toRadians(lat))) / Math.PI) / 2 * (1<<zoom) );
        return result;
    }
    public double path(double x1, double y1, double x2, double y2) {
        double R = 6371*10^3;
        double fi1 = x1 * Math.PI/180;
        double fi2 = x2 * Math.PI/180;
        double deltaFi = (x2-x1) * Math.PI/180;
        double deltaLambda = (y2-y1) * Math.PI/180;

        double a = Math.sin(deltaFi/2) * Math.sin(deltaFi/2) + Math.cos(fi1) * Math.cos(fi2) *
                Math.sin(deltaLambda/2) * Math.sin(deltaLambda/2);
        double c = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
        double d = R*c;
        return d;
        //return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
    public static void main(String[] args) {
        Tiles tiles = new Tiles();
        /*System.out.println(tiles.getTileX(16.1938,15));
        System.out.println(tiles.getTileX(16.1906,15));
        System.out.println(tiles.getTileX(16.1829,15));*/
        System.out.println(tiles.path(54.2051716, 16.1764199,54.193113, 16.1669997));
        System.out.println(tiles.path(54.2051716, 16.1764199,54.2049102, 16.1586666));
        //109
    }
}
