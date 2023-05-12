package Methods.nonLinearSystems;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.HashMap;

import static Methods.utilities.matrixUtilities.MatrixUtilities.norm;

public class fixedPoint
{
//    public static double fixedPointIteration(function_def g, double guess, int iteration, double error) throws Exception
//    {
//        if(iteration == 0) return guess;
//
//        double newGuess = g.function_(guess);
//        if(Math.abs(newGuess - guess) <= error) return newGuess;
//        else if(iteration == 1) throw new Exception("FIP diverges");
//        else return fixedPointIteration(g, newGuess, iteration - 1, error);
//    }

    public static double[] fixedPointIteration(Expression[] g, double[] guess, int iteration, double error) throws Exception
    {
        if(iteration == 0) return guess;

        double[] newGuess = new double[guess.length];
        HashMap<String, Double> variableValues = new HashMap<>();
        for(int j=0; j<guess.length; j++) variableValues.put("x"+(j+1), guess[j]);

        for(int i=0; i<newGuess.length; i++)
        {
            //set up expressions for evaluation
            g[i].setVariables(variableValues); //x1 x2 ...
            newGuess[i] = g[i].evaluate();
        }
        if(norm(guess, newGuess) <= error) return newGuess;
        else if(iteration == 1) throw new Exception("FIP diverges");
        else return fixedPointIteration(g, newGuess, iteration - 1, error);
    }

    public static void main(String[] args) {
        try
        {
            Expression g1 = new ExpressionBuilder("-0.02 * x1^2 - 0.02 * x2^2 - 0.02 * x3^2 + 4")
                    .variables("x1", "x2", "x3")
                    .build();
            Expression g2 = new ExpressionBuilder("-0.05 * x1^2 - 0.05 * x3^2 + 2.5")
                    .variables("x1", "x2", "x3")
                    .build();
            Expression g3 = new ExpressionBuilder("0.025 * x1^2 + 0.025 * x2^2 - 1.875")
                    .variables("x1", "x2", "x3")
                    .build();

            Expression[] g = {g1, g2, g3};
            double[] guess = {0, 0, 0};
            double[] finalRes = fixedPointIteration(g, guess, 100, 1.e-4);
            System.out.println(finalRes[0] + " " +finalRes[1]+" "+finalRes[2]);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
