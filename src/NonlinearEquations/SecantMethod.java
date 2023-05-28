package NonlinearEquations;

import net.objecthunter.exp4j.Expression;


/*

public class SecantMethod {
    public static double solve(Function<Double, Double> f, double x0, double x1, double epsilon, int maxIterations) {
        double fx0 = f.apply(x0);
        double fx1 = f.apply(x1);
        double x = 0;
        double fx=0;
        int iteration = 0;
        double error = Double.POSITIVE_INFINITY;
        while (iteration < maxIterations && error > epsilon) {
            x = x1 - fx1 * (x1 - x0) / (fx1 - fx0);
            fx = f.apply(x);
            error = Math.abs(x - x1);
            x0 = x1;
            fx0 = fx1;
            x1 = x;
            fx1 = fx;
            iteration++;
        }

        if (iteration >= maxIterations) {
            System.out.println("Maximum number of iterations reached.");
        }

        return x;
    }
}*/


public class SecantMethod {
    private final double epsilon; // Convergence criterion
    private final int maxIterations; // Maximum number of iterations
    private final Expression equation; // Equation to solve

    public SecantMethod(Expression equation, double epsilon, int maxIterations) {
        this.equation = equation;
        this.epsilon = epsilon;
        this.maxIterations = maxIterations;
    }

    public double solveequation(double initialGuess1, double initialGuess2) {
        double x = initialGuess1;
        double xPrev = initialGuess2;

        for (int i = 0; i < maxIterations; i++) {
            double dx = equation.setVariable("x", x).evaluate();
            double xNext = x - dx;

            if (Math.abs(dx) < epsilon) {
                return xNext;
            }

            xPrev = x;
            x = xNext;
        }

        throw new IllegalStateException("The method did not converge within the maximum number of iterations.");
    }



}



