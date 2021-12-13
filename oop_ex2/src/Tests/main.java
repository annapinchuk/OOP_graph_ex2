package Tests;

import api.EdgeData;
import clases.*;

import java.security.Timestamp;
import java.sql.Time;
import java.util.Iterator;

public class main {
    public static void main(String[] args) {
        DGraph dGraph = new DGraph();
        NodeData A;
        NodeData B;
        NodeData c;
        NodeData d;
        NodeData e;
        NodeData f;
        /*A = new NodeData(new Point3D(-1, 1, 1), 1, 1);
        B = new NodeData(new Point3D(-5, 6, 2), 2, 1);
        c = new NodeData(new Point3D(-4, 3, 2), 3, 1);
        d = new NodeData(new Point3D(-6, 7, 3), 4, 1);
        e = new NodeData(new Point3D(-32, 54, 44), 5, 1);
        f = new NodeData(new Point3D(23, 65, 54), 6, 1);
        dGraph.addNode(A);
        dGraph.addNode(B);
        dGraph.addNode(c);
        dGraph.addNode(d);
        dGraph.addNode(e);
        dGraph.addNode(f);
        dGraph.connect(A.getKey(), B.getKey(), 10);
        dGraph.connect(B.getKey(), d.getKey(), 12);
        dGraph.connect(B.getKey(), f.getKey(), 15);
        dGraph.connect(f.getKey(), e.getKey(), 5);
        dGraph.connect(d.getKey(), f.getKey(), 1);
        dGraph.connect(d.getKey(), e.getKey(), 2);
        dGraph.connect(A.getKey(), c.getKey(), 15);
        dGraph.connect(c.getKey(), e.getKey(), 10);*/
        Graph_Algo algorithms = new Graph_Algo();
       // algorithms.load("C:\\Users\\אנהפינצוק\\Desktop\\oop_ex2\\oop_ex2\\data\\100n (1).json");
        long timer1= java.util.Calendar.getInstance().getTime().getTime();
        algorithms.load("C:\\Users\\אנהפינצוק\\Desktop\\oop_ex2\\oop_ex2\\data\\10000Nodes.json");
        long timer2= java.util.Calendar.getInstance().getTime().getTime();
        System.out.println("took:"+(timer2-timer1));
        System.out.println(algorithms.DGraph.edgeSize() + ","+ algorithms.DGraph.nodeSize());
        Iterator<api.NodeData> gg = algorithms.getGraph().nodeIter();
        Iterator<EdgeData> g1 = algorithms.getGraph().edgeIter();
        while(gg.hasNext()){
            NodeData n = (NodeData) gg.next();
//            System.out.println(algorithms.getGraph().getNode(n.getKey()).toString());
        }
      /*  g1.forEachRemaining((n) -> {
            System.out.println(n.getDest());
        });*/
//        System.out.println(algorithms);
        while(g1.hasNext()){
            EdgeData n = g1.next();
//            System.out.println(n.getSrc()+ ","+ n.getDest());
        }


        //System.out.println(  algorithms.dijkstra(A));

    }
}
