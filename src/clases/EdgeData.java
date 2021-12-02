package clases;

public class EdgeData implements api.EdgeData {
    private int Src;
    private int Dest;
    private double Wight;
    private String Info = "";
    private int Tag = 0;

    public EdgeData(int Src, int Dest, double Wight){
        this.Src = Src;
        this.Dest = Dest;
        this.Wight = Wight;
    }
    @Override
    public int getSrc() {
        return this.Src;
    }

    @Override
    public int getDest() {
        return this.Dest;
    }

    @Override
    public double getWeight() {
        return this.Wight;
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
