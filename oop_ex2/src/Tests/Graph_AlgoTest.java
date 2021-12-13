package Tests;
import clases.DGraph;
import clases.Graph_Algo;
import clases.NodeData;
import clases.Point3D;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Graph_AlgoTest {
    static DGraph dGraph;
    Graph_Algo algorithms = new Graph_Algo(dGraph);
    static NodeData n1;
    static NodeData  n2;
    static NodeData  n3;
    static NodeData  n4;

    static NodeData n5;
    static NodeData  n6;
    static NodeData  n7;
    static NodeData  n8;

    static NodeData n9;

    @BeforeEach
    void setUp() {
        dGraph = new DGraph();

        n1 = new NodeData(new Point3D(-1,1,1),1,1);
        n2 = new NodeData(new Point3D(-5,6,2),2,1);
        n3 = new NodeData(new Point3D(-4,3,2),3,1);
        n4 = new NodeData(new Point3D(-6,7,3),4,1);

        n5 = new NodeData(new Point3D(-32,54,44),5,1);
        n6 = new NodeData(new Point3D(23,65,54),6,1);
        n7 = new NodeData(new Point3D(-19,33,45),7,1);
        n8 = new NodeData(new Point3D(-45,72,55),8,1);

        n9 = new NodeData(new Point3D(-12,85,32),9,1);



        dGraph.addNode(n1);
        dGraph.addNode(n2);
        dGraph.addNode(n3);
        dGraph.addNode(n4);

        dGraph.addNode(n5);
        dGraph.addNode(n6);
        dGraph.addNode(n7);
        dGraph.addNode(n8);
        dGraph.addNode(n9);

        dGraph.connect(n1.getKey(), n2.getKey(),1);
        dGraph.connect(n1.getKey(), n3.getKey(),4);
        dGraph.connect(n1.getKey(), n4.getKey(),9);
        dGraph.connect(n2.getKey(), n1.getKey(),1);
        dGraph.connect(n2.getKey(), n4.getKey(),2);
        dGraph.connect(n3.getKey(), n2.getKey(),1);
        dGraph.connect(n3.getKey(), n4.getKey(),3);
        dGraph.connect(n4.getKey(), n3.getKey(),10);

        dGraph.connect(n5.getKey(), n6.getKey(),1);
        dGraph.connect(n5.getKey(), n7.getKey(),2);
        dGraph.connect(n5.getKey(), n8.getKey(),3);
        dGraph.connect(n6.getKey(), n5.getKey(),1);
        dGraph.connect(n6.getKey(), n8.getKey(),5);
        dGraph.connect(n7.getKey(), n6.getKey(),6);
        dGraph.connect(n7.getKey(), n8.getKey(),7);
        dGraph.connect(n8.getKey(), n7.getKey(),9);

        dGraph.connect(n1.getKey(),n9.getKey(),10);
        dGraph.connect(n9.getKey(),n1.getKey(),1);
        dGraph.connect(n5.getKey(),n9.getKey(),1);
        dGraph.connect(n9.getKey(),n5.getKey(),1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void init() {
        algorithms=new Graph_Algo(dGraph);
    }


    @Test
    void isConnected() {
        init();
        assertTrue(algorithms.isConnected());
        dGraph.removeNode(n9.getKey());
        init();
        dGraph.addNode(n9);
        dGraph.connect(n1.getKey(),n9.getKey(),10);
        dGraph.connect(n9.getKey(),n1.getKey(),1);
        assertFalse(algorithms.isConnected());

    }

    @Test
    void shortestPathDist() {
        init();
        assertEquals(2,algorithms.shortestPathDist(n9.getKey(),n2.getKey()));
        assertEquals(4,algorithms.shortestPathDist(n9.getKey(),n4.getKey()));
        assertEquals(12,algorithms.shortestPathDist(n1.getKey(),n6.getKey()));
    }

    @Test
    void shortestPath() {
        init();
        List<api.NodeData> l1 = algorithms.shortestPath(n9.getKey(),n2.getKey());
        List<api.NodeData> l2 = algorithms.shortestPath(n9.getKey(),n4.getKey());
        List<api.NodeData> l3 = algorithms.shortestPath(n1.getKey(),n6.getKey());
        List<Integer> l1int = new LinkedList<>();
        List<Integer> l2int = new LinkedList<>();
        List<Integer> l3int = new LinkedList<>();

        for (api.NodeData node:l1){
            l1int.add(node.getKey());
        }
        for (api.NodeData node:l2){
            l2int.add(node.getKey());
        }
        for (api.NodeData node:l3){
            l3int.add(node.getKey());
        }
        List<Integer> l1result = new LinkedList<>();
        List<Integer> l2result = new LinkedList<>();
        List<Integer> l3result = new LinkedList<>();

        l1result.add(8);
        l1result.add(0);
        l1result.add(1);

        l2result.add(8);
        l2result.add(0);
        l2result.add(1);
        l2result.add(3);

        l3result.add(0);
        l3result.add(8);
        l3result.add(4);
        l3result.add(5);

        assertEquals(l1result,l1int);
        assertEquals(l2result,l2int);
        assertEquals(l3result,l3int);


    }

    @Test
    void tsp()
    {
        List<api.NodeData> targets = new LinkedList<>();
        targets.add(n1);
        targets.add(n4);
        targets.add(n5);
        targets.add(n7);
        targets.add(n9);
        init();
        List<api.NodeData> temp = algorithms.tsp(targets);
        List<Integer> tmpToInt = new LinkedList<>();
        Iterator<api.NodeData> t = temp.iterator();
        while (t.hasNext())
            tmpToInt.add(t.next().getKey());
        List<Integer> results = new LinkedList<>();
        results.add(n1.getKey());
        results.add(n2.getKey());
        results.add(n4.getKey());
        results.add(n3.getKey());
        results.add(n2.getKey());
        results.add(n1.getKey());
        results.add(n9.getKey());
        results.add(n5.getKey());
        results.add(n7.getKey());
        assertEquals(results,tmpToInt);
    }


    @Test
    void copy() {
        init();
        DGraph copyGraph = algorithms.copy();
        assertNotEquals(dGraph.getNode(n1.getKey()),copyGraph.getNode(n1.getKey()));
        assertNotEquals(dGraph.getNode(n2.getKey()),copyGraph.getNode(n2.getKey()));
        assertNotEquals(dGraph.getE(n1.getKey()),copyGraph.getE(n1.getKey()));
        Graph_Algo copyG = new Graph_Algo(copyGraph);
        //copyG.init(copyGraph);
        assertEquals(algorithms.isConnected(),copyG.isConnected());
    }
}