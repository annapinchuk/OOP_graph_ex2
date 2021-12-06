package clases;

import api.EdgeData;
import api.NodeData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class DirectedWeightedGraph implements api.DirectedWeightedGraph {
    private HashMap<Integer, NodeData> hashnode;
    private HashMap<String, EdgeData> hashedge;
    private int MC = 0;

    public DirectedWeightedGraph (){
        this.hashnode = new HashMap<Integer, NodeData>();
        this.hashedge = new HashMap<String, EdgeData>();
    }
    public DirectedWeightedGraph (DirectedWeightedGraph g){
        this.hashnode = new HashMap<Integer, NodeData>();
        this.hashedge = new HashMap<String, EdgeData>();
        this.MC = g.MC;
        while (g.edgeIter().hasNext()){
            EdgeData e = (clases.EdgeData) g.edgeIter().next();
            this.connect(e.getSrc(),e.getDest(),e.getWeight());
        }
        while (g.nodeIter().hasNext()){
            NodeData n  = (clases.NodeData) g.nodeIter().next();
            this.addNode(n);
        }
    }

    @Override
    public NodeData getNode(int key) {
        return this.hashnode.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        String key = "" + src + "," + dest;
        return this.hashedge.get(key);
    }

    @Override
    public void addNode(NodeData n) {
        hashnode.put(n.getKey(), n);
        MC++;
    }

    @Override
    public void connect(int src, int dest, double w) {
        clases.EdgeData e = new clases.EdgeData(src, dest, w);
        if(this.getNode(src) != null && this.getNode(dest) != null) {
            this.hashedge.put("" + src + "," + dest, e);
            MC++;
        }
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        Iterator<NodeData> i = this.hashnode.values().iterator();
        return i;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        Iterator<EdgeData> i = this.hashedge.values().iterator();
        return i;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        ArrayList<EdgeData> a = new ArrayList<EdgeData>();
        for (String key : this.hashedge.keySet()) {
            String src = key.split(",")[0];
            if (Integer.parseInt(src) == node_id) {
                a.add(this.hashedge.get(key));
            }
        }
        return a.iterator();
    }

    @Override
    public NodeData removeNode(int key) {
        NodeData ans=this.hashnode.get(key);
        for (String k:this.hashedge.keySet())
        {
            String src = k.split(",")[0];
            String dest = k.split(",")[1];
            if (Integer.parseInt(src) == key||Integer.parseInt(dest) == key) {
              this.hashedge.remove(k);
            }
        }
        this.hashnode.remove(key);
        MC++;
        return ans;
    }

    @Override
    public EdgeData removeEdge(int src, int dest)
    {
        String key=""+src+","+dest;
        EdgeData ans=this.hashedge.get(key);
        this.hashedge.remove(key);
        MC++;
        return ans;
    }

    @Override
    public int nodeSize() {
        return this.hashnode.size();
    }

    @Override
    public int edgeSize() {
        return this.hashedge.size();
    }

    @Override
    public int getMC() {
        return this.MC;
    }
}
