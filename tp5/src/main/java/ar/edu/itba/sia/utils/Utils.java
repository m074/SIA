package ar.edu.itba.sia.utils;

import java.util.List;
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

    public static void printLetter(int[] letter){
        String str = "";
        for (int i=0;i<letter.length;i++){
            str += letter[i]==1?"*":" ";
            if((i+1)%5 == 0)
                str += "\n";
        }
        System.out.println(str);
    }

    public static int[] addNoise(int[] pattern, double porcentage){
        int amount = (int)((pattern.length*porcentage)/100);

        int[] noise = new int[pattern.length];
        System.arraycopy(pattern, 0, noise, 0, pattern.length);
        int[] indexes = new int[pattern.length];
        for(int idx=0; idx<indexes.length; idx++)
            indexes[idx] = idx;
        Random rnd = ThreadLocalRandom.current();
        for (int i = indexes.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            int a = indexes[index];
            indexes[index] = indexes[i];
            indexes[i] = a;
        }
        for(int i=0; i<amount; i++){
            if(noise[indexes[i]] == 1){
                noise[indexes[i]] = -1;
            }
            else{
                noise[indexes[i]] = 1;
            }
        }
        return noise;
    }



    public static double[][] noiseData(double[][] data, double percentage){
        double[][] noiseData = new double[data.length][data[0].length];
        for(int i=0; i<data.length; i++){
            noiseData[i] = addNoise(data[i], percentage);
        }
        return noiseData;
    }


    public static double[] addNoise(double[] pattern, double porcentage) {
        int amount = (int) ((pattern.length * porcentage) / 100);
        double[] noise = new double[pattern.length];
        System.arraycopy(pattern, 0, noise, 0, pattern.length);
        int[] indexes = new int[pattern.length];
        for (int idx = 0; idx < indexes.length; idx++)
            indexes[idx] = idx;
        Random rnd = ThreadLocalRandom.current();
        for (int i = indexes.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int a = indexes[index];
            indexes[index] = indexes[i];
            indexes[i] = a;
        }
        for (int i = 0; i < amount; i++) {
            if (noise[indexes[i]] == 1d) {
                noise[indexes[i]] = -1d;
            } else {
                noise[indexes[i]] = 1d;
            }
        }
        return noise;
    }


    public static void printImage(double[] image, int imgWidth) {
        for (int i = 0; i < image.length; i++) {
            if(i!=0 && i%imgWidth==0){
                System.out.print("\n");
            }
            char aux = image[i] == 1 ? '*':' ';
            System.out.print(aux + " ");
        }
        System.out.println();
        System.out.println();
    }

    public static void printImage(List<Double> list, int imgSize, int imgWidth) {
        double [] image = new double[imgSize];
        for (int i = 0; i < 35 ; i++) {
            image[i] = list.get(i) > 0.5 ? 1 : 0;
        }
        for (int i = 0; i < image.length; i++) {
            if(i!=0 && i%imgWidth==0){
                System.out.print("\n");
            }
            char aux = image[i] == 1 ? '*':' ';
            System.out.print(aux + " ");
        }
        System.out.println();
        System.out.println();
    }



}
