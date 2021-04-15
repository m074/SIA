package ar.edu.itba.sia.Model;

import ar.edu.itba.sia.Activation.ActivationFunction;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class SimplePerceptron {
    ActivationFunction actFunc;
    double[] weights = {};
    double[][] inputData;
    double[] outputData;
    double[] min_weights;
    double learningRate;
    double min_error;
    double min_bias;
    double bias;
    double error_eps;
    Neuron neuron;

    public SimplePerceptron(double error_eps, double learningRate, double[][] inputData, double[] outputData, ActivationFunction actFunc) {
        this.actFunc = actFunc;
        this.learningRate = learningRate;
        this.inputData = inputData;
        this.outputData = outputData;
        this.min_error = inputData.length * 2.0;
        this.bias = ThreadLocalRandom.current().nextDouble(-1.0, 1.0);
        this.min_bias = this.bias;
        this.weights = new double[inputData[0].length];
        for(int i = 0; i<inputData[0].length; i++){
            this.weights[i] = 0.0;
        }
        this.neuron = new Neuron(weights, bias, actFunc);
        this.min_weights = weights;
    }

    public void train(int limit, int sameBiasIterations){
        int i = 0;
        int n = 0;
        double error = 1.0;
        while(error > error_eps && i < limit){
            if(n>sameBiasIterations*inputData.length){
                reset();
            }
            int random_idx =ThreadLocalRandom.current().nextInt(0, inputData.length);
            double[] randInput = inputData[random_idx];
            double randOutput = outputData[random_idx];
            double activation = neuron.activation(randInput);
            neuron.correct(learningRate, randOutput, activation, randInput);
            error = neuron.calculateError(inputData, outputData);
            if(error < min_error){
                min_error = error;
                min_weights = neuron.weights.clone();
                min_bias = neuron.bias;
            }
            System.out.println("Step " + i + ": " + "error " + error + " weights " + Arrays.toString(weights));
            i++; n++;
        }
    }

    public Neuron getBest(){
        return new Neuron(min_weights, min_bias, actFunc);
    }
    public void reset(){
        this.weights = new double[inputData[0].length];
        this.bias = ThreadLocalRandom.current().nextDouble(-1.0, 1.0);
        this.neuron = new Neuron(weights, bias, actFunc);
    }
}
