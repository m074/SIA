package ar.edu.itba.sia.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    public static void shuffleArray(double[][] array){
        int index; double[] temp;
        for (int i = array.length - 1; i > 0; i--)
        {
            index = ThreadLocalRandom.current().nextInt(0, i+1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    public static double[][] dotArray(double[][] A, double[][] B) {

        int aRows = A.length;
        int aColumns = A[0].length;
        int bRows = B.length;
        int bColumns = B[0].length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
        }

        double[][] C = new double[aRows][bColumns];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                C[i][j] = 0.00000;
            }
        }

        for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < bColumns; j++) { // bColumn
                for (int k = 0; k < aColumns; k++) { // aColumn
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    public static void div_Array(double[][] A, double scalar) {

        int aRows = A.length;
        int aColumns = A[0].length;

        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < aColumns; j++) {
                A[i][j] = A[i][j] / scalar;
            }
        }

    }

    public static void fill_diagonalArray(double[][] A, double value) {

        int aRows = A.length;
        int aColumns = A[0].length;

        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < aColumns; j++) {
                if(i==j){
                    A[i][j] = value;
                }
            }
        }

    }

    public static double[][] transposeArray(double[][] A) {

        int aRows = A.length;
        int aColumns = A[0].length;

        double[][] C = new double[aColumns][aRows];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < aColumns; j++) {
                C[j][i] = A[i][j];
            }
        }
        return C;
    }
}
