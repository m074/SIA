package ar.edu.itba.sia.Model;

import ar.edu.itba.sia.Activation.ActivationFunction;

import java.util.concurrent.ThreadLocalRandom;

public class SimplePerceptron {
    ActivationFunction actFunc;
    double[] weights = {0.0};
    double[][] inputData;
    double[] outputData;
    double[] min_weights;
    double learningRate;
    double min_error;
    double min_bias;
    double bias;
    Neuron neuron;

    public SimplePerceptron(double learningRate, double[][] inputData, double[] outputData, ActivationFunction actFunc) {
        this.actFunc = actFunc;
        this.learningRate = learningRate;
        this.inputData = inputData;
        this.outputData = outputData;
        this.min_error = inputData.length * 2.0;
        this.bias = ThreadLocalRandom.current().nextDouble(-1.0, 1.0);
        this.min_bias = this.bias;
        this.neuron = new Neuron(weights, bias, actFunc);
        this.min_weights = weights;
    }

    public void train(int limit){
        int i = 0;
        int n = 0;
        double error = 1.0;
        while(error > 0.0 && i < limit){
            int random_idx =ThreadLocalRandom.current().nextInt(0, inputData.length-1);
            double randInput[] = inputData[random_idx];
            double randOutput = outputData[random_idx];
            double activation = neuron.activation(randInput);
            neuron.correct(learningRate, randOutput, activation, randInput);
            error = neuron.calculateError(inputData, outputData);
            if(error < min_error){
                min_error = error;
                min_weights = neuron.weights.clone();
                min_bias = neuron.bias;
            }
            i++; n++;
        }|  
    }
}
