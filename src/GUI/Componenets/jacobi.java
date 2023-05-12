package GUI.Componenets;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import javax.swing.*;
import java.awt.*;

public class jacobi extends JPanel
{
    public int dimension;
    public JTextField[][] cells;

    public jacobi(int dimension)
    {
        this.dimension = dimension;
        this.cells = new JTextField[dimension][dimension];

        this.setLayout(new GridLayout(dimension, dimension));
        for(int i=0; i<dimension; i++)
        {
            for(int j=0; j<dimension; j++)
            {
                cells[i][j] = new JTextField();
                this.add(cells[i][j]);
            }
        }
    }

    public Expression[][] parseExpressions()
    {
        Expression[][] res = new Expression[dimension][dimension];
        String[] variables = new String[dimension];
        for(int i=0; i<dimension; i++) variables[i] = "x"+(i+1);
        for(int i=0; i<dimension; i++)
        {
            for(int j=0; j<dimension; j++)
            {
                res[i][j] = new ExpressionBuilder(cells[i][j].getText())
                        .variables(variables)
                        .build();
            }
        }
        return res;
    }
}
