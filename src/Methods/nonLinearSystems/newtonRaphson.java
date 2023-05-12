package Methods.nonLinearSystems;

import Methods.linearSystems.gaussianElimination;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.HashMap;

import static Methods.utilities.matrixUtilities.MatrixUtilities.norm;

public class newtonRaphson
{
    public static double[][] substituteJacobie(Expression[][] jacobi, double[] values)
    {
        double[][] res = new double[jacobi.length][jacobi.length];
        HashMap<String, Double> variableValues = new HashMap<>();
        for(int i=0; i<jacobi.length; i++) variableValues.put("x"+(i+1), values[i]);
        for(int line=0; line< jacobi.length; line++)
        {
            for(int column=0; column< jacobi.length; column++)
            {
                jacobi[line][column].setVariables(variableValues);
                res[line][column] = jacobi[line][column].evaluate();
            }
        }
        return res;
    }

    public static double[] minusF(Expression[] F, double[] values)
    {
        double[] res = new double[F.length];
        HashMap<String, Double> variableValues = new HashMap<>();
        for(int i=0; i<F.length; i++) variableValues.put("x"+(i+1), values[i]);
        for(int i=0; i<F.length; i++)
        {
            F[i].setVariables(variableValues);
            res[i] = -F[i].evaluate();
        }
        return res;
    }

    public static double[] newton(Expression[] F, double[] guess, Expression[][] jacobi, int iteration, double error) throws Exception
    {
        if(iteration == 0) return guess;

        double[] newGuess = new double[F.length];
        //find h
        double[] h = gaussianElimination.gaussianEliminationMathod(substituteJacobie(jacobi, guess), minusF(F, guess));
        for(int i=0; i<h.length; i++) newGuess[i] = guess[i] + h[i];
        if(norm(newGuess, guess) <= error) return newGuess;
        else if(iteration == 1) throw new Exception("Newton-Raphson method diverges");
        else return newton(F, newGuess, jacobi, iteration - 1, error);
    }

    public static void main(String[] args) throws Exception {
        Expression f1 = new ExpressionBuilder("-x1^3+x2").variables("x1", "x2").build(); // params -> - Math.pow(params[0], 3) + params[1];
        Expression f2 = new ExpressionBuilder("x1^2 + x2^2 - 1").variables("x1", "x2").build();//  params -> Math.pow(params[0], 2) + Math.pow(params[1], 2) - 1;

        Expression[] f = {f1, f2};
        Expression[][] jacobi = {
                {new ExpressionBuilder("-3 * x1^2").variables("x1", "x2").build(), new ExpressionBuilder("1").variables("x1", "x2").build()},
                {new ExpressionBuilder("2*x1").variables("x1", "x2").build(), new ExpressionBuilder("2 * x2").variables("x1", "x2").build()}
        };

        double[] res = newton(f, new double[] {1.0, 2.0}, jacobi, 10, 0.0000001);
        for (double re : res) System.out.print(re + " ");
    }
}
