package GUI;

import javax.swing.*;
import java.awt.*;

public class mainInterface extends JFrame
{
    public mainInterface()
    {
        this.setTitle("EquaMate");
        this.setBounds(200, 200, 500, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        //first tab
        JPanel tab1 = new luLlt();

        //second tab
        JPanel tab2 = new gauss_crammer();

        //third tab
        JPanel tab3 = new fixedPoint();

        //fourth tab
        JPanel tab4 = new newtonRaphson();

        //fifth tab
        JPanel tab5 = new secant();

        //sixth tab
        JPanel tab6= new Broyden();

        //seventh tab
        JPanel tab7= new JacobiGaussSeidel();
        JPanel tab8 = new RechercheDichotomiqueGui();

        //add tabes into the tabbed pane
        tabbedPane.addTab("Gauss/Crammer", tab2);
        tabbedPane.addTab("LU/LLT", tab1);
        tabbedPane.addTab("Jacobi/Gauss-Seidel", tab7);
        tabbedPane.addTab("Fixed Point", tab3);
        tabbedPane.addTab("Newton Raphson", tab4);
        tabbedPane.addTab("Sécante", tab5);
        tabbedPane.addTab("Broyden", tab6);
        tabbedPane.addTab("Recherche Dichotomique", tab8);



        this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        this.setBounds(200, 50, 900 , 600);
        this.setVisible(true);
    }
}