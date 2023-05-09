package GUI.Componenets;

import javax.swing.*;
import java.awt.*;

public class matrix extends JPanel
{
    public int lines, columns;
    public JTextField[][] cells;

    public matrix(int lines, int columns)
    {
        this.lines = lines;
        this.columns = columns;
        this.cells = new JTextField[lines][columns];

        this.setLayout(new GridLayout(lines, columns));

        //append cells
        for(int line=0; line<lines; line++)
        {
            for(int column=0; column<columns; column++)
            {
                cells[line][column] = new JTextField();
                this.add(cells[line][column]);
                cells[line][column].setEnabled(false);
            }
        }
    }

    public double[][] getMatrix()
    {
        double[][] res = new double[lines][columns];
        for(int line=0; line<lines; line++)
            for(int column=0; column<columns; column++)
                res[line][column] = Double.parseDouble(cells[line][column].getText());
        return res;
    }

    public void fillMatrix(double[][] values)
    {
        for(int line=0; line<values.length; line++)
            for(int column=0; column<values[line].length; column++)
                cells[line][column].setText(""+values[line][column]);
    }
}
