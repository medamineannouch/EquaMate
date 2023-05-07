package IterativeMethods;

public class MatrixChecker {

    public static boolean isDiagonallyDominant(double[][] A) {
        int n = A.length;

        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    sum += Math.abs(A[i][j]);
                }
            }
            if (Math.abs(A[i][i]) <= sum) {
                return false;
            }
        }

        return true;
    }
}

