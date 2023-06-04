package GUI;


import GUI.Componenets.*;
import net.objecthunter.exp4j.Expression;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSpinner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import NonlinearEquations.SecantMethod;

public class secant extends JPanel
{
    public GUI.Componenets.equationsInput equationsInput;
    public JPanel equationsPanel;


    public solutionHeader initialSolutionHeader1;
    public solutionHeader initialSolutionHeader2;
    public JPanel solutionHeaderPanel1;
    public JPanel solutionHeaderPanel2;

    public JSpinner iterations;
    public JTextField error;

    public solutionHeader solution;
    public JPanel solutionPanel;

    public secant()
    {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        secant app = this;

        equationsPanel = new JPanel();
        equationsPanel.setLayout(new BoxLayout(equationsPanel, BoxLayout.Y_AXIS));
        equationsInput = new equationsInput(1);


        solutionHeaderPanel1 = new JPanel();
        solutionHeaderPanel1.setLayout(new BoxLayout(solutionHeaderPanel1, BoxLayout.X_AXIS));
        initialSolutionHeader1 = new solutionHeader(1, true, "Initial solution 1 : ");
        solutionHeaderPanel1.add(initialSolutionHeader1);


        solutionHeaderPanel2 = new JPanel();
        solutionHeaderPanel2.setLayout(new BoxLayout(solutionHeaderPanel2, BoxLayout.X_AXIS));
        initialSolutionHeader2 = new solutionHeader(1, true, "Initial solution 2 : ");
        solutionHeaderPanel2.add(initialSolutionHeader2);

        solutionPanel = new JPanel();
        solutionPanel.setLayout(new BoxLayout(solutionPanel, BoxLayout.X_AXIS));
        solution = new solutionHeader(1, false, "Solution : ");
        solutionPanel.add(solution);

        equationsPanel.add(equationsInput);






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
        this.add(solutionHeaderPanel1);
        this.add(solutionHeaderPanel2);

        this.add(iterationsPanel);
        this.add(errorPanel);

        this.add(solutionPanel);
        this.add(solve);
    }



    public void solve()
    {
        //TODO: check all fields are filled
        Expression[] expression;
        double[] initialGuess1;
        double[] initialGuess2;
        double error;
        int iterations;
        try
        {
            expression = equationsInput.parseExpressions();
            initialGuess1 = initialSolutionHeader1.getValues();
            initialGuess2 = initialSolutionHeader2.getValues();
            error = Double.parseDouble(this.error.getText());
            iterations = (int) this.iterations.getValue();
            try
            {
                double[] solution = new double[1];

                solution[0] = SecantMethod.solve(expression[0],initialGuess1[0], initialGuess2[0],iterations,error);

                this.solution.setValues(solution);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, "Secant method diverges for this config");
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "invalid input");
        }
    }
}