package ar.edu.itba.sia.Model;

import ar.edu.itba.sia.Activation.ActivationFunction;
import ar.edu.itba.sia.utils.Utils;

public class Neuron {
    double[] weights;
    double bias;
    ActivationFunction actFunc;

    public Neuron(double[] weights, double bias, ActivationFunction actFunc) {
        this.weights = weights;
        this.bias = bias;
        this.actFunc = actFunc;
    }

    public double activation(double[] data){
        double excitation = Utils.DotProduct(data, weights);
        return actFunc.evaluate(excitation + bias);
    }

    public void correct(double learningRate, double expected, double result, double[] input){
        double correction = learningRate * (expected - result); //a chequear
        double[] weight_correction = input.clone();
        for(int j = 0; j<weight_correction.length; j++){
            weight_correction[j] *= correction;
        }
        for(int i = 0; i < weights.length; i++){
            this.weights[i] += weight_correction[i];
        }
        this.bias += correction;
    }

    public double calculateError(double[][] inputData, double[] outputData){
        double error = 0;
        int i = 0;
        for(double[] data : inputData){
            error += Math.abs(activation(data) - outputData[i]);
            i++;
        }
        return error;
    }
}
