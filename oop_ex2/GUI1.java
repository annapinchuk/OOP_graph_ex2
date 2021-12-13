package clases;


import com.company.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.LinkedList;

public class GUI1 extends JFrame implements ActionListener {
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu graphMenu;
    JMenu helpMenu;

    JMenuItem loadItem;
    JMenuItem saveItem;
    JMenuItem paintItem;
    JMenuItem exitItem;
    JMenuItem addGraphItem;
    JMenuItem GraphItem;
    JMenuItem DGraphItem;
    JMenuItem shortItem;
    JMenuItem paint2Item;
    JMenuItem idItem;
    JMenuItem graphItem;
    JMenuItem nameItem;
    ImageIcon loadIcon;
    ImageIcon saveIcon;
    ImageIcon paintIcon;
    ImageIcon paint2Icon;
    ImageIcon exitIcon;
    ImageIcon addGraphIcon;
    ImageIcon shortIcon;
    ImageIcon DGraphIcon;
    ImageIcon GraphIcon;
    ImageIcon graphIcon;
    JButton button;
    JButton button1;
    JLabel label;

    GUI1() {
        this.setDefaultCloseOperation(3);
        this.setLayout(new FlowLayout());
        this.button = new JButton("Select Json File");
        this.button.addActionListener(this);
        this.add(this.button);
        this.pack();
        this.setVisible(true);
        // this.setDefaultCloseOperation(3);
        this.setSize(500, 500);
        this.setLayout(new FlowLayout());
        this.menuBar = new JMenuBar();
        this.loadIcon = new ImageIcon();


        this.loadIcon = new ImageIcon("./ImageIcon/EXIT.png");
        this.exitIcon = new ImageIcon("./ImageIcon/img.png");
        this.saveIcon = new ImageIcon("./ImageIcon/save.png_.jpg");
        this.GraphIcon = new ImageIcon("./ImageIcon/Graph.png");
        this.DGraphIcon = new ImageIcon("./ImageIcon/D.png");
        this.shortIcon = new ImageIcon("./ImageIcon/shortestPath.jpg");
        this.addGraphIcon = new ImageIcon("./ImageIcon/add.png");

        this.graphIcon = new ImageIcon("graph.png");
        this.loadIcon = scaleImageIcon(loadIcon, 30, 30);
        this.saveIcon = scaleImageIcon(saveIcon, 30, 30);
        this.exitIcon = scaleImageIcon(exitIcon, 30, 30);
        this.GraphIcon = scaleImageIcon(GraphIcon, 40, 40);

        this.DGraphIcon= scaleImageIcon(DGraphIcon, 40, 40);
        this.shortIcon = scaleImageIcon(shortIcon, 30, 30);
        this.addGraphIcon= scaleImageIcon(addGraphIcon, 40, 50);

        this.setJMenuBar(this.menuBar);
        this.fileMenu = new JMenu("File");
        this.graphMenu = new JMenu("Graph");
        this.helpMenu = new JMenu("Help");
        this.loadItem = new JMenuItem("Load");
        this.saveItem = new JMenuItem("Save");
        this.paintItem = new JMenuItem("paint");
        this.exitItem = new JMenuItem("Exit");
        this.GraphItem = new JMenuItem("Graph");
        this.addGraphItem = new JMenuItem("Add Node");
        this.DGraphItem = new JMenuItem("Delete Node");
        this.paint2Item = new JMenuItem("Paint new graph");
        this.idItem = new JMenuItem("Id");
        this.loadItem.addActionListener(this);
        this.saveItem.addActionListener(this);
        this.paintItem.addActionListener(this);
        this.exitItem.addActionListener(this);
        this.idItem.addActionListener(this);
        this.GraphItem.addActionListener(this);
        this.addGraphItem.addActionListener(this);
        this.DGraphItem.addActionListener(this);
        //this.shortItem.addActionListener(this);

        this.paint2Item.addActionListener(this);
        loadItem.setIcon(loadIcon);

        this.loadItem.setIcon(this.loadIcon);
        this.saveItem.setIcon(this.saveIcon);
        this.paintItem.setIcon(this.paintIcon);
        this.paint2Item.setIcon(this.paint2Icon);
        this.GraphItem.setIcon(this.GraphIcon);
        this.DGraphItem.setIcon(this.DGraphIcon);
        //this.shortItem.setIcon(this.shortIcon);
        this.addGraphItem.setIcon(this.addGraphIcon);

        this.exitItem.setIcon(this.exitIcon);
        this.fileMenu.setMnemonic(70);
        this.graphMenu.setMnemonic(69);
        this.helpMenu.setMnemonic(72);
        this.loadItem.setMnemonic(76);
        this.saveItem.setMnemonic(83);
        this.paintItem.setMnemonic(KeyEvent.VK_P); //Alt+P
        this.exitItem.setMnemonic(69);
        this.idItem.setMnemonic(73);
        this.fileMenu.add(this.loadItem);
        this.fileMenu.add(this.saveItem);
        this.fileMenu.add(this.paintItem);
        this.fileMenu.add(this.exitItem);
        this.graphMenu.add(this.GraphItem);
        //this.GraphItem.add(shortItem);
        this.graphMenu.add(this.addGraphItem);
        this.graphMenu.add(this.DGraphItem);
        this.graphMenu.add(this.paint2Item);



        this.helpMenu.add(this.idItem);
        this.menuBar.add(this.fileMenu);
        this.menuBar.add(this.graphMenu);
        this.menuBar.add(this.helpMenu);
        this.setVisible(true);
    }

