/**
 * This class represents a 3D point in space, with several methods
 * for 2D including Point-Line test.
 */

package clases;


import api.GeoLocation;

public class Point3D implements GeoLocation{
        private double x;
        private double y;
        private double z;

    public Point3D(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Point3D(String s){
        this.x =Double.parseDouble(s.split(",")[0]);
        this.y = Double.parseDouble(s.split(",")[1]);
        this.z = Double.parseDouble(s.split(",")[2]);
    }
    public Point3D(GeoLocation p){
        this.x=p.x();
        this.y=p.y();
        this.z=p.z();
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
    public double distance(GeoLocation g) {
        double d1 = Math.pow((this.x - ((GeoLocation)g).x()),2);
        double d2 = Math.pow((this.y - ((GeoLocation)g).y()),2);
        double d3 = Math.pow((this.z - ((GeoLocation)g).z()),2);
        double D = Math.sqrt(d1 + d2 + d3);
        return D;
    }

    @Override
    public String toString() {
        return x+","+y+","+z;
    }
}

