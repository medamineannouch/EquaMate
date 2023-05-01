package GUI;

import javax.swing.*;
import java.awt.*;

public class temp extends JFrame
{
    final static int extraWindowWidth = 100;
    public temp()
    {
        this.setTitle("Btw, i hate analyse numerique!");
        this.setBounds(200, 200, 500, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        //first tab
        JPanel tab1 = new JPanel();
        tab1.add(new JLabel("btw, i hate ENSAM!"));

        //second tab
        JPanel tab2 = new JPanel();
        tab2.add(new JLabel("btw, i hate tram!"));

        //add tabes into the tabbed pane
        tabbedPane.addTab("tab1", tab1);
        tabbedPane.addTab("tab2", tab2);

        this.getContentPane().add(tabbedPane, BorderLayout.CENTER);


        this.setVisible(true);
    }
}
