package clases;

public class GeoLocation implements api.GeoLocation {
    private double x;
    private double y;
    private double z;

    public GeoLocation(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public double distance(api.GeoLocation g) {
        double d1 = Math.pow((this.x - ((GeoLocation)g).x()),2);
        double d2 = Math.pow((this.y - ((GeoLocation)g).y()),2);
        double d3 = Math.pow((this.z - ((GeoLocation)g).z()),2);
        double D = Math.sqrt(d1 + d2 + d3);
        return D;
    }
}
