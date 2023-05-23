package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class mainInterface extends JFrame
{
    private JTabbedPane tabbedPane;
    public mainInterface() {
        setTitle("EquaMate");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Calculate the dimensions based on screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.6);
        int height = (int) (screenSize.height * 0.6);
        setBounds((screenSize.width - width) / 2, (screenSize.height - height) / 2, width, height);
        // Create the main window with description and group buttons
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // Description label
        JLabel descriptionLabel = new JLabel("Select the System type:");
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.gridx = 0;
        gbcLabel.gridy = 0;
        gbcLabel.gridwidth = 2;
        gbcLabel.anchor = GridBagConstraints.CENTER;
        gbcLabel.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(descriptionLabel, gbcLabel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton linearMethodsButton = new JButton("Linear Systems");
        styleButton(linearMethodsButton);
        linearMethodsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openGroupWindow("Linear Systems");
            }
        });
        mainPanel.add(linearMethodsButton, gbc);

        JButton nonLinearMethodsButton = new JButton("Non Linear Systems");
        styleButton(nonLinearMethodsButton);
        nonLinearMethodsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openGroupWindow("Non Linear Systems");
            }
        });
        gbc.gridx++;
        mainPanel.add(nonLinearMethodsButton, gbc);

        getContentPane().add(mainPanel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setPreferredSize(new Dimension(200, 80));
        button.setFont(new Font("Arial", Font.BOLD, 16));
    }

    private void openGroupWindow(String groupName) {
        JFrame groupFrame = new JFrame(groupName);
        groupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        groupFrame.setBounds(200, 200, 500, 200);

        tabbedPane = new JTabbedPane();

        if (groupName.equals("Linear Systems")) {
            //first tab
            JPanel tab1 = new luLlt();

            //second tab
            JPanel tab2 = new gauss_crammer();
            //seventh tab
            JPanel tab7= new JacobiGaussSeidel();

            tabbedPane.addTab("Gauss/Crammer", tab2);
            tabbedPane.addTab("LU/LLT", tab1);
            tabbedPane.addTab("Jacobi/Gauss-Seidel", tab7);

        } else if (groupName.equals("Non Linear Systems")) {
            //third tab
            JPanel tab3 = new fixedPoint();

            //fourth tab
            JPanel tab4 = new newtonRaphson();

            //fifth tab
            JPanel tab5 = new secant();

            //sixth tab
            JPanel tab6= new Broyden();
            JPanel tab8 = new RechercheDichotomiqueGui();

            tabbedPane.addTab("Fixed Point", tab3);
            tabbedPane.addTab("Newton Raphson", tab4);
            tabbedPane.addTab("Sécante", tab5);
            tabbedPane.addTab("Broyden", tab6);
            tabbedPane.addTab("Recherche Dichotomique", tab8);

        }
        this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        this.setBounds(200, 50, 900 , 600);
        this.setVisible(true);

        groupFrame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        groupFrame.pack();
        groupFrame.setBounds(200, 50, 900 , 600);
        groupFrame.setVisible(true);
    }
}