package GUI;

import javax.swing.*;
import java.awt.*;

public class mainInterface extends JFrame
{
    public mainInterface()
    {
        this.setTitle("Analyse numeerique!");
        this.setBounds(200, 200, 500, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        //first tab
        JPanel tab1 = new luLlt();

        //second tab
        JPanel tab2 = new gauss();

        //third tab
        JPanel tab3 = new fixedPoint();

        //add tabes into the tabbed pane
        tabbedPane.addTab("Gauss/Crammer", tab2);
        tabbedPane.addTab("LU/LLT", tab1);
        tabbedPane.addTab("Fixed Point", tab3);

        this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        this.setBounds(200, 50, 900 , 600);
        this.setVisible(true);
    }
}