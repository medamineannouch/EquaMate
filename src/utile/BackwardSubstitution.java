package utile;

public class BackwardSubstitution {
    public static void solve(double[][] LU, double[] y, double[] x) {
        int n = LU.length;

        // Solve Ux = y
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum += LU[i][j] * x[j];
            }
            x[i] = (y[i] - sum) / LU[i][i];
        }
    }
}

