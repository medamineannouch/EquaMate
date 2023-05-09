package GUI;

import GUI.Componenets.matrixInput;
import GUI.Componenets.solutionHeader;
import Methods.linearSystems.gaussianElimination;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gauss extends JPanel
{
    public matrixInput matrixInput;
    public JPanel matricesContainer; //a wrapper for the above component, used to preserve the old place
    public solutionHeader solutionMatrix;
    public JPanel solutionPanel;
    public JComboBox<String> method;

    public gauss()
    {
        gauss app = this; //pointer for listeners
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        matricesContainer = new JPanel();
        matricesContainer.setPreferredSize(new Dimension(0, 200)); //prevents size change while redrawing
        matricesContainer.setLayout(new BoxLayout(matricesContainer, BoxLayout.X_AXIS));
        matrixInput = new matrixInput(2);

        matricesContainer.add(matrixInput);

        this.add(matricesContainer);

        //add dimensions chooser
        JPanel dimensionsChooser = new JPanel(new FlowLayout());
        dimensionsChooser.setPreferredSize(new Dimension(100, 30));

        SpinnerNumberModel model = new SpinnerNumberModel(2, 2, Integer.MAX_VALUE, 1);
        JSpinner spinner = new JSpinner(model);
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinner);
        spinner.setEditor(editor);
        // Add a change listener to the spinner to print the selected value
        spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //redraw matrix input
                app.redraw((Integer)spinner.getValue());
            }
        });

        dimensionsChooser.add(new JLabel("Dimensions: "));
        dimensionsChooser.add(spinner);
        this.add(dimensionsChooser);

        //solution matrix
        solutionPanel = new JPanel(); //wrapper
        solutionPanel.setLayout(new FlowLayout());
        solutionMatrix = new solutionHeader(2);
        solutionPanel.add(solutionMatrix);
        this.add(solutionPanel);

        //method chooser
        JPanel methodList = new JPanel(new FlowLayout());
        methodList.setPreferredSize(new Dimension(0, 30));
        methodList.add(new JLabel("Method: "));

        String[] methods = {"Gauss", "Crammer"};
        method = new JComboBox<>(methods);
        methodList.add(method, BorderLayout.CENTER);

        //solve btn
        JButton solveBtn = new JButton("Solve");
        methodList.add(solveBtn);

        this.add(methodList);
        solveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solve();
            }
        });
    }

    public void redraw(int newDim)
    {
        matricesContainer.remove(matrixInput);
        matrixInput = new matrixInput(newDim);
        matricesContainer.add(matrixInput);

        solutionPanel.remove(solutionMatrix);
        solutionMatrix = new solutionHeader(newDim);
        solutionPanel.add(solutionMatrix);

        this.updateUI();
    }

    public void solve()
    {
        try
        {
            if(((String)method.getSelectedItem()).equals("Gauss"))
            {
                double[] res = gaussianElimination.gaussianEliminationMathod(matrixInput.getA(), matrixInput.getB());
                solutionMatrix.setValues(res);
            }
            else
            {
                System.out.println("crammer");
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Warning: Check your input", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}

