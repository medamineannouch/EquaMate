import Methods.linearSystems.Cholesky;
import Methods.linearSystems.Crammer;
import NonlinearEquations.NewtonDim1;
import NonlinearEquations.RecherchDichotomique;

import java.util.Arrays;
import java.util.function.Function;

public class Main_Charaf {
    public Main_Charaf(){
        testCholesky();
    }
    public void testCrammer(){
        Crammer A = new Crammer(3);
        double [] B = {0.5,0.25,1};
        double [] [] C = {  {1,0,-0.25},
                {0,1,-0.25},
                {-0.25,-0.25,1}};
        A.setMatrix(C);
        A.setSecMember(B);
        A.showMatrix();
        System.out.println(A.detereminant());
        double [] X = A.crammer();
        System.out.println(Arrays.toString(X));
    }

    public  void testCholesky(){
        Cholesky A = new Cholesky(3);
        double [] B = {0.5,0.25,1};
        double [] [] C = {  {1,0,-0.25},
                {0,1,-0.25},
                {-0.25,-0.25,1}};
        A.setMatrix(C);
        A.setSecMember(B);
        A.showMatrix(A.choleskyProcess());
        System.out.println(Arrays.toString(A.choleskyV2()));
        // Problems in solving syst and reverse system

    }

    public void testDichotomie(){
        double[] coeff = {1,3,1};
        RecherchDichotomique R = new RecherchDichotomique(0.01,coeff,2,5);
        R.setEpsilon(0.01);
        //Function<Double, Double> f = x -> Math.pow(x,3) - 3*x + 5;
        Function<Double, Double> f = x -> Math.cos(x) ;
        System.out.println(R.dichotomy(f));
    }
    public void testNewtonDim1(){
        //Function<Double, Double> f = x -> 3*Math.pow(x,2);
        //Function<Double, Double> f = x -> Math.pow(x,3) - 3*x + 1;
        Function<Double, Double> f = x -> Math.tanh(x)*Math.cos(Math.pow(x,2))+x-2;
        NewtonDim1 N = new NewtonDim1(1e-6,f,2);  //tanh(x)cos(x2)+x-2
        System.out.println(N.newtonDim1());
    }

    public void testNewtonDimN(){
        Function<double[], Double> f = x -> Math.pow(x[0],3) - 3*x[1] + 1;
        double [] x = {1,2};
        System.out.println(partialDerivative(f,x,1,1e-6));
    }
    public static double partialDerivative(Function<double[], Double> f, double[] x, int i, double h) {
        double[] xplus = x.clone();
        xplus[i] += h;
        double[] xminus = x.clone();
        xminus[i] -= h;
        return (f.apply(xplus) - f.apply(xminus)) / (2 * h);
    }
    public static void main(String[] args) {
        new Main_Charaf();
    }
}