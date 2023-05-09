package IterativeMethods;

public class JacobiMethod {
    public static double[] solve(double[][] A, double[] b, double[] x0, double epsilon, int maxIterations) {
        int n = A.length;
        double[] x = new double[n];

        int iteration = 0;
        double error = Double.POSITIVE_INFINITY;
        while (iteration < maxIterations && error > epsilon) {
            for (int i = 0; i < n; i++) {
                double sum = 0;
                for (int j = 1; j < n; j++) {
                    if (j != i) {
                        sum += A[i][j] * x0[j];
                    }
                }
                x[i] = (b[i] - sum) / A[i][i];
            }

            error = computeError(x, x0);
            x0 = x.clone();
            iteration++;
        }

        if (iteration >= maxIterations) {
            System.out.println("Maximum number of iterations reached.");
        }

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
