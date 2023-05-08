package NonlinearEquations;

import utile.*;
import utile.LUDecomposition;
import utile.Operations;

import java.util.function.Function;
public class BroydenMethod{
    public static double[] solve(Function<double[], double[]> f, double[] x0, double[][] B0, double epsilon, int maxIterations) {
        int n = x0.length;
        double[] x = x0.clone();
        double[][] B = B0.clone();

        int iteration = 0;
        double error = Double.POSITIVE_INFINITY;

        while (iteration < maxIterations && error > epsilon) {
            double[] s = linearSolve(B, Operations.negate(f.apply(x)));
            double[] xNext = Operations.add(x, s);
            double[] y = Operations.subtract(f.apply(xNext), f.apply(x));
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

    private static double[] linearSolve(double[][] A, double[] b) {
        int n = A.length;
        double[] x = new double[n];
        double[][] LU = LUDecomposition.decompose(A);

        double[] y = ForwardSubstitution.solve(LU, b);
        BackwardSubstitution.solve(LU, y, x);

        return x;
    }



    private static double computeError(double[] x, double[] y) {
        double error = 0;
        for (int i = 0; i < x.length; i++) {
            error = Math.max(error, Math.abs(x[i] - y[i]));
        }
        return error;
    }
}
