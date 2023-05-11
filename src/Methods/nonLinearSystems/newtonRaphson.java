//package Methods.nonLinearSystems;
//
//import Methods.linearSystems.gaussianElimination;
//
//import static Methods.matrixUtilities.matrixUtilities.MatrixUtilities.norm;
//
//public class newtonRaphson
//{
//    public static double[][] substituteJacobie(function_def[][] jacobi, double[] values)
//    {
//        double[][] res = new double[jacobi.length][jacobi.length];
//        for(int line=0; line< jacobi.length; line++)
//        {
//            for(int column=0; column< jacobi.length; column++)
//            {
//                res[line][column] = jacobi[line][column].function_(values);
//            }
//        }
//        return res;
//    }
//
//    public static double[] minusF(function_def[] F, double[] values)
//    {
//        double[] res = new double[F.length];
//        for(int i=0; i<F.length; i++) res[i] = -F[i].function_(values);
//        return res;
//    }
//
//    public static double[] newton(function_def[] F, double[] guess, function_def[][] jacobi, int iteration, double error) throws Exception
//    {
//        if(iteration == 0) return guess;
//
//        double[] newGuess = new double[F.length];
//        //find h
//            double[] h = gaussianElimination.gaussianEliminationMathod(substituteJacobie(jacobi, guess), minusF(F, guess));
//        for(int i=0; i<h.length; i++) newGuess[i] = guess[i] + h[i];
//        if(norm(newGuess, guess) <= error) return newGuess;
//        else if(iteration == 1) throw new Exception("Newton-Raphson method diverges");
//        else return newton(F, newGuess, jacobi, iteration - 1, error);
//    }
//
//    public static void main(String[] args) throws Exception {
//        function_def f1 = params -> - Math.pow(params[0], 3) + params[1];
//        function_def f2 = params -> Math.pow(params[0], 2) + Math.pow(params[1], 2) - 1;
//
//        function_def[] f = {f1, f2};
//        function_def[][] jacobi = {
//                {params -> -3 * Math.pow(params[0], 2), params -> 1},
//                {params -> 2*params[0], params -> 2*params[1]}
//        };
//
//        double[] res = newton(f, new double[] {1.0, 2.0}, jacobi, 10, 0.0000001);
//        for (double re : res) System.out.print(re + " ");
//    }
//}
