package GUI;

import javax.swing.*;

public class mainInterface extends JFrame
{
    private JTabbedPane tabbedPane;
    private JPanel mainPanel;

    public mainInterface()
    {
        this.setTitle("btw, i hate analyse num");

        //add individual tabs
        tabbedPane.addTab("Gauss/Crammer", (new gaussCrammer()).getPanel());
        tabbedPane.addTab("Jacobi/GaussSciedle", (new jacobiGaussSciedle()).getPanel());
        tabbedPane.addTab("Bisection/Sécant", (new bisectionSecant()).getPanel());
        tabbedPane.addTab("LU/LLt", (new luLlt()).getPanel());
        tabbedPane.addTab("Fixed Point", (new fixedPoint()).getPanel());
        tabbedPane.addTab("Newton raphson", (new newtonRaphson()).getPanel());


        this.setContentPane(mainPanel);
        setLocationRelativeTo(null);
        this.setBounds(200, 200, 800, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
