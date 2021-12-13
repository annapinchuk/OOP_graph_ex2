package clases;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import org.apiguardian.api.API;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 *
 * @author
 */
public class Graph_Algo implements DirectedWeightedGraphAlgorithms {
    public DirectedWeightedGraph DGraph;
    public boolean Ok = true;

    public Graph_Algo() {
        this.DGraph = new DGraph();
    }

    /**
     * init
     *
     * @param graph - the graph for the algorithms.
     */
    public Graph_Algo(DirectedWeightedGraph graph) {
        this.DGraph = graph;
    }


    /**
     * save to file
     *
     * @param file - the file name that will be save in
     * @return
     */
    @Override
    public boolean save(String file) {
        JSONObject j = new JSONObject();
        JSONArray N = new JSONArray();
        JSONArray E = new JSONArray();
        Iterator i = this.DGraph.nodeIter();
        while (i.hasNext()) {
            NodeData n = (NodeData) i.next();
            JSONObject o = new JSONObject();
            o.put("pos", n.getLocation().toString());
            o.put("id", n.getKey());
            N.add(o);
        }
        Iterator i1 = this.DGraph.edgeIter();
        while (i1.hasNext()) {
            Edata n1 = (Edata) i1.next();
            JSONObject o1 = new JSONObject();
            o1.put("src", n1.getSrc());
            o1.put("w", n1.getWeight());
            o1.put("dest", n1.getDest());
            E.add(o1);
        }
        j.put("Edges", E);
        j.put("Nodes", N);
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(j.toJSONString());
            fw.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean load(String file) {

        try {
            DGraph dg = new DGraph();
            JSONObject J = (JSONObject) new JSONParser().parse(new FileReader(file));
            JSONArray N = (JSONArray) J.get("Nodes"), E = (JSONArray) J.get("Edges");
            for (Object o : N.toArray()) {
                JSONObject ver = (JSONObject) o;
                String l = (String) ver.get("pos");
                int key = (int) (long) ver.get("id");
                Point3D p = new Point3D(l);
                NodeData nd = new NodeData(p, key, 1);
                dg.addNode(nd);
            }
            for (Object o : E.toArray()) {
                JSONObject edg = (JSONObject) o;
                int src = (int) (long) edg.get("src"), dest = (int) (long) edg.get("dest");
                double w = (double) edg.get("w");
                dg.connect(src, dest, w);
            }
            this.DGraph = dg;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Collection<NodeData> getV() {
        Collection<NodeData> c = new ArrayList<>();
        ArrayList<NodeData> a = new ArrayList<>();
        Iterator<api.NodeData> it = DGraph.nodeIter();

        while (it.hasNext())
            a.add((NodeData) it.next());
        c.addAll(a);


        return c;
    }


    public Collection<EdgeData> getE(int node_id) {

        Collection<EdgeData> c = new ArrayList<>();
        ArrayList<EdgeData> a = new ArrayList<>();
        Iterator<EdgeData> ite1 = DGraph.edgeIter(node_id);
        if (!ite1.hasNext())
            return null;
        ArrayList<NodeData> e = (ArrayList<NodeData>) getV();

        if (!e.contains(DGraph.getNode(node_id)))
            return null;


        while (ite1.hasNext())
            a.add(ite1.next());
        // if (!a.contains(DGraph.getEdge(DGraph.getNode(node_id).))) {
        //     return null;
        //  }
        c.addAll(a);

        return c;
    }

    /**
     * Returns true if and only if (iff) there is a valid path from EVREY node to each
     * other node. NOTE: assume directional graph - a valid path (a-->b) does NOT imply a valid path (b-->a).
     *
     * @return true if the graph is connected and not if not
     */
    @Override
    public boolean isConnected() {
        resetTag();
        if (DGraph.nodeSize() == 0 || DGraph.nodeSize() == 1) return true;
        Collection<NodeData> temp = getV();
        Iterator<NodeData> nodeIter = temp.iterator();
        while (nodeIter.hasNext()) {
            nodeIter.next().setTag(-1);
        }
        boolean FLAG = true;
        Queue<NodeData> myQue = new LinkedList<>();
        Queue<NodeData> finish = new LinkedList<>();
        NodeData current;
        nodeIter = getV().iterator();
        if (nodeIter.hasNext()) {
            current = (NodeData) nodeIter.next();
        } else {
            System.out.println("BUG");
            return false;
        }
        boolean second = false;
        int k = 0;
        for (; FLAG; k++) {
            while (FLAG) {
                FLAG = false;
                if (getE(current.getKey()) == null) return false;
                List<EdgeData> list = new LinkedList<>(getE(current.getKey()));
                if (list.isEmpty()) return false;
                for (EdgeData i : list) {
                    if (DGraph.getNode(i.getDest()).getTag() != k) {
                        DGraph.getNode(i.getDest()).setTag(k);
                        myQue.add((NodeData) DGraph.getNode(i.getDest()));
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
                NodeData tempNode = (NodeData) nodeIter.next();
                if (tempNode.getTag() != k) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * returns the length of the shortest path between src to dest
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return Distance of the src to dest in double
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        return this.dijkstra(this.DGraph.getNode(src)).get(dest);
        /*resetTag();
        if (DGraph.getNode(src) == null || DGraph.getNode(dest) == null)
            return Integer.MIN_VALUE;

        Queue<Edata> PQdist = new LinkedList<Edata>();
        Queue<Edata> PQnode = new LinkedList<Edata>();
        HashMap<Integer, Double> dist = new HashMap<Integer, Double>(DGraph.nodeSize());
        NodeData Runner = (NodeData) DGraph.getNode(src);
        Edata CurNode = null;

        dist.put(src, (double) 0);
        int i = 0;

        while (i <= DGraph.nodeSize() || !PQnode.isEmpty()) {
            Collection<EdgeData> Col = new ArrayList<EdgeData>(getE(Runner.getKey()));
            Collection<EdgeData> Col2 = new ArrayList<EdgeData>(getE(Runner.getKey()));
            AddEdgesToPriorityQueue(PQdist, Col);
            AddNodesToPriorityQueue(PQnode, Col2);
            GetNewDist(PQdist, dist);
            CurNode = (Edata) PQnode.poll();
            if (CurNode != null)
                Runner = (NodeData) DGraph.getNode(CurNode.getDest());
            i++;
        }

        return dist.get(dest).doubleValue();*/
    }


    /**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return the path of the way in list of node_data
     */
    @Override
    public List<api.NodeData> shortestPath(int src, int dest) {
        dijkstra(this.getGraph().getNode(src));
        List<api.NodeData> path = new ArrayList<>();
        int cur = dest;
        while (cur != src && dijhelper.get(cur) != null){
            path.add(this.getGraph().getNode(cur));
            cur = dijhelper.get(cur);
        }
        path.add(this.getGraph().getNode(src));
        Collections.reverse(path);
        return path;
    /*resetTag();
        if (DGraph.getNode(src) == null || DGraph.getNode(dest) == null)
            return null;
        Queue<Edata> PQdist = new LinkedList<Edata>();
        Queue<Edata> PQnode = new LinkedList<Edata>();
        HashMap<Integer, Double> dist = new HashMap<Integer, Double>(DGraph.nodeSize());
        HashMap<Integer, ArrayList<Integer>> Paths = new HashMap<Integer, ArrayList<Integer>>(DGraph.nodeSize());
        Edata CurNode = null;
        NodeData Runner = (NodeData) DGraph.getNode(src);

        dist.put(src, (double) 0);
        Paths.put(src, new ArrayList<Integer>());
        Paths.get(src).add(src);
        int i = 0;

        while (i <= DGraph.nodeSize() || !PQnode.isEmpty()) {
            Collection<EdgeData> Col = new ArrayList<EdgeData>(getE(Runner.getKey()));
            Collection<EdgeData> Col2 = new ArrayList<EdgeData>(getE(Runner.getKey()));
            AddEdgesToPriorityQueue(PQdist, Col);
            AddNodesToPriorityQueue(PQnode, Col2);
            GetNewDist(PQdist, dist, Paths);
            CurNode = (Edata) PQnode.poll();
            if (CurNode != null)
                Runner = (NodeData) DGraph.getNode(CurNode.getDest());
            i++;
        }
        List<api.NodeData> Ans = new LinkedList<api.NodeData>();
        int j = 0;
        while (j < Paths.get(dest).size()) {
            Ans.add((NodeData) DGraph.getNode(Paths.get(dest).get(j)));
            j++;
        }
        return Ans;*/
    }

    @Override
    public api.NodeData center() {
        // saving the max vertex distance between all the vertexes
        HashMap<Integer, Double> maxv = new HashMap<>();
        Iterator<api.NodeData> i = this.DGraph.nodeIter();
        while (i.hasNext()) {
            NodeData v = (NodeData) i.next();
            HashMap<Integer, Double> distance = dijkstra(v);
            double max_d = Double.MIN_VALUE;
            for (int key : distance.keySet()) {
                double d = distance.get(key);
                if (d > max_d) {
                    max_d = d;

                }
            }
            maxv.put(v.getKey(), max_d);
        }
        double minv = Double.MAX_VALUE;
        int minkey = Integer.MIN_VALUE;
        for (int key : maxv.keySet()) {
            if (minv > maxv.get(key)) {
                minv = maxv.get(key);
                minkey = key;
            }
        }
        return this.DGraph.getNode(minkey);
    }

    /**
     * computes a relatively short path which visit each node in the targets List.
     * Note: this is NOT the classical traveling salesman problem,
     * as you can visit a node more than once, and there is no need to return to source node -
     * just a simple path going over all nodes in the list.
     *
     * @param cities
     * @return the path of the way in list of node_data
     */
        @Override
     public List<api.NodeData> tsp(List<api.NodeData> cities){
        if(cities==null)
        {
            return null;
        }

        List<api.NodeData> priority =new ArrayList<>();
        List<Integer> NotHandeled = new ArrayList<>();
        for (api.NodeData v:cities)
        {
            NotHandeled.add(v.getKey());
        }
        List<api.NodeData> shortestpath ;

        NodeData cur = (NodeData) cities.get(0);

        priority.add((NodeData) this.DGraph.getNode(NotHandeled.get(0)));

        NotHandeled.remove(0);///////handeled now
        while (!NotHandeled.isEmpty())//////we didnt finish
        {
            double shortestDist = Double.POSITIVE_INFINITY;
            int idShort = Integer.MIN_VALUE;
            int location = Integer.MIN_VALUE;
            for(int i = 0 ; i < NotHandeled.size();i++)
            {
                int key =NotHandeled.get(i);//////now we work on this node[key]
                Double temp=shortestPathDist(cur.getKey(),key);
                if(temp < shortestDist)
                {
                    shortestDist = temp;
                    idShort = key;
                    location = i;
                }
            }
            shortestpath = shortestPath(cur.getKey(),idShort);
            shortestpath.remove(0);

            while (!shortestpath.isEmpty())
            {
                priority.add((NodeData) shortestpath.get(0));
                shortestpath.remove(0);
            }
            int Node_id = NotHandeled.get(location);
            cur = (NodeData) this.DGraph.getNode(Node_id);
            NotHandeled.remove(NotHandeled.get(location));
        }
        if(priority.size()==1)
        {
            return null;
        }
        else
        {
            return priority;
        }
    }
//    @Override
//    public List<api.NodeData> tsp(List<api.NodeData> cities) {
//        resetTag();
//        if (cities == null || cities.isEmpty()) return null;
//        int i = 0;
//        List<api.NodeData> ans = new LinkedList<api.NodeData>();
//
//        HashMap<api.NodeData, Boolean> hashMap = new LinkedHashMap<>();
//        while (i < cities.size()) {
//            if (!hashMap.containsKey(cities.get(i))) {
//                hashMap.put(cities.get(i), true);
//                i++;
//            } else {
//                cities.remove(i);
//            }
//        }
//        i = 0;
//        NodeData temp = null;
//        NodeData temp2;
//        List<api.NodeData> tempN;
//        if (!cities.isEmpty()) {
//            temp = (NodeData) cities.remove(0);
//        }
//        while (!cities.isEmpty()) {
//            temp2 = (NodeData) cities.remove(0);
//            tempN = shortestPath(temp.getKey(), temp2.getKey());
//            if (tempN == null) return null;
//            for (api.NodeData nk : tempN) {
//                if (cities.contains(nk)) {
//                    cities.remove(nk);
//                }
//            }
//            ans.addAll(tempN);
//            temp = temp2;
//        }
//
//        i = 0;
//        while (i < ans.size() - 1) {
//            if (ans.get(i).equals(ans.get(i + 1)))
//                ans.remove(i);
//            else
//                i++;
//        }
//        //System.out.println( );
//        //for(node_data n:ans){
//        //System.out.print(" "+(n.getKey()+1));
//        //}
//        return ans;
//    }

    @Override
    public void init(DirectedWeightedGraph g) {
        this.DGraph = g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.DGraph;
    }

    /**
     * Compute a deep copy of this graph.
     *
     * @return
     */
    @Override
    public DGraph copy() {
        return new DGraph(DGraph);//
    }

    private void resetTag() {
        if (getV() != null) {
            for (api.NodeData n : getV()) {
                n.setTag(0);
            }
        }
    }

    private void GetNewDist(Queue<Edata> PQdist, HashMap<Integer, Double> dist) {
        while (!PQdist.isEmpty()) {
            Edata CurNode = PQdist.poll();
            if (!dist.containsKey(CurNode.getDest()) || dist.get(CurNode.getDest()).doubleValue() > dist.get(CurNode.getSrc()).doubleValue() + CurNode.getWeight()) {
                dist.put(CurNode.getDest(), dist.get(CurNode.getSrc()) + CurNode.getWeight());
            }
        }
    }

    private void GetNewDist(Queue<Edata> PQdist, HashMap<Integer, Double> dist, HashMap<Integer, ArrayList<Integer>> Paths) {
        while (!PQdist.isEmpty()) {
            Edata CurNode = PQdist.poll();
            if (!dist.containsKey(CurNode.getDest()) || dist.get(CurNode.getDest()).doubleValue() > dist.get(CurNode.getSrc()).doubleValue() + CurNode.getWeight()) {
                Paths.put(CurNode.getDest(), new ArrayList<Integer>());
                dist.put(CurNode.getDest(), dist.get(CurNode.getSrc()) + CurNode.getWeight());
                int k = 0;
                while (k < Paths.get(CurNode.getSrc()).size()) {
                    Paths.get(CurNode.getDest()).add(Paths.get(CurNode.getSrc()).get(k));
                    k++;
                }
                Paths.get(CurNode.getDest()).add(CurNode.getDest());
            }
        }
    }

    private void AddEdgesToPriorityQueue(Queue<Edata> Pqueue, Collection<EdgeData> Col) {
        PriorityQueue<EdgeData> minHeap = new PriorityQueue<EdgeData>(new Comparator<EdgeData>() {
            @Override
            public int compare(EdgeData o1, EdgeData o2) {
                return -Double.compare(o2.getWeight(), o1.getWeight());
            }
        });
        Object[] temp = Col.toArray();
        int i = 0;
        while (i < temp.length) {
            minHeap.add((EdgeData) temp[i]);
            i++;
        }
        while (minHeap.iterator().hasNext()) {
            Pqueue.add((Edata) minHeap.poll());
        }
    }

    private void AddNodesToPriorityQueue(Queue<Edata> Pqueue, Collection<EdgeData> Col) {
        PriorityQueue<EdgeData> minHeap = new PriorityQueue<EdgeData>(new Comparator<EdgeData>() {
            @Override
            public int compare(EdgeData o1, EdgeData o2) {
                return -Double.compare(o2.getWeight(), o1.getWeight());
            }
        });
        int i = 0;
        Object[] temp = Col.toArray();
        while (i < temp.length) {
            Edata cur = (Edata) temp[i];
            if (DGraph.getNode(cur.getDest()).getTag() != 2) {
                minHeap.add((EdgeData) temp[i]);
            }
            i++;
        }
        i = 0;
        while (i < minHeap.size()) {
            if (!Pqueue.contains((Edata) minHeap.peek()) && !minHeap.isEmpty())
                Pqueue.add((Edata) minHeap.poll());
            if (Pqueue.contains((Edata) minHeap.peek()))
                minHeap.poll();
        }
        i = 0;
        while (i < Col.size()) {
            Edata cur = (Edata) temp[i];
            this.DGraph.getNode(cur.getDest()).setTag(2);
            i++;
        }
        Col = null;
    }
    private HashMap<Integer, Integer> dijhelper;
    public HashMap<Integer, Double> dijkstra(api.NodeData src) {
        dijhelper = new HashMap<>();
        HashMap<Integer, Double> distance = new HashMap<Integer, Double>();
        Iterator<api.NodeData> it = this.getGraph().nodeIter();
        while (it.hasNext()) {
            NodeData n = (NodeData) it.next();
            distance.put(n.getKey(), Double.MAX_VALUE);
        }
        PriorityQueue<NodeData> pq = new PriorityQueue<NodeData>(this.DGraph.nodeSize());
        pq.add(new NodeData(new Point3D(0, 0, 0), src.getKey(), 0));
        distance.put(src.getKey(), 0.0);
        dijhelper.put(src.getKey(), -1);
        HashSet<Integer> settled = new HashSet<Integer>();
        while (settled.size() != this.DGraph.nodeSize()) {
            if (pq.isEmpty())
                return distance;
            int u = pq.remove().getKey();
            if (settled.contains(u))
                continue;
            settled.add(u);
            double edgeDistance = -1;
            double newDistance = -1;

            Iterator<EdgeData> eIt = this.DGraph.edgeIter(u);
            while (eIt.hasNext()) {
                EdgeData e = eIt.next();
                NodeData v = (NodeData) this.DGraph.getNode(e.getDest());

                // If current node hasn't already been processed
                if (!settled.contains(v.getKey())) {
                    edgeDistance = e.getWeight();
                    newDistance = distance.get(u) + edgeDistance;

                    // If new distance is cheaper in cost
                    if (newDistance < distance.get(v.getKey())) {
                        distance.put(v.getKey(), newDistance);
                        dijhelper.put(v.getKey(),u);
                    }

                    // Add the current node to the queue
                    pq.add(new NodeData(new Point3D(0, 0, 0), v.getKey(), distance.get(v.getKey())));
                }
            }
        }
        return distance;
    }

    private void calculateMinimumDistance(NodeData evaluationNode, double edgeWeigh, NodeData sourceNode) {
        double sourceDistance = sourceNode.getWeightTag();
        if (sourceDistance + edgeWeigh < evaluationNode.getWeightTag()) {
            evaluationNode.setWeightTag(sourceDistance + edgeWeigh);
        }
    }

    private NodeData getLowestDistanceNode(Set<NodeData> unsettledNodes) {
        NodeData lowestDistanceNode = null;
        double lowestDistance = Integer.MAX_VALUE;
        for (NodeData node : unsettledNodes) {
            double nodeDistance = node.getWeightTag();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

}

