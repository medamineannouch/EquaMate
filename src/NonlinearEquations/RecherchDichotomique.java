package NonlinearEquations;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class RecherchDichotomique {
    private double epsilon;
    private double[] coeff;
    private double borneInf;
    private double borneSup;

    public RecherchDichotomique() {
    }

    public RecherchDichotomique(double epsilon, double borneInf, double borneSup) {
        this.epsilon = epsilon;
        this.borneInf = borneInf;
        this.borneSup = borneSup;
    }

    public RecherchDichotomique(double epsilon, double[] coeff, double borneInf, double borneSup) {
        this.epsilon = epsilon;
        this.coeff = coeff;
        this.borneInf = borneInf;
        this.borneSup = borneSup;
    }

    public double xFinder() {
        long n = coeff.length;
        double a = borneInf, b = borneSup;
        double fa = 0, fb = 0, fc = 0;
        double c = 0;
        while (b - a > epsilon) {
            c = (a + b) / 2;
            fa = fb = fc = 0;
            for (int i = 0; i < n; i++) { // i l'index de ieme coeff
                fa += coeff[i] * Math.pow(a, i);
                fb += coeff[i] * Math.pow(b, i);
                fc += coeff[i] * Math.pow((a + b) / 2, i);
            }
            System.out.println("f(a)="+fa);
            System.out.println("f(b)="+fb);
            System.out.println("f(c)="+fc);
            if (Math.abs(fc) == 0) {
                break;
            } else if (fa * fc <= 0) {
                b = c;
            } else {
                a = c;
            }
        }
        return c;
    }

    public double dichotomy(Function<Double, Double> f) {
        double fa = f.apply(borneInf);
        double fb = f.apply(borneSup);
       // System.out.println("f(a)="+fa);
        //System.out.println("f(b)="+fb);
        //System.out.println("f(2)="+f.apply(-2.27));
        while (borneSup - borneInf > epsilon) {
            double mil = (borneInf + borneSup) / 2;
            double fmil = f.apply(mil);

            if (fmil == 0) {
                return mil;
            } else if (  borneSup <= fmil && fmil >= borneSup - 0.01) {
                System.out.println("no zero detected");
                return borneSup;
            } else if (fmil * fa < 0) {
                borneSup = mil;
                fb = fmil;
            } else {
                borneInf = mil;
                fa = fmil;
            }
        }

        return (borneInf + borneSup) / 2;
    }

    public List<Double> zerosFinder(Function<Double, Double> f){// Warning! : instablité des resultats
        List<Double> zeros = new ArrayList<>();
        double x = borneInf;
        while (x <= borneSup){
            x = dichotomy(f);
            zeros.add(x);
            System.out.println(x);
            this.setBorneInf(x+1);
        }
        return zeros;
    }

    public  double dichotomyV2(Expression exp) throws Exception
    {   double fa = exp.setVariable("x"+1,borneInf).evaluate();
        double fb = exp.setVariable("x"+1,borneSup).evaluate();

        while (borneSup - borneInf > epsilon) {
            double mil = (borneInf + borneSup) / 2;
            double fmil = exp.setVariable("x"+1,mil).evaluate();

            if (fmil == 0) {
                return mil;
            } else if (  borneSup <= fmil && fmil >= borneSup - 0.01) {
                System.out.println("no zero detected");
                return borneSup;
            } else if (fmil * fa < 0) {
                borneSup = mil;
                fb = fmil;
            } else {
                borneInf = mil;
                fa = fmil;
            }
        }

        return (borneInf + borneSup) / 2;
    }

    public double getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(double epsilon) {
        this.epsilon = epsilon;
    }

    public double[] getCoeff() {
        return coeff;
    }

    public void setCoeff(double[] coeff) {
        this.coeff = coeff;
    }

    public double getBorneInf() {
        return borneInf;
    }

    public void setBorneInf(double borneInf) {
        this.borneInf = borneInf;
    }

    public double getBorneSup() {
        return borneSup;
    }

    public void setBorneSup(double borneSup) {
        this.borneSup = borneSup;
    }
}

class Main {
    public static void main(String[] args){
        Expression expression = new ExpressionBuilder("3+2").build();
        double result = expression.evaluate();
        System.out.println(result);
        Expression expression2 = new ExpressionBuilder("sin(x)*sin(x)+cos(x)*cos(x)")
                .variables("x")
                .build()
                .setVariable("x", 0.5);
        double result2 = expression2.evaluate();
        System.out.println(result2);

    }
}