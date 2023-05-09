package IterativeMethods;
import java.util.Scanner;
import utile.MatrixChecker;
import utile.MatrixPreconditioner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the size of the matrix: ");
        int n = input.nextInt();

        double[][] A = new double[n][n];
        double[] b = new double[n];
        double[] x0 = new double[n];
        double[] xJacobi = new double[n];
        double[] xGaussSeidel = new double[n];

        System.out.println("Enter the elements of the matrix A:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = input.nextDouble();
            }
        }

        System.out.println("Enter the elements of the vector b:");
        for (int i = 0; i < n; i++) {
            b[i] = input.nextDouble();
        }

        System.out.println("Enter the initial guess for x:");
        for (int i = 0; i < n; i++) {
            x0[i] = input.nextDouble();
        }

        System.out.print("Enter the maximum number of iterations: ");
        int maxIterations = input.nextInt();

        System.out.print("Enter the error tolerance: ");
        double epsilon = input.nextDouble();

        // Check if the matrix A is diagonally dominant
        if (!MatrixChecker.isDiagonallyDominant(A)) {
            System.out.println("The matrix A is not diagonally dominant. The Jacobi and Gauss-Seidel methods may not converge.");
            System.out.println("We are going to do matrix preconditioning for A using the diagonal scaling approach");
            // Precondition the matrix A
            double[][] B = MatrixPreconditioner.precondition(A);

            xJacobi = JacobiMethod.solve(B, b, x0, epsilon, maxIterations);

            xGaussSeidel = GaussSeidleMethod.solve(B, b, x0, epsilon, maxIterations);

        }
        else {
            xJacobi = JacobiMethod.solve(A, b, x0, epsilon, maxIterations);
            xGaussSeidel = GaussSeidleMethod.solve(A, b, x0, epsilon, maxIterations);
        }


        System.out.println("Solution using Jacobi method:");
        printVector(xJacobi);

        System.out.println("Solution using Gauss-Seidel method:");
        printVector(xGaussSeidel);
    }

    private static void printVector(double[] x) {
        for (double value : x) {
            System.out.printf("%.4f ", value);
        }
        System.out.println();
    }
}

