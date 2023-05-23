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
        private final Expression[] equations; // Array to store the system of equations

        public SecantMethod(Expression[] equations, double epsilon, int maxIterations) {
            this.equations = equations;
            this.epsilon = epsilon;
            this.maxIterations = maxIterations;
        }

        public double[] solveSystem(double[] initialGuess1, double[] initialGuess2) {
            int n = equations.length;
            if (initialGuess1.length != n || initialGuess2.length != n) {
                throw new IllegalArgumentException("Dimensions of initial guess vectors must match the number of equations.");
            }

            double[] x = initialGuess1.clone();
            double[] xPrev = initialGuess2.clone();

            for (int i = 0; i < maxIterations; i++) {
                double[] dx = new double[n];
                double[] xNext = x.clone();

                for (int j = 0; j < n; j++) {
                    Expression equation = equations[j].setVariable("x", x[j]).setVariable("xPrev", xPrev[j]);
                    dx[j] = equation.evaluate();
                    xNext[j] = x[j] - dx[j];
                }

                double maxDeltaX = Math.abs(dx[0]);
                for (int j = 1; j < n; j++) {
                    if (Math.abs(dx[j]) > maxDeltaX) {
                        maxDeltaX = Math.abs(dx[j]);
                    }
                }

                if (maxDeltaX < epsilon) {
                    return xNext;
                }

                xPrev = x;
                x = xNext;
            }

            throw new IllegalStateException("The method did not converge within the maximum number of iterations.");
        }


    private double evaluateEquations(double x) {
        double result = 0;

        for (Expression equation : equations) {
            equation.setVariable("x", x);
            result += equation.evaluate();
        }

        return result;
    }
}



