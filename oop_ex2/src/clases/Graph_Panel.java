package clases;

import api.EdgeData;
import api.GeoLocation;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class Graph_Panel extends JPanel {
    private DGraph graph;
    private double minx = Double.MAX_VALUE, maxx = 0, miny = Double.MAX_VALUE, maxy = 0;

    Graph_Panel(DGraph graph) {
        this.graph = graph;
        this.setBackground(Color.white);
    }

    public void findingv() {
        Iterator<api.NodeData> i = this.graph.nodeIter();
        while (i.hasNext()) {
            NodeData node = (NodeData) i.next();
            GeoLocation l = node.getLocation();
            if (this.maxx < l.x())
                this.maxx = l.x();
            if (this.maxy < l.y())
                this.maxy = l.y();
            if (this.miny > l.y())
                this.miny = l.y();
            if (this.minx > l.x())
                this.minx = l.x();

        }
    }

    public double scalex(double x) {
        x = x - this.minx;
        x = ((this.getWidth() - (2 * 11)) * 0.968) * (x / (this.maxx - this.minx));
        return x + 11;
    }

    public double scaley(double y) {
        y = y - this.miny;
        y = ((this.getHeight() - (2 * 11)) * 0.88) * (y / (this.maxy - this.miny));
        return y + 11;
    }
    private void arrows(Graphics g, int x1, int y1, int x2, int y2) {
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx * dx + dy * dy);
        double xm = D - 21, xn = xm, ym = 6, yn = -6, x;
        double sin = dy / D, cos = dx / D;

        x = xm * cos - ym * sin + x1;
        ym = xm * sin + ym * cos + y1;
        xm = x;

        x = xn * cos - yn * sin + x1;
        yn = xn * sin + yn * cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints, ypoints, 3);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D newg = (Graphics2D) g;
        findingv();
        newg.setStroke(new BasicStroke(2));
        newg.setColor(Color.pink);
        // draw edges
        Iterator i = graph.edgeIter();
        while (i.hasNext()) {
            EdgeData e = (Edata) i.next();
            NodeData s, d;
            s = (NodeData) graph.getNode(e.getSrc());
            d = (NodeData) graph.getNode(e.getDest());
            GeoLocation l1 = s.getLocation();
            GeoLocation l2 = d.getLocation();
            arrows(g,(int) scalex(l1.x()), (int) scaley(l1.y()), (int) scalex(l2.x()), (int) scaley(l2.y()));
            setVisible(true);
        }
        // draw nodes
        Iterator i2 = graph.nodeIter();
        while (i2.hasNext()) {
            NodeData n = (NodeData) i2.next();
            Point3D n2 = n.getLocation();
            newg.fillOval((int)scalex(n2.x())-4,(int)scaley(n2.y())-4,8,8);
            newg.setColor(Color.darkGray);
            newg.drawString(n.getKey() +"", (int)scalex(n2.x())+4,(int)scaley(n2.y())+4);
            setVisible(true);
        }

    }
}
