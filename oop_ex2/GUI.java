package clases;


import com.company.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.LinkedList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class GUI extends JFrame implements ActionListener
{


    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu graphMenu;
    JMenu helpMenu;
    JMenuItem loadItem;
    JMenuItem saveItem;
    JMenuItem paintItem;
    JMenuItem exitItem;
    JMenuItem E1Item;
    JMenuItem E2Item;
    JMenuItem idItem;
    JMenuItem graphItem;
    JMenuItem nameItem;
    ImageIcon loadIcon;
    ImageIcon saveIcon;
    ImageIcon paintIcon;
    ImageIcon exitIcon;
    ImageIcon graphIcon;
    JButton button;
    JButton button1;
    JLabel label;

    GUI() {
        this.setDefaultCloseOperation(3);
        this.setLayout(new FlowLayout());
        this.button = new JButton("Select Json File");
        this.button.addActionListener(this);
        this.add(this.button);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
        this.setSize(500, 500);
        this.setLayout(new FlowLayout());
        this.menuBar = new JMenuBar();
        this.loadIcon = new ImageIcon();

        this.loadIcon = new ImageIcon("save.png_.jpg");
        this.exitIcon = new ImageIcon("graph.png");
        saveIcon = new ImageIcon("ImageIcon/save.png");

        this.graphIcon = new ImageIcon("graph.png");
        loadIcon = scaleImageIcon(loadIcon, 20, 20);
        saveIcon = scaleImageIcon(saveIcon, 20, 20);
        exitIcon = scaleImageIcon(exitIcon, 20, 20);

        this.setJMenuBar(this.menuBar);
        this.fileMenu = new JMenu("File");
        this.graphMenu = new JMenu("Graph");
        this.helpMenu = new JMenu("Help");
        this.loadItem = new JMenuItem("Load");
        this.saveItem = new JMenuItem("Save");
        this.paintItem = new JMenuItem("paint");
        this.exitItem = new JMenuItem("Exit");
        this.E1Item = new JMenuItem("E1");
        this.E2Item = new JMenuItem("E2");
        this.idItem = new JMenuItem("Id");
        this.loadItem.addActionListener(this);
        this.saveItem.addActionListener(this);
        this.paintItem.addActionListener(this);
        this.exitItem.addActionListener(this);
        this.idItem.addActionListener(this);
        this.E1Item.addActionListener(this);
        this.E2Item.addActionListener(this);
        loadItem.setIcon(loadIcon);

        this.loadItem.setIcon(this.loadIcon);
        this.saveItem.setIcon(this.saveIcon);
        this.paintItem.setIcon(this.paintIcon);

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
        this.graphMenu.add(this.E1Item);
        this.graphMenu.add(this.E2Item);


        this.helpMenu.add(this.idItem);
        this.menuBar.add(this.fileMenu);
        this.menuBar.add(this.graphMenu);
        this.menuBar.add(this.helpMenu);
        this.setVisible(true);
    }

    private ImageIcon scaleImageIcon(ImageIcon imageIcon, int width, int height) {
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        return new ImageIcon(newimg);  // transform it back
    }


    public static void main(String[] args) {
        new GUI();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.loadItem) {
            Main.load();
            Main.save();
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

        if (e.getSource() == this.idItem) {
            String name = "205961972_206431082";
            //MyGraph();
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
}