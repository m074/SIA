package ar.edu.itba.sia.Model;

import java.util.concurrent.ThreadLocalRandom;

public class Oja {
    double eta;
    double[][] inputData;
    ThreadLocalRandom rand;

    public Oja(double[][] inputData, double learning_rate){
        this.eta = learning_rate;
        this.inputData = inputData;
        this.rand  = ThreadLocalRandom.current();
    }

    public double[] train(int epochs){
        int dimension = inputData[0].length;
        double[] w = new double[dimension];
        for(int x=0; x<w.length; x++){
            w[x] = rand.nextDouble();
        }
        for(int ep=0; ep < epochs; ep++){
            for(int i=0; i<inputData.length; i++){
                double[] input = inputData[i];
                double y_value = innerArray(input, w);
                for(int x=0; x<w.length; x++){
                    double w_delta = this.eta * y_value * (input[x] - y_value * w[x]);
                    w[x] += w_delta;
                }

            }
        }
        double norm_value = Math.sqrt(innerArray(w,w));
        for(int x=0; x<w.length; x++){
            w[x] = w[x]/norm_value;
        }
        return w;
    }

    private double innerArray(double[] array1, double[] array2){
        double sum = 0;
        for(int x=0; x<array1.length; x++){
            sum += array1[x] * array2[x];
        }
        return sum;
    }
}
