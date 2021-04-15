package ar.edu.itba.sia.Model;

import ar.edu.itba.sia.Activation.ActivationFunction;

import java.util.concurrent.ThreadLocalRandom;

public class SimplePerceptron {
    ActivationFunction actFunc;
    double[] weights = {0.0};
    double[][] inputData;
    double[] outputData;
    double learningRate;
    double min_error;
    double bias;
    Neuron neuron;

    public SimplePerceptron(double learningRate, double[][] inputData, double[] outputData, ActivationFunction actFunc) {
        this.actFunc = actFunc;
        this.learningRate = learningRate;
        this.inputData = inputData;
        this.outputData = outputData;
        this.min_error = inputData.length * 2.0;
        this.bias = ThreadLocalRandom.current().nextDouble(-1.0, 1.0);
        this.neuron = new Neuron(weights, bias, actFunc);
    }

    public void train(){
        int i = 0;
        int n = 0;
        double error = 1.0;
        while(error > 0.0){ // && i < limite
            int random_idx =ThreadLocalRandom.current().nextInt(0, inputData.length-1);
            double randInput[] = inputData[random_idx];
            double randOutput = outputData[random_idx];
            double activation = neuron.activation(randInput);
            neuron.correct(learningRate, randOutput, activation, randInput);
            //falta calcular error, avanzar el i, etc
        }
    }
}
