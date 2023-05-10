package Methods.linearSystems;

import java.util.Arrays;

public class Crammer {
    private double[][] matrix;
    private double [] secMember;

    public Crammer(int size) {
        matrix = new double[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                matrix[i][j] = 0;
            }
        }
    }
    private double [][] cloneMatrix(){
        double[][] matRes = new double[matrix.length][matrix[matrix.length-1].length];
        for(int i=0;i < matrix.length;i++){
            for (int j=0;j < matrix.length;j++){
                matRes[i][j] = matrix[i][j];
            }
        }
        return matRes;
    }

    public double detereminant() {

        double[][] matRes = cloneMatrix();
        int signe = 1;
        double pivot, coef, det = 1;
        for (int i = 0; i < matRes.length; i++) {
            pivot = matRes[i][i];
            if (pivot == 0) {
                int indice = -1;
                for (int j = i + 1; j < matRes.length; j++) {
                    if (matRes[j][i] != 0) {
                        indice = j;
                        break;
                    }
                }
                if (indice == -1) {
                    return 0; //si le pivot est nul et tous en dessous nuls, le déterminant = 0;
                }
                for (int k = 0; k < matRes.length; k++) {
                    double temp = matRes[i][k];
                    matRes[i][k] = matRes[indice][k];
                    matRes[indice][k] = temp;
                }
                signe *= -1;
                pivot = matRes[i][i];
            }
            for (int j = i + 1; j < matRes.length; j++) {//triangularisation de matrice
                coef = matRes[j][i] / pivot;
                for (int k = i; k < matRes.length; k++) {
                    matRes[j][k] = matRes[j][k] - coef * matRes[i][k];
                }
            }
            det *= pivot;
        }
        return signe * det;
    }

    private double detereminant(double[][] A) {
        //double[][] matRes = new double[matrix.length][matrix[matrix.length-1].length];
        int signe = 1;
        double pivot, coef, det = 1;
        for (int i = 0; i < A.length; i++) {
            pivot = A[i][i];
            if (pivot == 0) {
                int indice = -1;
                for (int j = i + 1; j < A.length; j++) {
                    if (A[j][i] != 0) {
                        indice = j;
                        break;
                    }
                }
                if (indice == -1) {
                    return 0; //si le pivot est nul et tous en dessous nuls, le déterminant = 0;
                }
                for (int k = 0; k < A.length; k++) {
                    double temp = A[i][k];
                    A[i][k] = A[indice][k];
                    A[indice][k] = temp;
                }
                signe *= -1;
                pivot = A[i][i];
            }
            for (int j = i + 1; j < A.length; j++) {//triangularisation de matrice
                coef = A[j][i] / pivot;
                for (int k = i; k < A.length; k++) {
                    A[j][k] = A[j][k] - coef * A[i][k];
                }
            }
            det *= pivot;
        }
        return signe * det;
    }

    public double[] crammer(){//resolution de AX=B
        double detemp;
        double [] X = new double[secMember.length];
        double [][] temp = cloneMatrix();
        for (int i = 0;i< matrix.length;i++){
            for (int j = 0;j< matrix.length;j++){
                temp[j][i] = secMember[j];
            }
            detemp = detereminant(temp);
            //System.out.println("detemp = "+detemp + "|" + "det(matrix) ="+this.detereminant());
            X[i] = detemp/this.detereminant();
            temp = cloneMatrix();
        }
        return  X;
    }

    public void showMatrix(){
        for(int i = 0;i < matrix.length;i++){
                    if(i==0){
                        System.out.print("["+Arrays.toString(matrix[i]));
                    }else if(i== matrix.length -1){
                        System.out.println(Arrays.toString(matrix[matrix.length-1])+"]");
                    }else {
                        System.out.print(Arrays.toString(matrix[i]));
                    }
        }
    }
    private void showMatrix(double [][] A){
        for(int i = 0;i < A.length;i++){
            if(i==0){
                System.out.println("["+Arrays.toString(A[i]));
            }else if(i== A.length -1){
                System.out.println(Arrays.toString(A[A.length-1])+"]");
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
