package ar.edu.itba.sia.Model;

import ar.edu.itba.sia.Activation.ActivationFunction;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

class Layer{
    LinkedList<Neuron> neurons;

    public Layer(int neurons_qty, int connections_qty, ActivationFunction activationFunction){
        neurons = new LinkedList<>();
        for(int n=0; n<neurons_qty; n++){
            double[] weights = new double[connections_qty];
            for(int i = 0; i<connections_qty; i++){
                weights[i] = 0;
            }
            Neuron neuron = new Neuron(weights, activationFunction);
            neurons.add(neuron);
        }


    }
}


public class Multilayer {
    int[] layers_qty;
    ActivationFunction activationFunction;
    double momemtum = 0;
    double learningRate;
    double min_error;
    double error_eps;

    double[][] inputData;
    double[] outputData;
    
    LinkedList<Layer> layers;

    public Multilayer(int[] layers_qty, ActivationFunction activationFunction, double[][] inputData, double[] outputData){
        this.layers_qty =layers_qty;
        this.activationFunction = activationFunction;
        this.layers = new LinkedList();
        this.inputData = new double[inputData.length][inputData[0].length+1];
        for(int i = 0; i < this.inputData.length; i++){
            this.inputData[i][0] = 1.0;
            for(int j = 1; j < this.inputData[0].length; j++){
                this.inputData[i][j] = inputData[i][j-1];
            }
        }
        this.outputData = outputData;

        for(int l = 0; l<layers_qty.length; l++){
            int connections = 0;
            if(l==0){
                connections = this.inputData[0].length;
            }else{
                connections = layers_qty[l-1];
            }
            layers.add(new Layer(layers_qty[l],connections, activationFunction));
        }
    }

    public void train(int limit, int sameBiasIterations){}

    public void feed_forward(){
    }
}

