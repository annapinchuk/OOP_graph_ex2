package clases;

import api.DirectedWeightedGraph;
import api.NodeData;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;


public class DirectedWeightedGraphAlgorithms implements api.DirectedWeightedGraphAlgorithms {
    private DirectedWeightedGraph graph;

    public static void main(String[] args) {
        clases.DirectedWeightedGraph g = new clases.DirectedWeightedGraph();

        clases.NodeData n0 = new clases.NodeData(0, new clases.GeoLocation(0, 0, 0), 4);
        clases.NodeData n1 = new clases.NodeData(1, new clases.GeoLocation(0, 0, 0), 4);
        clases.NodeData n2 = new clases.NodeData(2, new clases.GeoLocation(0, 0, 0), 4);
        clases.NodeData n3 = new clases.NodeData(3, new clases.GeoLocation(0, 0, 0), 4);
        clases.NodeData n4 = new clases.NodeData(4, new clases.GeoLocation(0, 0, 0), 4);

        g.addNode(n0);
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);

        g.connect(0, 1, 1);
        g.connect(0, 3, 1);
        g.connect(0, 4, 1);
        g.connect(2, 3, 1);

        clases.DirectedWeightedGraphAlgorithms a = new DirectedWeightedGraphAlgorithms();
        a.init(g);
        g.tostring();

       // HashMap<Integer, Double> distances = a.Dijkstra(0);
    }

    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        clases.DirectedWeightedGraph g = new clases.DirectedWeightedGraph((clases.DirectedWeightedGraph)this.graph);
        return null;
    }

    @Override
    public boolean isConnected() {
        // DFS/BFS
       /* resetTag();
        if (this.graph.nodeSize() == 0|| this.graph.nodeSize()==1) return true;
        while (this.graph.nodeIter().hasNext()) {
            this.graph.nodeIter().next().setTag(-1);
        }
        boolean FLAG = true;
        Queue<clases.NodeData> myQue = new LinkedList<>();
        Queue<clases.NodeData> finish = new LinkedList<>();
        clases.NodeData current;
        if (this.graph.nodeIter().hasNext()) {
            current = (clases.NodeData)this.graph.nodeIter().next();
        } else {
            System.out.println("BUG");
            return false;
        }
        boolean second = false;
        int k = 0;
        for (; FLAG; k++) {
            while (FLAG) {
                FLAG = false;
                if (myGraph.getE(current.getKey()) == null) return false;
                List<edge_data> list = new LinkedList<>(myGraph.getE(current.getKey()));
                if (list.isEmpty()) return false;
                for (edge_data i : list) {
                    if (myGraph.getNode(i.getDest()).getTag() != k) {
                        myGraph.getNode(i.getDest()).setTag(k);
                        myQue.add(myGraph.getNode(i.getDest()));
                    } else if (!second) {
                        finish.add(current);
                    }
                }
                if (!myQue.isEmpty()) {
                    current = myQue.poll();
                    FLAG = true;
                }
            }
            if (!finish.isEmpty()) {
                current = finish.poll();
                second = true;
                FLAG = true;
            }
            nodeIter = temp.iterator();
            while (nodeIter.hasNext()) {
                node_data tempNode = nodeIter.next();
                if (tempNode.getTag() != k) {
                    return false;
                }
            }
        }*/
        return true;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    private void BFS(int key) {
        Queue<clases.NodeData> q = new LinkedList<clases.NodeData>();
        clases.NodeData t = (clases.NodeData)this.graph.getNode(key);
        q.add(t);
        t.setTag(1);
        while (!q.isEmpty()) {
            t = q.poll();
            api.EdgeData e = (clases.EdgeData) this.graph.edgeIter();
            while (this.graph.edgeIter().hasNext()) {
                EdgeData ed = (clases.EdgeData)this.graph.edgeIter().next();
                if (this.graph.getNode(ed.getDest()).getTag() != 1) {//check if  visited
                    this.graph.getNode(ed.getDest()).setTag(1);//set as visited
                    q.add((clases.NodeData)this.graph.getNode(ed.getDest()));
                }
            }
        }
    }
    private HashMap<Integer, Double> Dijkstra(int src) {
        HashMap<Integer, Double> distance = new HashMap<>();
        Iterator<NodeData> it = this.graph.nodeIter();
        while (it.hasNext()) {
            NodeData n = it.next();
            distance.put(n.getKey(), Double.MAX_VALUE);
        }
        PriorityQueue<clases.NodeData> q = new PriorityQueue<clases.NodeData>(this.graph.nodeSize());
        q.add(new clases.NodeData(src, new clases.GeoLocation(0,0,0), 0.0));
        distance.put(src, 0.0);
        HashSet<Integer> visited = new HashSet<Integer>();

        while (visited.size() != this.graph.nodeSize()) {
            if (q.isEmpty())
                return distance;
            int u = q.remove().getKey();
            if (visited.contains(u))
                continue;
            visited.add(u);
            double edgeDistance = -1;
            double newDistance = -1;

            Iterator<api.EdgeData> eIt = this.graph.edgeIter(u);
            while (eIt.hasNext()) {
                api.EdgeData e = eIt.next();
                NodeData v = this.graph.getNode(e.getDest());

                // If current node hasn't already been processed
                if (!visited.contains(v.getKey())) {
                    edgeDistance = v.getWeight();
                    newDistance = distance.get(u) + edgeDistance;

                    // If new distance is cheaper in cost
                    if (newDistance < distance.get(v.getKey()))
                        distance.put(v.getKey(), newDistance);

                    // Add the current node to the queue
                    q.add(new clases.NodeData(v.getKey(), new GeoLocation(0,0,0), distance.get(v.getKey())));
                }
            }
        }
        return distance;
    }
    private void resetTag(){
            while (this.graph.nodeIter().hasNext()){
                NodeData n  = (clases.NodeData) this.graph.nodeIter().next();
                this.graph.getNode(n.getKey()).setTag(0);
            }
    }
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        clases.NodeData s = (clases.NodeData) this.graph.getNode(src);
        clases.NodeData d = (clases.NodeData) this.graph.getNode(dest);
//        HashSet<clases.NodeData> p = Dijkstra(s, d);//hash map with the parent of each node in this search
//        if (p == null) {//if the map does not contain dest's key there is no path between theos nodes
//            return null;
//        }
//        NodeData th = d;
//        NodeData t = th.getNode();
//        LinkedList<NodeData> path = new LinkedList<>();
//
//        path.add(t);
//        while (t != this.g.getNode(src) && t != null) {
//
//            t = p.get(th.getKey()).getNode();
//            path.addFirst(t);//add the nodes to the list in the correct order
//            th = p.get(th.getKey());
//        }
//
//        if (t == null)
//            return null;
//        return path;
        return null;
    }


    @Override
    public NodeData center() {
        if(this.isConnected()){
            return null;
        }
        else {
            return null;
        }
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {

        return null;
    }

    @Override
    public boolean save(String file) {
        try {
            FileOutputStream f = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(f);
            objectOutputStream.writeObject(this.graph);
            objectOutputStream.close();
            f.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean load(String file) {
        return false;
    }
}
