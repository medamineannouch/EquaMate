package GUI;

import GUI.Componenets.matricesInput;
import GUI.Componenets.matrix;
import GUI.Componenets.solutionHeader;
import Methods.linearSystems.Cholesky;
import Methods.linearSystems.luDecomposition;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class luLlt extends JPanel
{
    public matricesInput matricesInput;
    public JPanel matricesContainer; //a wrapper for the above component, used to preserve the old place
    public solutionHeader solutionMatrix;
    public JPanel solutionPanel;
    public matrix L, U;
    public JPanel lPanel, uPanel;

    public JComboBox<String> method;

    public luLlt()
    {
        luLlt app = this; //pointer for listeners
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

        String[] methods = {"LU", "Cholesky"};
        method = new JComboBox<>(methods);
        methodList.add(method, BorderLayout.CENTER);
        //Add Items Selection
        this.add(methodList);

        //solve btn
        JPanel pnl = new JPanel(new FlowLayout());
        JButton solveBtn = new JButton("Solve");
        pnl.add(solveBtn);

        this.add(pnl);
        solveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solve();
            }
        });

        //L & U matrices
        //panel wrapper

        JPanel luPanel = new JPanel();
        luPanel.setLayout(new BoxLayout(luPanel, BoxLayout.X_AXIS));
        luPanel.add(new JLabel("L: "));

        lPanel = new JPanel();
        lPanel.setLayout(new BoxLayout(lPanel, BoxLayout.X_AXIS));
        L = new matrix(2, 2);
        lPanel.add(L);
        luPanel.add(lPanel);

        luPanel.add(new JLabel("U/Lt: "));

        uPanel = new JPanel();
        uPanel.setLayout(new BoxLayout(uPanel, BoxLayout.X_AXIS));
        U = new matrix(2, 2);
        uPanel.add(U);
        luPanel.add(uPanel);

        this.add(luPanel);
        luPanel.setPreferredSize(new Dimension(100, 150));

    }

    public void redraw(int newDim)
    {
        matricesContainer.remove(matricesInput);
        matricesInput = new matricesInput(newDim);
        matricesContainer.add(matricesInput);

        solutionPanel.remove(solutionMatrix);
        solutionMatrix = new solutionHeader(newDim, false, "Solution : ");
        solutionPanel.add(solutionMatrix);


        lPanel.remove(L);
        L = new matrix(newDim, newDim);
        lPanel.add(L);

        uPanel.remove(U);
        U = new matrix(newDim, newDim);
        uPanel.add(U);

        this.updateUI();
    }

    public void solve()
    {
        try
        {
            if(((String)method.getSelectedItem()).equals("LU"))
            {
                System.out.println("LU");
                HashMap<String, Object> res = luDecomposition.LU(matricesInput.getA(), matricesInput.getB());
                solutionMatrix.setValues((double[]) res.get("result"));

                //set L and U
                L.fillMatrix((double[][]) res.get("L"));
                U.fillMatrix((double[][]) res.get("U"));
            }
            else{

                System.out.println("Cholesky");
                Cholesky A = new Cholesky(matricesInput.getA().length);
                A.setMatrix(matricesInput.getA());
                A.setSecMember(matricesInput.getB());
                //set solution
                solutionMatrix.setValues(A.choleskyV2());

                //set L and Lt
                L.fillMatrix(A.choleskyProcess());
                U.fillMatrix(A.Ltranspose());

            }

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Warning: Check your input", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
