package GUI;

import GUI.Componenets.matricesInput;
import GUI.Componenets.solutionHeader;
import IterativeMethods.GaussSeidleMethod;
import IterativeMethods.JacobiMethod;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JacobiGaussSeidel extends JPanel
{
    public GUI.Componenets.matricesInput matricesInput;
    public JPanel matricesContainer; //a wrapper for the above component, used to preserve the old place
    public solutionHeader solutionMatrix;
    public JPanel solutionPanel;

    public solutionHeader initialSolutionHeader;
    public JPanel solutionHeaderPanel;
    public JComboBox<String> method;

    public JSpinner iterations;

    public JTextField error;
    public JacobiGaussSeidel()
    {
        JacobiGaussSeidel app = this; //pointer for listeners
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        matricesContainer = new JPanel();
        matricesContainer.setPreferredSize(new Dimension(0, 200)); //prevents size change while redrawing
        matricesContainer.setLayout(new BoxLayout(matricesContainer, BoxLayout.X_AXIS));
        matricesInput = new matricesInput(2);

        matricesContainer.add(matricesInput);

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

        //initial sol
        solutionHeaderPanel = new JPanel();
        solutionHeaderPanel.setLayout(new BoxLayout(solutionHeaderPanel, BoxLayout.X_AXIS));
        initialSolutionHeader = new solutionHeader(2, true, "Initial solution : ");
        solutionHeaderPanel.add(initialSolutionHeader);

        //solution matrix
        solutionPanel = new JPanel(); //wrapper
        solutionPanel.setLayout(new FlowLayout());
        solutionMatrix = new solutionHeader(2, false, "Solution : ");
        solutionPanel.add(solutionMatrix);
        this.add(solutionPanel);

        //method chooser
        JPanel methodList = new JPanel(new FlowLayout());
        methodList.setPreferredSize(new Dimension(0, 30));
        methodList.add(new JLabel("Method: "));

        String[] methods = {"Jacobi", "Gauss-Seidel"};
        method = new JComboBox<>(methods);
        methodList.add(method, BorderLayout.CENTER);

        //iterations
        JPanel iterationsPanel = new JPanel();
        iterationsPanel.setLayout(new FlowLayout());
        iterationsPanel.setPreferredSize(new Dimension(100, 30));
        iterations = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        iterationsPanel.add(new JLabel("Iterations : "));
        iterationsPanel.add(iterations);

        //error
        JPanel errorPanel = new JPanel(new FlowLayout());
        errorPanel.add(new JLabel("Error : "));
        error = new JTextField();
        errorPanel.add(error);
        error.setPreferredSize(new Dimension(150, 25));

        //solve btn



        this.add(solutionHeaderPanel);
        this.add(iterationsPanel);
        this.add(errorPanel);
        this.add(methodList);
        JButton solveBtn = new JButton("Solve");
        methodList.add(solveBtn);
        solveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solve();
            }
        });
        this.add(solutionPanel);
    }

    public void redraw(int newDim)
    {
        matricesContainer.remove(matricesInput);
        matricesInput = new matricesInput(newDim);
        matricesContainer.add(matricesInput);

        solutionHeaderPanel.remove(initialSolutionHeader);
        initialSolutionHeader = new solutionHeader(newDim, true, "Initial solution : ");
        solutionHeaderPanel.add(initialSolutionHeader);

        solutionPanel.remove(solutionMatrix);
        solutionMatrix = new solutionHeader(newDim, false, "Solution : ");
        solutionPanel.add(solutionMatrix);

        this.updateUI();
    }

    public void solve()
    {
        double[] initialGuess;
        double error;
        int iterations;
        try
        {
            iterations = (int) this.iterations.getValue();
            error = Double.parseDouble(this.error.getText());
            initialGuess = initialSolutionHeader.getValues();


            if(((String)method.getSelectedItem()).equals("Jacobi"))
            {
                double[] res = JacobiMethod.solve(matricesInput.getA(), matricesInput.getB(),initialGuess,error,iterations);
                solutionMatrix.setValues(res);
            }
            else
            {
                double[] res = GaussSeidleMethod.solve(matricesInput.getA(), matricesInput.getB(),initialGuess,error,iterations);
                solutionMatrix.setValues(res);            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Warning: Check your input", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
