package GUI.Componenets;

import javax.swing.*;
import java.awt.*;

public class matrixInput extends JPanel
{
    //dimensions for both matrices
    public int dimension;
    //individual cell components, that contain data
    public JTextField[][] A;
    public JTextField[] B;

    public matrixInput(int dimension)
    {
        this.dimension = dimension;
        this.A = new JTextField[dimension][dimension];
        this.B = new JTextField[dimension];

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(new JLabel("A : "));

        //matrix A
        JPanel matrixA = new JPanel();
        matrixA.setLayout(new GridLayout(dimension, dimension));
        //push individual input cells
        for(int line=0; line<dimension; line++)
            for(int column=0; column<dimension; column++)
            {
                A[line][column] = new JTextField();
                matrixA.add(A[line][column]);
            }

        this.add(matrixA);

        this.add(new JLabel("B: "));

        //matrix B
        JPanel matrixB = new JPanel();
        matrixB.setLayout(new GridLayout(dimension, 0));
        for(int line=0; line<dimension; line++)
        {
            B[line] = new JTextField();
            matrixB.add(B[line]);
        }
        this.add(matrixB);
    }

    public double[][] getA()
    {
        double[][] A = new double[dimension][dimension];
        for(int line=0; line<dimension; line++)
            for(int column=0; column<dimension; column++)
                A[line][column] = Double.parseDouble(this.A[line][column].getText());
        return A;
    }

    public double[] getB()
    {
        double[] B = new double[dimension];
        for(int line=0; line<dimension; line++)
            B[line] = Double.parseDouble(this.B[line].getText());
        return B;
    }
}
