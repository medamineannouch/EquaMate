package GUI;

import GUI.Componenets.equationsInput;
import GUI.Componenets.jacobi;
import GUI.Componenets.solutionHeader;
import NonlinearEquations.BroydenMethod;
import net.objecthunter.exp4j.Expression;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Broyden extends JPanel
{
    public GUI.Componenets.equationsInput equationsInput;
    public JPanel equationsPanel;
    public solutionHeader initialSolutionHeader;
    public JPanel solutionHeaderPanel;
    public JSpinner iterations;
    public JTextField error;
    public solutionHeader solution;
    public JPanel solutionPanel;
    public GUI.Componenets.jacobi jacobiappr;
    public JPanel jacobiPanel;

    public Broyden()
    {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Broyden app = this;

        equationsPanel = new JPanel();
        equationsPanel.setLayout(new BoxLayout(equationsPanel, BoxLayout.Y_AXIS));
        equationsInput = new equationsInput(1);

        solutionHeaderPanel = new JPanel();
        solutionHeaderPanel.setLayout(new BoxLayout(solutionHeaderPanel, BoxLayout.X_AXIS));
        initialSolutionHeader = new solutionHeader(1, true, "Initial solution : ");
        solutionHeaderPanel.add(initialSolutionHeader);

        solutionPanel = new JPanel();
        solutionPanel.setLayout(new BoxLayout(solutionPanel, BoxLayout.X_AXIS));
        solution = new solutionHeader(1, false, "Solution : ");
        solutionPanel.add(solution);

        equationsPanel.add(equationsInput);

        //jacobi
        jacobiappr = new jacobi(1);
        jacobiPanel = new JPanel();
        jacobiPanel.setLayout(new BoxLayout(jacobiPanel, BoxLayout.X_AXIS));
        jacobiPanel.add(new JLabel("Approximation de la matrice Jacobienne : "));
        jacobiPanel.add(jacobiappr);

        //add dimensions chooser
        JPanel dimensionsChooser = new JPanel(new FlowLayout());
        dimensionsChooser.setPreferredSize(new Dimension(100, 30));

        SpinnerNumberModel model = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
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
        dimensionsChooser.add(new JLabel("Dimension : "));
        dimensionsChooser.add(spinner);

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

        //solve button
        JButton solve = new JButton("Solve");
        solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solve();
            }
        });

        this.add(equationsPanel);
        this.add(jacobiPanel);
        this.add(dimensionsChooser);
        this.add(solutionHeaderPanel);
        this.add(iterationsPanel);
        this.add(errorPanel);
        this.add(solutionPanel);
        this.add(solve);
    }

    public void redraw(int newDimensions)
    {
        this.equationsPanel.remove(this.equationsInput);
        this.equationsInput = new equationsInput(newDimensions);
        this.equationsPanel.add(this.equationsInput);

        solutionHeaderPanel.remove(initialSolutionHeader);
        initialSolutionHeader = new solutionHeader(newDimensions, true, "Initial solution : ");
        solutionHeaderPanel.add(initialSolutionHeader);

        solutionPanel.remove(solution);
        solution = new solutionHeader(newDimensions, false, "Solution : ");
        solutionPanel.add(solution);

        jacobiPanel.remove(jacobiappr);
        jacobiappr = new jacobi(newDimensions);
        jacobiPanel.add(jacobiappr);

        updateUI();
    }

    public void solve()
    {
        //TODO: check all fields are filled
        Expression[] expressions;
        Expression[][] jacobi;
        double[] initialGuess;
        double error;
        int iterations;
        try
        {
            expressions = equationsInput.parseExpressions();
            jacobi = this.jacobiappr.parseExpressions();
            initialGuess = initialSolutionHeader.getValues();
            iterations = (int) this.iterations.getValue();
            error = Double.parseDouble(this.error.getText());
            try
            {
                double[] solution = BroydenMethod.solve(expressions, initialGuess, jacobi, iterations, error);
                this.solution.setValues(solution);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, "Broyden method diverges for this config");
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "invalid input");
        }
    }
}

