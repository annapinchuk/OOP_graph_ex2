package clases;

import api.DirectedWeightedGraph;
import api.NodeData;

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
        HashMap<Integer, Double> distances = new HashMap<>();
        HashMap<Integer, LinkedList<api.NodeData>> shortestPaths = new HashMap<>();
        a.Dijkstra(distances, shortestPaths);

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
        return false;
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
    private void Dijkstra(HashMap<Integer, Double> distances, HashMap<Integer, LinkedList<api.NodeData>> shortestPaths, clases.NodeData src) {

        distances.put(src.getKey(), 0.0);

        HashSet<clases.NodeData> settledNodes = new HashSet<>();
        HashSet<clases.NodeData> unsettledNodes = new HashSet<>();

        unsettledNodes.add(src);

        while (unsettledNodes.size() != 0) {

            clases.NodeData currentNode = null;
            double lowestDistance = Double.MAX_VALUE;
            for (clases.NodeData node : unsettledNodes) {
                double nodeDistance = distances.get(node.getKey());
                if (nodeDistance < lowestDistance) {
                    lowestDistance = nodeDistance;
                    currentNode = node;
                }
            }

            unsettledNodes.remove(currentNode);
            Iterator<api.EdgeData> it = this.graph.edgeIter(currentNode.getKey());
            while (it.hasNext()) {
                api.EdgeData e = it.next();
                System.out.println(e.getDest());
                api.NodeData adjacentNode = this.graph.getNode(e.getDest());
                double edgeWeight = adjacentNode.getWeight();
                if (!settledNodes.contains(adjacentNode)) {
                    double sourceDistance = distances.get(currentNode.getKey());
                    if (sourceDistance + edgeWeight < distances.get(adjacentNode.getKey())) {
                        distances.put(adjacentNode.getKey(), sourceDistance + edgeWeight);
                        LinkedList<api.NodeData> shortestPath = new LinkedList(shortestPaths.get(currentNode.getKey()));
                        shortestPath.add(currentNode);
                        shortestPaths.put(adjacentNode.getKey(), shortestPath);
                    }
                    unsettledNodes.add((clases.NodeData) adjacentNode);
                }

            }
            settledNodes.add(currentNode);
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
//        JSONParser parser = new JSONParser();
//        try {
//            Object obj = parser.parse(new FileReader("//cdn.crunchify.com/Users/Shared/crunchify.json"));
//
//            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
//            JSONObject jsonObject = (JSONObject) obj;
//
//            // A JSON array. JSONObject supports java.util.List interface.
//            JSONArray companyList = (JSONArray) jsonObject.get("Company List");
//
//            // An iterator over a collection. Iterator takes the place of Enumeration in the Java Collections Framework.
//            // Iterators differ from enumerations in two ways:
//            // 1. Iterators allow the caller to remove elements from the underlying collection during the iteration with well-defined semantics.
//            // 2. Method names have been improved.
//            Iterator<JSONObject> iterator = companyList.iterator();
//            while (iterator.hasNext()) {
//                System.out.println(iterator.next());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        return false;
    }

    @Override
    public boolean load(String file) {
        return false;
    }
}
