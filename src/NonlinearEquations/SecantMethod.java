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


    public static double solve(Expression expression, double x0, double x1, int iteration, double error) throws Exception {
        if (iteration == 0) return x1;

        double fx1 = expression.setVariable("x", x1).evaluate();
        double fx0 = expression.setVariable("x", x0).evaluate();

        double x2 = x1 - (fx1 * (x1 - x0)) / (fx1 - fx0);
        if (Math.abs(x1 - x2) <= error) return x2;
        else if (iteration == 1) throw new Exception("Secant doesn't converge");
        else return solve(expression, x1, x2, iteration - 1, error);
    }



}



