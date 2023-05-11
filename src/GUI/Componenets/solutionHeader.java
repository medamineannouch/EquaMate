package GUI.Componenets;

import javax.swing.*;
import java.awt.*;

public class solutionHeader extends JPanel
{
    public int dimension;
    public JTextField[] solutionMatrix;
    public JPanel solutionMatrixPanel;

    public solutionHeader(int dim, boolean isEnabled, String label)
    {
        this.setLayout(new FlowLayout());
        this.add(new JLabel(label));
        this.dimension = dim;
        solutionMatrix = new JTextField[dim];
        solutionMatrixPanel = new JPanel();

        //solution grid
        solutionMatrixPanel.setLayout(new GridLayout(0, dim));
        for(int line=0; line<dim; line++)
        {
            solutionMatrix[line] = new JTextField();
            solutionMatrix[line].setEnabled(isEnabled);
            solutionMatrixPanel.add(solutionMatrix[line]);
        }
        this.add(solutionMatrixPanel);
        solutionMatrixPanel.setPreferredSize(new Dimension(500, 30));
    }

    //assumes data.length = dimension
    public void setValues(double[] data)
    {
        for(int line=0; line<dimension; line++)
        {
            solutionMatrix[line].setText(""+data[line]);
        }
    }

    public double[] getValues()
    {
        double[] res = new double[dimension];
        for(int i=0; i<dimension; i++) res[i] = Double.parseDouble(solutionMatrix[i].getText());
        return res;
    }
}
