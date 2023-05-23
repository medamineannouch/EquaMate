package GUI;

import GUI.Componenets.equationsInput;
import GUI.Componenets.solutionHeader;
import NonlinearEquations.RecherchDichotomique;
import net.objecthunter.exp4j.Expression;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RechercheDichotomiqueGui extends JPanel {
    public GUI.Componenets.equationsInput equationsInput;
    public JPanel equationsPanel;
    public solutionHeader borneInf,borneSup;
    public JPanel solutionHeaderPanel,solutionHeaderPanel2;
    public JTextField error;
    public solutionHeader solution;
    public JPanel solutionPanel;
    public RechercheDichotomiqueGui()
    {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        RechercheDichotomiqueGui app = this;

        equationsPanel = new JPanel();
        equationsPanel.setLayout(new BoxLayout(equationsPanel, BoxLayout.Y_AXIS));
        equationsInput = new equationsInput(1);

        solutionHeaderPanel = new JPanel();
        solutionHeaderPanel.setLayout(new BoxLayout(solutionHeaderPanel, BoxLayout.X_AXIS));
        borneInf = new solutionHeader(1, true, "Borne Inf : ");
        solutionHeaderPanel.add(borneInf);

        solutionHeaderPanel2 = new JPanel();
        solutionHeaderPanel.setLayout(new BoxLayout(solutionHeaderPanel, BoxLayout.X_AXIS));
        borneSup = new solutionHeader(1, true, "Borne Sup : ");
        solutionHeaderPanel2.add(borneSup);

        solutionPanel = new JPanel();
        solutionPanel.setLayout(new BoxLayout(solutionPanel, BoxLayout.X_AXIS));
        solution = new solutionHeader(1, false, "Solution : ");
        solutionPanel.add(solution);

        equationsPanel.add(equationsInput);

        //No dimensions chooser

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
        //No iterations


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
        this.add(solutionHeaderPanel);
        this.add(solutionHeaderPanel2);
        this.add(errorPanel);
        this.add(solve);
        this.add(solutionPanel);
    }

    public void redraw(int newDimensions)
    {
        this.equationsPanel.remove(this.equationsInput);
        this.equationsInput = new equationsInput(newDimensions);
        this.equationsPanel.add(this.equationsInput);

        solutionHeaderPanel.remove(borneInf);
        borneInf = new solutionHeader(newDimensions, true, "Borne inf : ");
        solutionHeaderPanel.add(borneInf);

        solutionHeaderPanel2.remove(borneInf);
        borneSup = new solutionHeader(newDimensions, true, "Borne sup : ");
        solutionHeaderPanel2.add(borneSup);

        solutionPanel.remove(solution);
        solution = new solutionHeader(newDimensions, false, "Solution : ");
        solutionPanel.add(solution);

        updateUI();
    }

    public void solve()
    {
        //TODO: check all fields are filled
        Expression[] expressions;
        double[] inf;
        double[] sup;
        double error;
        try
        {
            expressions = equationsInput.parseExpressions();
            inf = borneInf.getValues();
            sup = borneSup.getValues();
            error = Double.parseDouble(this.error.getText());
            RecherchDichotomique R = new RecherchDichotomique(error,inf[0],sup[0]);
            try
            {
                double[] solution = new double[1];
                solution[0] = R.dichotomyV2(expressions[0]);
                this.solution.setValues(solution);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, "Fixed Point method diverges for this config");
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "invalid input");
        }



    }
}
