package Methods.linearSystems;

import java.util.Arrays;

public class Cholesky {
    private double[][] matrix;
    private double [] secMember;
    public Cholesky(int size){
        matrix = new double[size][size];
        secMember = new double[size];
    }
    public double [][] choleskyProcess(){//retourne la matrice L triang inf
        double [][] L = new double[matrix.length][matrix.length];
        double sum =0;
        //First Column
        L[0][0]=1;
        for(int k=1;k<L.length;k++){
            L[k][0] = matrix[k][0] / L[0][0];
        }
        for(int i=1;i<L.length;i++){
            for (int k=0;k<=i-1;k++){
                sum += Math.pow(L[i][k],2);
            }
            //les elements diagonaux
            L[i][i] = Math.sqrt(matrix[i][i]-sum);
            sum=0;
            // line i column j
            for(int j=i+1;j<L.length;j++){ //  k (1-->i-1) j(i+1-->n)
                for(int k=0;k<=i-1;k++){
                    sum+= (L[j][k]-sum) * L[i][k];
                }
                L[j][i] = (matrix[j][i]-sum) / L[i][i];
            }
            sum = 0;
        }
        return L;
    }
    public double[][] Ltranspose(){
        double [][] L = this.choleskyProcess();
        double [][] Lt = new double[matrix.length][matrix.length];
        for(int i=0;i < L.length;i++){
            for(int j=0;j < L.length;j++){
                Lt[i][j] = L[j][i];
            }
        }
        return Lt;
    }
    public double[] triangSystResolv(){ //resoudre syst triang sup par remontée
        double[] X = new double[matrix.length];
        double[][] Lt = Ltranspose();
        X[matrix.length-1] = secMember[secMember.length-1];
        for(int i= matrix.length-1;i>=0;i--){
            double sum = 0;
            for(int j = matrix.length-1;j>i;j--){
                sum += Lt[i][j] * X[j];
                X[i] = (secMember[i]-sum) / Lt[i][i];
            }
        }
        return  X;
    }

    public double[] triangInvSystResolv(){
        double [] Y = triangSystResolv();
        double[][] L = choleskyProcess();
        double[] X = new double[secMember.length];
        X[0] = Y[0] / L[0][0]; //first index = 0 & last n-1 ! A[n-1][n-1]
        for(int i=0;i < L.length;i++){
            double sum = 0;
            for(int j=0;j<i;j++){
                sum += L[i][j] * X[j];
                X[i] = (Y[i]-sum) * L[i][i];
            }
        }
        return X;
    }

    public double[] triangInvSystResolv2(){
        double [] Y = {0.5,0.5,1.2695};
        double[][] L = choleskyProcess();
        double[] X = new double[secMember.length];
        X[0] = Y[0] / L[0][0]; //first index = 0 & last n-1 ! A[n-1][n-1]
        for(int i=0;i < L.length;i++){
            double sum = 0;
            for(int j=0;j<i;j++){
                sum += L[i][j] * X[j];
                X[i] = (Y[i]-sum) * L[i][i];
            }
        }
        return X;
    }

    public double [] cholesky(){
        return triangInvSystResolv();
    }
    public double [] choleskyV2(){
        Crammer C = new Crammer(matrix.length);
        C.setMatrix(matrix);
        C.setSecMember(secMember);
        return C.crammer();
    }
    public void showMatrix(double [][] A){
        for(int i = 0;i < A.length;i++){
            if(i==0){
                System.out.println( Arrays.toString(A[i]));
            }else if(i== A.length -1){
                System.out.println(Arrays.toString(A[A.length-1]));
            }else {
                System.out.println(Arrays.toString(A[i]));
            }
        }
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public double[] getSecMember() {
        return secMember;
    }

    public void setSecMember(double[] secMember) {
        this.secMember = secMember;
    }
}
