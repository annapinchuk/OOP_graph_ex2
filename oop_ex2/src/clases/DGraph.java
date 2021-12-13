package clases;

import api.DirectedWeightedGraph;
import api.EdgeData;
import java.util.*;
import java.util.function.Consumer;

public class DGraph implements DirectedWeightedGraph {
    private HashMap<Integer, api.NodeData> hashnode = new HashMap<>();
    private HashMap<Integer, HashMap<Integer, EdgeData>> hashedge = new HashMap<>();
    private int EdgeZise = 0;
    private int myMc = 0;
    //Graph_GUI gui;

    /**
     * default contractor
     */
    ///// public DGraph(){
    //     gui = new Graph_GUI(this);
    //  }

    /**
     * copy contractor
     */
    public DGraph(DirectedWeightedGraph g) {

        Collection<api.NodeData> tempV = ((DGraph) g).getV();

        for (api.NodeData NodeData : tempV) {
            NodeData nodeData = new NodeData(NodeData);
            hashnode.put(nodeData.getKey(), nodeData);
            Collection<EdgeData> tempE = ((DGraph) g).getE(NodeData.getKey());
            if (tempE != null) {
                for (api.EdgeData EdgeData : tempE) {
                    Edata edata = new Edata(EdgeData);
                    if (!hashedge.containsKey(edata.getSrc())) {
                        HashMap<Integer, EdgeData> tempHASH = new HashMap<>();
                        tempHASH.put(edata.getDest(), edata);
                        hashedge.put(edata.getSrc(), tempHASH);
                    } else {
                        hashedge.get(edata.getSrc()).put(edata.getDest(), edata);
                    }
                }
            } else {
                HashMap<Integer, EdgeData> tempHASH = new HashMap<>();
                hashedge.put(nodeData.getKey(), tempHASH);

            }
        }
        EdgeZise = g.edgeSize();
        //gui = new Graph_GUI(this);

    }

    public DGraph() {
    }

    /**
     * @param key - the node_id.
     * @return node in node_data.
     */
    @Override
    public api.NodeData getNode(int key) {
        if (hashnode.containsKey(key))
            return hashnode.get(key);
        return null;
    }

    /**
     * @param src  - the source of the edge.
     * @param dest - the destination of the edge.
     * @return edge from src to dest in edge_data.
     */
    @Override
    public EdgeData getEdge(int src, int dest) {
        if (hashedge.containsKey(src))
            if (hashedge.get(src).containsKey(dest))
                return hashedge.get(src).get(dest);
        return null;
    }

    /**
     * add node to the graph
     *
     * @param n
     */
    @Override
    public void addNode(api.NodeData n) {
        hashnode.put(n.getKey(), n);
        myMc++;
    }
    /**
     * Connect an edge with weight w between node src to node dest.
     *
     * @param src  - the source of the edge.
     * @param dest - the destination of the edge.
     * @param w    - positive weight representing the cost (aka time, price, etc) between src-->dest.
     */
    @Override
    public void connect(int src, int dest, double w) {
        if (!(hashnode.containsKey(dest) && hashnode.containsKey(src)))
            throw new RuntimeException("not Exist");
        EdgeData edata = new Edata(src, dest, w);
        if (hashedge.containsKey(src)) {
            hashedge.get(src).put(dest, edata);
        } else {
            HashMap<Integer, EdgeData> temp = new HashMap<>();
            temp.put(dest, edata);
            hashedge.put(src, temp);
        }
        EdgeZise++;
        myMc++;

    }

    @Override
    public Iterator<api.NodeData> nodeIter() {
        return new Iterator<api.NodeData>() {
            Iterator<api.NodeData> i = hashnode.values().iterator();
            int MC = myMc;

            @Override
            public boolean hasNext() {
                if (MC != myMc) {
                    throw new RuntimeException();
                }
                return i.hasNext();
            }

            @Override
            public api.NodeData next() {
                if (MC != myMc) {
                    throw new RuntimeException();
                }
                return i.next();
            }
        };
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return new Iterator<EdgeData>() {
            Iterator<HashMap<Integer, EdgeData>> i = hashedge.values().iterator();
            Iterator<EdgeData> tmp = null;
            int MC = myMc;

            @Override
            public void remove() {
                Iterator.super.remove();
            }

            @Override
            public boolean hasNext() {
                if (MC != myMc) {
                    throw new RuntimeException();
                }
                if (tmp == null) {
                    return i.hasNext();
                }
                // if i || tmp has next , return true
                return i.hasNext() || tmp.hasNext();
            }

            @Override
            public EdgeData next() {
                if (MC != myMc) {
                    throw new RuntimeException();
                }
                if (tmp == null || !tmp.hasNext()) {
                    tmp = i.next().values().iterator();
                }
                return tmp.next();
            }

            @Override
            public void forEachRemaining(Consumer<? super EdgeData> action) {
                if (MC != myMc) {
                    throw new RuntimeException();
                }
                Iterator.super.forEachRemaining(action);
            }
        };
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return new Iterator<EdgeData>() {
            Iterator<EdgeData> i = hashedge.containsKey(node_id) ? hashedge.get(node_id).values().iterator() : null;
            int MC = myMc;

            @Override
            public boolean hasNext() {
                if (MC != myMc) {
                    throw new RuntimeException();
                }
                return i!=null && i.hasNext();
            }

            @Override
            public EdgeData next() {
                if (MC != myMc) {
                    throw new RuntimeException();
                }
                return i.next();
            }
        };
    }

    /**
     * @return all nodes in collection
     */

    public Collection<api.NodeData> getV() {
        Collection<api.NodeData> c = this.hashnode.values();
        return c;
    }

    /**
     * @param node_id
     * @return All the edge that come from this node (out) in collection
     */

    public Collection<EdgeData> getE(int node_id) {
        if (!hashnode.containsKey(node_id)) {
            return null;
        }
        return hashedge.get(node_id).values();
    }

    /**
     * remove node in the graph and all the edge that associate to this node
     *
     * @param key
     * @return the node that removed
     */
    @Override
    public api.NodeData removeNode(int key) {
        if (hashnode.containsKey(key)) {
            myMc++;
            for (int i = 0; i < hashedge.size(); i++) {
                if (hashedge.containsKey(i)) {
                    removeEdge(i, key);
                }
            }
            hashedge.remove(key);
            return hashnode.remove(key);
        } else return null;
    }

    /**
     * @param src  - src of this edge
     * @param dest - dest of this node
     * @return edge that removed
     */
    @Override
    public EdgeData removeEdge(int src, int dest) {
        if (!(hashnode.containsKey(src) && hashnode.containsKey(dest))) {
            return null;
        }
        myMc++;
        if (hashedge.containsKey(src)) {
            EdgeData temp = hashedge.get(src).remove(dest);

            if (temp != null) {
                EdgeZise--;
            }
            return temp;
        }
        return null;
    }

    /**
     * @return amount of the node in the graph
     */
    @Override
    public int nodeSize() {
        return hashnode.size();
    }

    /**
     * @return amount of the edge in the graph
     */
    @Override
    public int edgeSize() {
        return EdgeZise;
    }

    /**
     * the mode change every time when the graph changed.
     *
     * @return Mode Count
     */
    @Override
    public int getMC() {
        return myMc;
    }
}