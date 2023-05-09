package utile;

public class Operations {
    public static double[] negate(double[] x) {
        int n = x.length;
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            y[i] = -x[i];
        }
        return y;
    }

    public static double[] add(double[] x, double[] y) {
        int n = x.length;
        double[] z = new double[n];
        for (int i = 0; i < n; i++) {
            z[i] = x[i] + y[i];
        }
        return z;
    }

    public static double[] subtract(double[] x, double[] y) {
        int n = x.length;
        double[] z = new double[n];
        for (int i = 0; i < n; i++) {
            z[i] = x[i] - y[i];
        }
        return z;
    }

    public static double[][] outerProduct(double[] x, double[] y) {
        int n = x.length;
        double[][] A = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = x[i] * y[j];
            }
        }
        return A;
    }

    public static double[][] add(double[][] A, double[][] B) {
        int n = A.length;
        int m = A[0].length;
        double[][] C = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }
}
// charaf was here
