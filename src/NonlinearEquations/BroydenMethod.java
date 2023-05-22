package NonlinearEquations;

import utile.*;
import utile.LUDecomposition;
import utile.Operations;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.util.function.Function;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
public class BroydenMethod{

    public static double[] solve(Expression[] F, double[] x0, Expression[][] B0, int maxIterations, double epsilon) {
        int n = x0.length;
        double[] x = x0.clone();
        double[][] B = evaluateExpression2(B0);

        int iteration = 0;
        double error = Double.POSITIVE_INFINITY;

        while (iteration < maxIterations && error > epsilon) {
            double[] s = linearSolve(B, Operations.negate(evaluateExpression(F, x)));
            double[] xNext = Operations.add(x, s);
            double[] y = Operations.subtract(evaluateExpression(F, xNext), evaluateExpression(F, x));
            double[] z = linearSolve(B, y);
            double[] u = Operations.subtract(s, z);
            double[] v = Operations.subtract(xNext, x);
            double[][] BNext = Operations.add(B, Operations.outerProduct(u, v));

            error = computeError(xNext, x);
            x = xNext.clone();
            B = BNext.clone();
            iteration++;
        }

        if (iteration >= maxIterations) {
            System.out.println("Maximum number of iterations reached.");
        }

        return x;
    }

    private static double[] evaluateExpression(Expression[] F, double[] x) {
        double[] result = new double[F.length];
        for (int i = 0; i < F.length; i++) {
            result[i] = F[i].setVariable("x", x[0]).setVariable("y", x[1]).setVariable("z", x[2]).evaluate();
        }
        return result;
    }

    private static double[] linearSolve(double[][] A, double[] b) {
        int n = A.length;
        double[] x = new double[n];
        double[][] LU = LUDecomposition.decompose(A);

        double[] y = ForwardSubstitution.solve(LU, b);
        BackwardSubstitution.solve(LU, y, x);

        return x;
    }

    public static double[][] evaluateExpression2(Expression[][] expressions) {
        int rows = expressions.length;
        int columns = expressions[0].length;
        double[][] results = new double[rows][columns];

        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("JavaScript");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                String expression = expressions[i][j].toString();
                try {
                    Object result = engine.eval(expression);
                    if (result instanceof Number) {
                        results[i][j] = ((Number) result).doubleValue();
                    } else {
                        // Handle non-numeric result if needed
                    }
                } catch (ScriptException e) {
                    // Handle script evaluation error if needed
                }
            }
        }

        return results;
    }

    private static double computeError(double[] x, double[] y) {
        double error = 0;
        for (int i = 0; i < x.length; i++) {
            error = Math.max(error, Math.abs(x[i] - y[i]));
        }
        return error;
    }
}
