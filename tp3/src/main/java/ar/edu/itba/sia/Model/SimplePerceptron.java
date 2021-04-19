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
    double error_eps;
    Neuron neuron;

    public SimplePerceptron(double error_eps, double learningRate, double[][] inputData, double[] outputData, ActivationFunction actFunc) {
        this.actFunc = actFunc;
        this.learningRate = learningRate;
        this.inputData = new double[inputData.length][inputData[0].length+1];
        for(int i = 0; i < this.inputData.length; i++){
            this.inputData[i][0] = 1.0;
            for(int j = 1; j < this.inputData[0].length; j++){
                this.inputData[i][j] = inputData[i][j-1];
            }
        }
        this.outputData = outputData;
        this.min_error = Double.MAX_VALUE;
        this.weights = new double[this.inputData[0].length];
        for(int i = 0; i<this.inputData[0].length; i++){
            this.weights[i] = ThreadLocalRandom.current().nextDouble(-1.0, 1.0);
        }
        this.neuron = new Neuron(weights, actFunc);
        this.min_weights = weights;
    }

    public void train(int limit, int sameBiasIterations){
        int i = 0;
        int n = 0;
        double error;
        reset();
        do{

            if(n>sameBiasIterations*inputData[0].length){
                n=0;
                reset();
            }
            int random_idx =ThreadLocalRandom.current().nextInt(0, inputData.length);
            for(int k = 0; k < random_idx; k++) {
                double[] randInput = inputData[k];
                double randOutput = outputData[k];
                double activation = neuron.activation(randInput);
                neuron.correct(learningRate, randOutput, activation, randInput);
            }
            error = neuron.calculateError(inputData, outputData);
            if(error < min_error){
                min_error = error;
                min_weights = neuron.weights.clone();
            }
            System.out.println("Step " + i + ": " + "error " + error + " weights " + Arrays.toString(weights));
            i++; n++;
        }while((error > error_eps && i < limit));
        System.out.println("Min error: " + min_error);
    }

    public Neuron getBest(){
        return new Neuron(min_weights,  actFunc);
    }
    public void reset(){
        for(int i = 0; i<inputData[0].length; i++){
            this.weights[i] = ThreadLocalRandom.current().nextDouble(-1.0, 1.0);
        }
        this.neuron = new Neuron(weights,actFunc);
    }
}
