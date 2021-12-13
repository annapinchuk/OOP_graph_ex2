package clases;


import api.GeoLocation;

import java.io.Serializable;
import java.util.*;

public class NodeData implements api.NodeData, Comparable<clases.NodeData> {
    private int id;
    private Point3D nodeLoaction = null;
    private double nodeWeight = 0;
    private String nodeInfo = "";
    private int nodeTag = -1;
    private double weightTag;
    private HashMap<Integer, Edata> EMap = new LinkedHashMap<>();

    public NodeData(Point3D location, int id, double nodeWeight) {
        this.id = id;
        this.nodeWeight = nodeWeight;
        nodeLoaction = location;
    }

    /**
     * Copy constructor
     *
     * @param n
     */
    public NodeData(api.NodeData n) {
        id = n.getKey();
        nodeLoaction = new Point3D(n.getLocation().x(), n.getLocation().y(), n.getLocation().z());
        nodeWeight = n.getWeight();
        nodeInfo = n.getInfo();
        nodeTag = n.getTag();
    }

    /**
     * @return the key of the node
     */
    @Override
    public int getKey() {
        return id;
    }

    /**
     * @return the location of the node
     */
    @Override
    public Point3D getLocation() {
        return nodeLoaction;
    }

    @Override
    /**
     * @param p - new new location  (position) of this node.
     */
    public void setLocation(GeoLocation p) {
        nodeLoaction = new Point3D(p);
    }

    /**
     * @return the weight of this node
     */
    @Override
    public double getWeight() {
        return nodeWeight;
    }

    /**
     * Set weight to this node
     *
     * @param w - the new weight
     */
    @Override
    public void setWeight(double w) {
        nodeWeight = w;
    }

    /**
     * @return info of the node
     */
    @Override
    public String getInfo() {
        return nodeInfo;
    }

    /**
     * Set info for the node
     *
     * @param s
     */
    @Override
    public void setInfo(String s) {
        nodeInfo = s;
    }

    /**
     * @return tag in int of this node
     */
    @Override
    public int getTag() {
        return nodeTag;
    }

    /**
     * @param t - the new value of the tag
     */
    @Override
    public void setTag(int t) {
        nodeTag = t;
    }

    @Override
    public int compereTo(clases.NodeData n) {
        return Double.compare(this.getWeight(), n.getWeight());
    }

    /**
     * @return return max of location x of all the nodes
     */
    public double getWeightTag() {
        return weightTag;
    }

    public void setWeightTag(double w) {
        this.weightTag = w;
    }

    @Override
    public int compareTo(NodeData o) {
        return Double.compare(this.getWeight(), o.getWeight());
    }

    @Override
    public String toString() {
        return "NodeData{" +
                "id=" + id +
                ", nodeLoaction=" + nodeLoaction +
                ", nodeWeight=" + nodeWeight +
                ", nodeInfo='" + nodeInfo + '\'' +
                ", nodeTag=" + nodeTag +
                ", weightTag=" + weightTag +
                ", EMap=" + EMap +
                '}';
    }
}
