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


    public solutionHeader initialSolutionHeader;
    public JPanel solutionHeaderPanel;
    public JSpinner iterations;

    public solutionHeader solution;
    public JPanel solutionPanel;

    public secant()
    {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        secant app = this;

        equationsPanel = new JPanel();
        equationsPanel.setLayout(new BoxLayout(equationsPanel, BoxLayout.Y_AXIS));
        equationsInput = new equationsInput(1);


        solutionHeaderPanel = new JPanel();
        solutionHeaderPanel.setLayout(new BoxLayout(solutionHeaderPanel, BoxLayout.X_AXIS));
        initialSolutionHeader = new solutionHeader(2, true, "Initial solutions: ");
        solutionHeaderPanel.add(initialSolutionHeader);

        solutionPanel = new JPanel();
        solutionPanel.setLayout(new BoxLayout(solutionPanel, BoxLayout.X_AXIS));
        solution = new solutionHeader(1, false, "Solution : ");
        solutionPanel.add(solution);

        equationsPanel.add(equationsInput);


        //add dimensions chooser
        JPanel dimensionsChooser = new JPanel(new FlowLayout());
        dimensionsChooser.setPreferredSize(new Dimension(100, 30));

        SpinnerNumberModel model = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
        JSpinner spinner = new JSpinner(model);
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinner);
        spinner.setEditor(editor);
        // Add a change listener to the spinner to print the selected value
        spinner.addChangeListener(
                new ChangeListener() {
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


        //solve button
        JButton solve = new JButton("Solve");
        solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solve();
            }
        });

        this.add(equationsPanel);
        this.add(dimensionsChooser);
        this.add(solutionHeaderPanel);

        this.add(iterationsPanel);
        this.add(solutionPanel);
        this.add(solve);
    }

    public void redraw(int newDimensions)
    {
        this.equationsPanel.remove(this.equationsInput);
        this.equationsInput = new equationsInput(newDimensions);
        this.equationsPanel.add(this.equationsInput);

        solutionHeaderPanel.remove(initialSolutionHeader);
        initialSolutionHeader = new solutionHeader(newDimensions, true, "Initial solutions : ");
        solutionHeaderPanel.add(initialSolutionHeader);

        solutionPanel.remove(solution);
        solution = new solutionHeader(newDimensions, false, "Solution : ");
        solutionPanel.add(solution);
        updateUI();
    }

    public void solve()
    {
        //TODO: check all fields are filled
        Expression[] expressions;
        double[] initialGuess;
        int iterations;
        try
        {
            expressions = equationsInput.parseExpressions();
            initialGuess = initialSolutionHeader.getValues();

            iterations = (int) this.iterations.getValue();
            try
            {
                SecantMethod secantMethod = new SecantMethod(expressions);
                double[] solution = secantMethod.solve(initialGuess[0], initialGuess[1], iterations);
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