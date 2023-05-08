package utile;

public class LUDecomposition {
    private final int n;
    private final double[][] LU;
    private final int[] pivot;
    private boolean singular;

    public LUDecomposition(double[][] A) {
        n = A.length;
        LU = new double[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(A[i], 0, LU[i], 0, n);
        }
        pivot = new int[n];
        for (int i = 0; i < n; i++) {
            pivot[i] = i;
        }

        for (int k = 0; k < n - 1; k++) {
            int p = findPivot(k);
            swapRows(k, p);
            if (LU[k][k] == 0.0) {
                singular = true;
                return;
            }
            for (int i = k + 1; i < n; i++) {
                LU[i][k] /= LU[k][k];
                for (int j = k + 1; j < n; j++) {
                    LU[i][j] -= LU[i][k] * LU[k][j];
                }
            }
        }

        if (LU[n - 1][n - 1] == 0.0) {
            singular = true;
        }
    }

    private int findPivot(int k) {
        int p = k;
        for (int i = k + 1; i < n; i++) {
            if (Math.abs(LU[i][k]) > Math.abs(LU[p][k])) {
                p = i;
            }
        }
        return p;
    }

    private void swapRows(int i, int j) {
        double[] tmp = LU[i];
        LU[i] = LU[j];
        LU[j] = tmp;
        int t = pivot[i];
        pivot[i] = pivot[j];
        pivot[j] = t;
    }

    public double[][] getL() {
        double[][] L = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                L[i][j] = LU[i][j];
            }
            L[i][i] = 1.0;
        }
        return L;
    }

    public double[][] getU() {
        double[][] U = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                U[i][j] = LU[i][j];
            }
        }
        return U;
    }

    public int[] getPivot() {
        return pivot.clone();
    }

    public boolean isSingular() {
        return singular;
    }

    public static double[][] decompose(double[][] A) {
        LUDecomposition lu = new LUDecomposition(A);
        if (lu.isSingular()) {
            throw new IllegalArgumentException("Matrix is singular.");
        }
        return lu.LU;
    }
}
