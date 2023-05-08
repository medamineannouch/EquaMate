package utile;

public class ForwardSubstitution {
    public static double[] solve(double[][] L, double[] b) {
        int n = b.length;
        double[] x = new double[n];

        // Solve for x[0]
        x[0] = b[0] / L[0][0];

        // Solve for remaining entries of x
        for (int i = 1; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < i; j++) {
                sum += L[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / L[i][i];
        }

        return x;
    }
}


