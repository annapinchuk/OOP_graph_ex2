package clases;

import api.EdgeData;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class My_GUI extends JFrame implements ActionListener {
    private Graph_Algo a;

    JMenu File;
    JMenu Edit;
    JMenu Algo;
    JMenuItem Save;
    JMenuItem Load;
    JMenuItem Add_Node;
    JMenuItem Add_Edge;
    JMenuItem Remove_Node;
    JMenuItem Remove_Edge;
    JMenuItem Center;
    JMenuItem Is_Connected;
    JMenuItem TSP;
    JMenuItem Shortest_path;
    Graph_Panel g;
    JFrame frame;

    My_GUI(Graph_Algo a) {
        this.a = a;
        Graph_Panel gp = new Graph_Panel(((DGraph) a.DGraph));
        this.add(gp);
        //Creating the Frame
        frame = new JFrame("OOP sucks");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        File = new JMenu("File");
        Edit = new JMenu("Edit");
        Algo = new JMenu("Algo");
        mb.add(File);
        mb.add(Edit);
        mb.add(Algo);
        Save = new JMenuItem("Save");
        Load = new JMenuItem("Load");
        Add_Node = new JMenuItem("Add Node");
        Add_Edge = new JMenuItem("Add Edge");
        Remove_Node = new JMenuItem("Remove Node");
        Remove_Edge = new JMenuItem("Remove Edge");
        Center = new JMenuItem("Center");
        Is_Connected = new JMenuItem("Is Connected");
        TSP = new JMenuItem("TSP");
        Shortest_path = new JMenuItem("Shortest path");
        File.add(Save);
        File.add(Load);
        Edit.add(Add_Node);
        Edit.add(Add_Edge);
        Edit.add(Remove_Node);
        Edit.add(Remove_Edge);
        Algo.add(Center);
        Algo.add(Is_Connected);
        Algo.add(TSP);
        Algo.add(Shortest_path);

        //
        Save.addActionListener(this);
        Load.addActionListener(this);
        Add_Node.addActionListener(this);
        Add_Edge.addActionListener(this);
        Remove_Node.addActionListener(this);
        Remove_Edge.addActionListener(this);
        Center.addActionListener(this);
        Is_Connected.addActionListener(this);
        TSP.addActionListener(this);
        Shortest_path.addActionListener(this);
        //Adding Components to the frame.

        g = new Graph_Panel((DGraph) a.DGraph);
        g.add(new Graph_Panel((DGraph) a.DGraph));
        frame.add(g);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser;
        if (e.getSource() == this.Save) {
            fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(this.frame.getParent()) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                a.save(fileChooser.getSelectedFile().getAbsolutePath());
                JOptionPane.showMessageDialog(frame, "saved");
            } else {
                JOptionPane.showMessageDialog(frame, "try again");
            }
        }
        if (e.getSource() == this.Load) {
            fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            int response = fileChooser.showOpenDialog((Component) null);
            if (response == 0) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                a.load(file.getAbsolutePath());
                g.removeAll();
                g.revalidate();
                frame.remove(frame.getGlassPane());
                frame.revalidate();
                g.setVisible(false);
                g = new Graph_Panel((DGraph) a.DGraph);
                g.add(new Graph_Panel((DGraph) a.DGraph));
                frame.add(g);
                g.removeAll();
                frame.repaint();
                this.repaint();
                System.out.println(a.DGraph.edgeSize());


            }

        }
        if (e.getSource() == this.Add_Node) {
            String s1 = JOptionPane.showInputDialog("Give me X pls ");
            String s2 = JOptionPane.showInputDialog("Give me Y pls");
            String s3 = JOptionPane.showInputDialog("Give me new ID pls");
            try {
                int i = Integer.parseInt(s3);
                Point3D location = new Point3D(Double.parseDouble(s1), Double.parseDouble(s2), 0);
                NodeData n = new clases.NodeData(location, i, 1);
                a.DGraph.addNode(n);
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(frame, "HOO SORRY ITS NOT A NUMBER :?");
            }
            g.setVisible(false);
            g = new Graph_Panel((DGraph) a.DGraph);
            g.add(new Graph_Panel((DGraph) a.DGraph));
            frame.add(g);
            g.removeAll();
            frame.repaint();
            this.repaint();
            frame.setVisible(true);
        }
        if (e.getSource() == this.Add_Edge) {
            String s1 = JOptionPane.showInputDialog("Give me first ID pls ");
            String s2 = JOptionPane.showInputDialog("Give me second ID pls ");
            String s5 = JOptionPane.showInputDialog("Give me Weight pls ");
            try {
                int s3 = Integer.parseInt(s1);
                int s4 = Integer.parseInt(s2);
                int s6 = Integer.parseInt(s5);
                if (a.DGraph.getNode(s3) != null && a.DGraph.getNode(s4) != null) {
                    a.DGraph.connect(a.DGraph.getNode(s3).getKey(), a.DGraph.getNode(s4).getKey(), s6);
                    g = new Graph_Panel((DGraph) a.DGraph);
                    g.add(new Graph_Panel((DGraph) a.DGraph));
                    frame.add(g);
                    g.removeAll();
                    frame.repaint();
                    this.repaint();
                    frame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(frame, "NO SUCH NODE, try again");
                }
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(frame, "HOO SORRY ITS NOT A NUMBER :?");
            }
        }
        // bugged
        if (e.getSource() == this.Remove_Node) {
            String s1 = JOptionPane.showInputDialog("Give me ID pls ");
            try {
                int s2 = Integer.parseInt(s1);
                if (a.DGraph.getNode(s2) != null) {
                    a.DGraph.removeNode(s2);
                } else {
                    JOptionPane.showMessageDialog(frame, "NO SUCH NODE");
                }
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(frame, "HOO SORRY ITS NOT A NUMBER :?");
            }
            g.setVisible(false);
            g = new Graph_Panel((DGraph) a.DGraph);
            g.add(new Graph_Panel((DGraph) a.DGraph));
            frame.add(g);
            g.removeAll();
            frame.repaint();
            this.repaint();
            frame.setVisible(true);
        }

        if (e.getSource() == this.Remove_Edge) {
            String s1 = JOptionPane.showInputDialog("Give me first ID (src) to remove edgefrom pls ");
            String s2 = JOptionPane.showInputDialog("Give me second ID (dect) to reomve edge from pls ");
            try {
                int s3 = Integer.parseInt(s1);
                int s4 = Integer.parseInt(s2);
                if (a.DGraph.getEdge(s3, s4) != null) {
                    a.DGraph.removeEdge(s3, s4);
                    JOptionPane.showMessageDialog(frame, "removed");
                } else {
                    JOptionPane.showMessageDialog(frame, "NO SUCH DIRECTED EDGE");
                }
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(frame, "HOO SORRY ITS NOT A NUMBER :?");
            }
            g.setVisible(false);
            g = new Graph_Panel((DGraph) a.DGraph);
            g.add(new Graph_Panel((DGraph) a.DGraph));
            frame.add(g);
            g.removeAll();
            frame.repaint();
            this.repaint();
            frame.setVisible(true);
        }


        if (e.getSource() == this.Center) {
            JOptionPane.showMessageDialog(frame, "the center is " + a.center().getKey());
        }

        if (e.getSource() == this.Is_Connected) {
            JOptionPane.showMessageDialog(frame, "the graph is " + (a.isConnected() ? "" : "not ") + "connected");
        }
        // TODO pls change this, pls change this
        if (e.getSource() == this.TSP) {
            try {
                String s1 = JOptionPane.showInputDialog("Give your ID's pls");
                String[] ids = s1.split(",");
                int[] IDS = new int[ids.length];
                List<api.NodeData> cities = new ArrayList<>();

                for (int i = 0; i < ids.length; i++) {
                    IDS[i] = Integer.parseInt(ids[i]);
                    cities.add(this.a.getGraph().getNode(IDS[i]));
                }
                List<NodeData> list = a.tsp(cities);
                String path = "" +list.get(0).getKey();
                for (int i = 1; i < list.size(); ++i) {
                    path += "->" + list.get(i).getKey();
                }

                JOptionPane.showMessageDialog(null, path, "TSP", JOptionPane.PLAIN_MESSAGE);

            } catch (NumberFormatException eaher) {
                JOptionPane.showMessageDialog(null, "HOO SORRY ITS NOT A NUMBER :?", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        }
        if (e.getSource() == this.Shortest_path) {
            String s1 = JOptionPane.showInputDialog("Give me first ID (src) pls ");
            String s2 = JOptionPane.showInputDialog("Give me second ID (dect) pls ");
            int maxPathLine = 6;
            try {
                int s3 = Integer.parseInt(s1);
                int s4 = Integer.parseInt(s2);
                List<api.NodeData> path = a.shortestPath(s3, s4);
                StringBuilder s = new StringBuilder();
                int i = 0;
                for (api.NodeData n : path) {
                    s.append(n.getKey());
                    if (i < path.size() - 1) {
                        s.append(" -> ");
                    }
                    if (i != 0 && i != path.size() && i % maxPathLine == 0) {
                        s.append("\n");
                    }
                    i++;
                }
                JOptionPane.showMessageDialog(frame, s.toString(), "the distance of the path is:  " + new DecimalFormat("#.###").format(a.shortestPathDist(s3, s4)), JOptionPane.PLAIN_MESSAGE);

            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(frame, "HOO SORRY ITS NOT A NUMBER 😒😒");
            }
        }
    }

}
