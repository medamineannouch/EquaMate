package utile;

public class MatrixPreconditioner {

    /**
     * Precondition a matrix A using diagonal scaling.
     *
     * @param A the matrix to be preconditioned
     * @return the preconditioned matrix
     */
    public static double[][] precondition(double[][] A) {
        int n = A.length;
        double[][] B = new double[n][n];

        // Compute the diagonal scaling matrix D
        double[] D = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0.0;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    sum += Math.abs(A[i][j]);
                }
            }
            if (A[i][i] <= sum) {
                D[i] = A[i][i];
            } else {
                D[i] = sum;
            }
        }

        // Precondition the matrix A
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    B[i][j] = 1.0;
                } else {
                    B[i][j] = -A[i][j] / D[i];
                }
            }
        }

        return B;
    }
}

