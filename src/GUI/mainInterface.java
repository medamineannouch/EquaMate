package GUI;

import javax.swing.*;

public class mainInterface extends JFrame
{
    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;

    public mainInterface()
    {
        this.setTitle("btw, i hate analyse num");
        this.setBounds(200, 200, 800, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setContentPane(mainPanel);
        setVisible(true);
    }
}