    private ImageIcon scaleImageIcon(ImageIcon imageIcon, int width, int height) {
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // scale it the smooth way
        return new ImageIcon(newimg);  // transform it back
    }


    public static void main(String[] args) {
        new GUI1();

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.loadItem) {

            Main.load();
            Main.save();
            new MyGraph();
            System.out.println("*beep boop* you loaded a file");
        }
        if (e.getSource() == this.paint2Item) {


            new MyGraph();
            System.out.println("*beep boop* you loaded a file");
        }
        if (e.getSource() == this.saveItem) {
            System.out.println("*beep boop* you saveItem a file");
        }
        if (e.getSource() == this.paintItem) {
            System.out.println("*beep boop* you paintItem a file");
        }

        if (e.getSource() == this.exitItem) {
            System.exit(0);
        }

        if (e.getSource() == this.idItem) {
           

            String name = "205961972_206431082";

            System.out.println("id:205961972_206431082");
        }

        JFileChooser fileChooser;
        if (e.getSource() == this.button) {
            fileChooser = new JFileChooser();
            fileChooser.showSaveDialog((Component) null);
        }

        if (e.getSource() == this.button) {
            fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            int response = fileChooser.showOpenDialog((Component) null);
            System.out.println(fileChooser.showOpenDialog((Component) null));
            if (response == 0) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                System.out.println(file);
            }
        }

    }

    public class MyGraph extends JFrame {
        public MyGraph() throws HeadlessException {
            this.add(new GraphP());
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(500, 500);
//        this.add(new GraphP());
            this.setVisible(true);
        }

        public class GraphP extends JPanel implements MouseListener {
            LinkedList<Point2D> points = new LinkedList<Point2D>();

            public GraphP() {
                this.addMouseListener(this);
            }

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                Point2D p = new Point2D.Double(e.getX(), e.getY());
                points.add(p);
                repaint();

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Point2D prev = null;
                for (Point2D p : points) {
                    g.setColor(new Color(26, 99, 234));
                    g.fillOval((int) p.getX() - 10, (int) p.getY() - 10, 20, 20);
                    if (prev != null) {
                        Double dist = p.distance(prev);
                        String distS = dist.toString().substring(0, dist.toString().indexOf(".") + 2);
                        g.drawLine((int) p.getX(), (int) p.getY(), (int) prev.getX(), (int) prev.getY());
                        g.drawString(distS, (int) ((p.getX() + prev.getX()) / 2), (int) ((p.getY() + prev.getY()) / 2));
                    }
                    prev = p;

                }

            }
        }


    }
}
