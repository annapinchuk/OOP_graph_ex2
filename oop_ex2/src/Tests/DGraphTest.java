package Tests;

import api.EdgeData;
import clases.DGraph;
import clases.Edata;
import clases.NodeData;

import clases.Point3D;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Collection;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class DGraphTest {
    static DGraph dGraph;
    static Point3D p1;
    static Point3D p3;
    static Point3D p2;
    static Point3D p4;

    static NodeData n1;
    static NodeData  n2;
    static NodeData  n3;
    static NodeData n4;

    static EdgeData e1;
    static EdgeData e2;
    static EdgeData e3;
    static EdgeData e4;
    static EdgeData e5;
    @BeforeEach
    void setUp() {
        dGraph = new DGraph();
        p1 = new Point3D(-1,1,2);
        p3 = new Point3D(-5,6,3);
        p2 = new Point3D(-4,3,4);
        p4 = new Point3D(-6,7,3);

        n1 = new NodeData(p1,4,1);
        n2 = new NodeData(p2,5,1);
        n3 = new NodeData(p3,6,1);
        n4 = new NodeData(p4,7,1);

        e1 = new Edata(n1.getKey(), n2.getKey(),1);
        e2 = new Edata(n1.getKey(),n3.getKey(),4);
        e3 = new Edata(n1.getKey(),n4.getKey(),9);
        e4 = new Edata(n2.getKey(),n3.getKey(),1);
        e5 = new Edata(n2.getKey(),n4.getKey(),16);

        dGraph.addNode(n1);
        dGraph.addNode(n2);
        dGraph.addNode(n3);
        dGraph.addNode(n4);
        dGraph.connect(n1.getKey(), n2.getKey(),1);
        dGraph.connect(n1.getKey(), n3.getKey(),4);
        dGraph.connect(n1.getKey(), n4.getKey(),9);
        dGraph.connect(n2.getKey(), n1.getKey(),1);
        dGraph.connect(n2.getKey(), n4.getKey(),16);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getNode() {
        assertEquals(n1.getKey(),dGraph.getNode(n1.getKey()).getKey());
        assertEquals(n1.getTag(),dGraph.getNode(n1.getKey()).getTag());
        assertEquals(n1.getInfo(),dGraph.getNode(n1.getKey()).getInfo());
        assertEquals(n1.getWeight(),dGraph.getNode(n1.getKey()).getWeight());
        assertEquals(n1.getLocation().x(),dGraph.getNode(n1.getKey()).getLocation().x());
        assertEquals(n1.getLocation().y(),dGraph.getNode(n1.getKey()).getLocation().y());

    }

    @Test
    void getEdge() {
        assertEquals(1,dGraph.getEdge(n1.getKey(),n2.getKey()).getWeight());
        assertEquals(n1.getKey(),dGraph.getEdge(n1.getKey(),n2.getKey()).getSrc());
        assertEquals(n2.getKey(),dGraph.getEdge(n1.getKey(),n2.getKey()).getDest());

    }

    @Test
    void addNode() {
        Point3D temPoint =new Point3D(-8,9,5);
        NodeData tempN = new NodeData(temPoint,1,1);

        dGraph.addNode(tempN);
        assertEquals(tempN.getKey(),dGraph.getNode(tempN.getKey()).getKey());
        assertEquals(tempN.getTag(),dGraph.getNode(tempN.getKey()).getTag());
        assertEquals(tempN.getInfo(),dGraph.getNode(tempN.getKey()).getInfo());
        assertEquals(tempN.getWeight(),dGraph.getNode(tempN.getKey()).getWeight());
        assertEquals(tempN.getLocation().x(),dGraph.getNode(tempN.getKey()).getLocation().x());
        assertEquals(tempN.getLocation().y(),dGraph.getNode(tempN.getKey()).getLocation().y());

    }

    @Test
    void connect() {
        dGraph.connect(n4.getKey(),n3.getKey(),1);
        EdgeData tempE = new Edata(n4.getKey(),n3.getKey(),1);
        assertEquals(tempE.getSrc(),dGraph.getEdge(n4.getKey(),n3.getKey()).getSrc());
        assertEquals(tempE.getDest(),dGraph.getEdge(n4.getKey(),n3.getKey()).getDest());
        assertEquals(tempE.getWeight(),dGraph.getEdge(n4.getKey(),n3.getKey()).getWeight());
    }

    @Test
    void getV() {
       Collection<api.NodeData> collection = dGraph.getV();
       Collection<api.NodeData> expection = new LinkedList<>();
       Collection<Integer> expectedInt = new LinkedList<>();
       Collection<Double> expectedWeight = new LinkedList<>();
       Collection<Double> expectedPx = new LinkedList<>();
       Collection<Double> expectedPy = new LinkedList<>();
       Collection<Integer> collectionInt = new LinkedList<>();
       Collection<Double> collectionWeight = new LinkedList<>();
       Collection<Double> collectionPx = new LinkedList<>();
       Collection<Double> collectionPy = new LinkedList<>();
       expection.add(n1);
       expection.add(n2);
       expection.add(n3);
       expection.add(n4);
        for (api.NodeData temp : collection) {
            collectionInt.add(temp.getKey());
            collectionWeight.add(temp.getWeight());
            collectionPx.add(temp.getLocation().x());
            collectionPy.add(temp.getLocation().y());
        }
        for (api.NodeData temp : expection) {
           expectedInt.add(temp.getKey());
            expectedWeight.add(temp.getWeight());
            expectedPx.add(temp.getLocation().x());
            expectedPy.add(temp.getLocation().y());
        }

       assertEquals(expectedInt,collectionInt);
       assertEquals(expectedWeight,collectionWeight);
       assertEquals(expectedPx,collectionPx);
       assertEquals(expectedPy,collectionPy);
    }

    @Test
    void getE() {

        assertEquals(0,dGraph.getE(n4.getKey()).size());

        Collection<EdgeData> collection = dGraph.getE(n1.getKey());
        Collection<EdgeData> expection = new LinkedList<>();
        Collection<Double> expectedWeight = new LinkedList<>();
        Collection<Integer> expectedSrc = new LinkedList<>();
        Collection<Integer> expectedDes = new LinkedList<>();
        Collection<Double> collectionWeight = new LinkedList<>();
        Collection<Integer> collectionSrc = new LinkedList<>();
        Collection<Integer> collectionDes = new LinkedList<>();
        expection.add(e1);
        expection.add(e2);
        expection.add(e3);
        for (EdgeData temp : collection) {
            collectionWeight.add(temp.getWeight());
            collectionSrc.add(temp.getSrc());
            collectionDes.add(temp.getDest());
        }
        for (EdgeData temp : expection) {
            expectedWeight.add(temp.getWeight());
            expectedSrc.add(temp.getSrc());
            expectedDes.add(temp.getDest());
        }

        assertEquals(expectedWeight,collectionWeight);
        assertEquals(expectedSrc,collectionSrc);
        assertEquals(expectedDes,collectionDes);


    }


    @Test
    void removeNode() {
        dGraph.removeNode(n1.getKey());

        assertNull(dGraph.getNode(n1.getKey()));
        assertNull(dGraph.getE(n1.getKey()));
        assertNull(dGraph.getE(n1.getKey()));
        assertNull(dGraph.getEdge(1,0));

    }

    @Test
    void removeEdge() {
        dGraph.removeEdge(1,0);
        assertNull(dGraph.getEdge(1,0));

    }

    @Test
    void nodeSize() {
        assertEquals(4,dGraph.nodeSize());
    }

    @Test
    void edgeSize() {
        assertEquals(5,dGraph.edgeSize());

    }

    @Test
    void getMC() {
        assertEquals(9,dGraph.getMC());
       NodeData temp = new NodeData(new Point3D(-80,2,32),2,1);
        dGraph.addNode(temp);
        assertEquals(10,dGraph.getMC());
        dGraph.removeNode(temp.getKey());
        assert(10<dGraph.getMC());

    }

}