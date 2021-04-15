package ar.edu.itba.sia.utils;

public class Utils {
    public static double DotProduct(double[] v1, double[] v2){
        int n = v2.length;
        double sum = 0.0;
        for(int i =0; i<n; i++){
            sum += v1[i] * v2[i];
        }
        return sum;
    }
}
