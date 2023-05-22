package GUI;

import GUI.Componenets.*;
import NonlinearEquations.BroydenMethod;

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

        //fourth tab
        JPanel tab4 = new newtonRaphson();

        //fifth tab
        JPanel tab5 = new secant();

        //sixth tab
        JPanel tab6= new Broyden();

        //add tabes into the tabbed pane
        tabbedPane.addTab("Gauss/Crammer", tab2);
        tabbedPane.addTab("LU/LLT", tab1);
        tabbedPane.addTab("Fixed Point", tab3);
        tabbedPane.addTab("Newton Raphson", tab4);
        tabbedPane.addTab("Sécante", tab5);
        tabbedPane.addTab("Broyden", tab6);

        this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        this.setBounds(200, 50, 900 , 600);
        this.setVisible(true);
    }
}