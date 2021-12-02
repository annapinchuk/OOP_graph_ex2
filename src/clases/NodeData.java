package clases;

public class NodeData implements api.NodeData {
    private int Key;
    private GeoLocation Location;
    private double Weight;
    private String Info = "";
    private int Tag = 0;

    // לבדוק אם באמת מקבלים ID ואם לא לעשות מספור סטטי ולתת עצמאית KEY
    public void NodeData (int key, GeoLocation Location, double Weight){
        this.Key = Key;
        this.Location = new GeoLocation(Location.x(),Location.y(),Location.z());
        this.Weight = Weight;
    }
    @Override
    public int getKey() {
        return this.Key;
    }

    @Override
    public api.GeoLocation getLocation() {
        return this.Location;
    }
    @Override
    public void setLocation(api.GeoLocation p) {
        this.Location = (GeoLocation)p;

    }

    @Override
    public double getWeight() {
        return this.Weight;
    }

    @Override
    public void setWeight(double w) {
        this.Weight = w;
    }

    @Override
    public String getInfo() {
        return this.Info;
    }

    @Override
    public void setInfo(String s) {
        this.Info = s;
    }

    @Override
    public int getTag() {
        return this.Tag;
    }

    @Override
    public void setTag(int t) {
        this.Tag = t;
    }
}
