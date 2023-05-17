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
    private static final double EPSILON = 1e-6; // Error tolerance

    private Expression[] equations; // Array to store the system of equations

    public SecantMethod(Expression[] equations) {
        this.equations = equations;
    }

    public double[] solve(double x0, double x1, int iterations) {
        double[] results = new double[equations.length];

        for (int i = 0; i < iterations; i++) {
            double fx0 = evaluateEquations(x0);
            double fx1 = evaluateEquations(x1);

            if (Math.abs(fx1 - fx0) < EPSILON) {
                break; // Convergence achieved
            }

            double x2 = x1 - ((fx1 * (x1 - x0)) / (fx1 - fx0));

            x0 = x1;
            x1 = x2;
        }

        for (int i = 0; i < equations.length; i++) {
            results[i] = equations[i].setVariable("x", x1).evaluate();
        }

        return results;
    }

    private double evaluateEquations(double x) {
        double result = 0;

        for (Expression equation : equations) {
            equation.setVariable("x", x);
            result += equation.evaluate();
        }

        return result;
    }
    //test
    /* public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of equations in the system: ");
        int numEquations = scanner.nextInt();

        Expression[] equations = new Expression[numEquations];

        System.out.println("Enter the equations in the form of 'f(x) = 0':");
        for (int i = 0; i < numEquations; i++) {
            System.out.print("Equation " + (i + 1) + ": ");
            String equation = scanner.next();
            equations[i] = new ExpressionBuilder(equation).variable("x").build();
        }

        System.out.print("Enter the initial guess x0: ");
        double x0 = scanner.nextDouble();

        System.out.print("Enter the initial guess x1: ");
        double x1 = scanner.nextDouble();

        System.out.print("Enter the number of iterations: ");
        int iterations = scanner.nextInt();

        System.out.print("Enter the error tolerance: ");
        double error = scanner.nextDouble();

        SecantMethod secantMethod = new SecantMethod(equations);
        double[] results = secantMethod.solve(x0, x1, iterations);

        System.out.println("Solution:");
        for (int i = 0; i < results.length; i++) {
            System.out.println("x" + i + " = " + results[i]);
        }
    }*/
}

