package NonlinearEquations;

import java.util.List;
import java.util.function.Function;

public class NewtonDim1 {
    private double epsilon;
    private Function<Double,Double> f;
    private double init;

    private List< Function<Double,Double> > fonctiions;

    public NewtonDim1(double epsilon, Function<Double, Double> f, double init) {
        this.epsilon = epsilon;
        this.f = f;
        this.init = init;
    }

    public NewtonDim1() {
    }

    public static double derivative(double x, Function<Double, Double> f) {
        double h = 1e-6; // petit pas
        double fx = f.apply(x); // valeur de la fonction à x
        double fxh = f.apply(x + h); // valeur de la fonction à x + h
        double rawRes = (fxh - fx) / h; // formule de la dérivée
        return Math.floor(rawRes*100) / 100;
    }
    public double newtonDim1(){
        int k = 0;
        double xk = init;
        do {
            xk = xk - (f.apply(xk)/ NewtonDim1.derivative(xk,f));
        }while ( Math.abs(f.apply(xk)) > epsilon); // error Math.abs(init - xk) < epsilon
        return xk;

    }
    public double getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(double epsilon) {
        this.epsilon = epsilon;
    }

    public Function<Double, Double> getF() {
        return f;
    }

    public void setF(Function<Double, Double> f) {
        this.f = f;
    }

    public double getInit() {
        return init;
    }

    public void setInit(double init) {
        this.init = init;
    }
}
