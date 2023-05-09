package GUI.Componenets;

import javax.swing.*;
import java.awt.*;

public class solutionHeader extends JPanel
{
    public int dimension;
    public JTextField[] solutionMatrix;
    public JPanel solutionMatrixPanel;

    public solutionHeader(int dim)
    {
        this.setLayout(new FlowLayout());
        this.add(new JLabel("Solution : "));
        this.dimension = dim;
        solutionMatrix = new JTextField[dim];
        solutionMatrixPanel = new JPanel();

        //solution grid
        solutionMatrixPanel.setLayout(new GridLayout(0, dim));
        for(int line=0; line<dim; line++)
        {
            solutionMatrix[line] = new JTextField();
            solutionMatrix[line].setEnabled(false);
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
}
