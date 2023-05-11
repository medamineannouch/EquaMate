package Methods.linearSystems;

import Methods.utilities.matrixUtilities;

public class gaussianElimination
{
    public static double[] gaussianEliminationMathod(double[][] A, double[] B) {
        //elimination phase
        for (int column = 0; column < A.length; column++) {
            for (int line = column + 1; line < A.length; line++) {
                matrixUtilities.MatrixUtilities.eliminateEntry(A, B, line, column);
            }
        }
        //back-solving phase
        return matrixUtilities.MatrixUtilities.backSolve(A, B);
    }
}
