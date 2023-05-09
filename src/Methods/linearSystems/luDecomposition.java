package Methods.linearSystems;

import java.util.HashMap;

public class luDecomposition
{
    public static HashMap<String, Object> LU(double[][] A, double[] B) {
        double[][] L = new double[A.length][A.length];
        double[][] U = new double[A.length][A.length];

        // initialise L
        for (int l = 0; l < A.length; l++) {
            for (int c = 0; c < A.length; c++) {
                if (l == c)
                    L[l][c] = 1;
                else
                    L[l][c] = 0;
            }
        }

        // copy A into U
        for (int l = 0; l < A.length; l++) {
            for (int c = 0; c < A.length; c++) {
                U[l][c] = A[l][c];
            }
        }

        // construct U & L
        for (int column = 0; column < A.length; column++) {
            for (int line = column + 1; line < A.length; line++) {
                L[line][column] = -1 * MatrixUtilities.eliminateEntry(U, null, line, column);
            }
        }

        double[] c = MatrixUtilities.backSolveReversed(L, B);

        double[] result =  MatrixUtilities.backSolve(U, c);
        HashMap<String , Object> res = new HashMap<>();
        res.put("result", result);
        res.put("L", L);
        res.put("U", U);
        return res;
    }
}