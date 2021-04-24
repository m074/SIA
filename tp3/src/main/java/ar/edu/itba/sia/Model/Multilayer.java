package ar.edu.itba.sia.Model;

import ar.edu.itba.sia.Activation.ActivationFunction;
import ar.edu.itba.sia.utils.Utils;

import java.util.Arrays;
import java.util.Collections;
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


public class Multilayer{
    int[] layers_qty;
    ActivationFunction activationFunction;
    double momentum = 0.5;
    double learningRate;
    double minErr;
    double error_eps;

    double[][] inputData;
    double[] outputData;

    LinkedList<double[]> activation_list;
    LinkedList<double[]> excitation_list;
    LinkedList<double[]> delta_list;


    LinkedList<Layer> layers;

    public Multilayer(double error_eps, double learningRate, double[][] inputData, double[] outputData, ActivationFunction actFunc, int[] layers_qty){
        this.error_eps = error_eps;
        this.learningRate = learningRate;
        this.layers_qty =layers_qty;
        this.activationFunction = actFunc;
        this.layers = new LinkedList();
        this.delta_list = new LinkedList<>();
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
            delta_list.add(new double[layers_qty[l]]);
        }
    }


    public void train(int limit, int sameBiasIterations){
        int iterations = 0;
        while(iterations < limit){
            for(int i=0; i<inputData.length; i++){
                this.feed_forward(inputData[i]);
                this.back_propagation(outputData[i]);
            }
            iterations++;
        }
    }


    public LinkedList<double[]> feed_forward(double[] input_value){
        LinkedList<double[]> elem = new LinkedList<>();
        elem.add(input_value);
        this.excitation_list = new LinkedList<>();

        int i=0;
        for (Layer layer: this.layers) {
            int j=0;
            elem.add(new double[layer.neurons.size() + 1]);
            excitation_list.add(new double[layer.neurons.size()]);
            Arrays.fill(elem.getLast(), 1.0);
            for (Neuron neuron: layer.neurons) {
                elem.get(i+1)[j] = neuron.activation(elem.get(i));
                excitation_list.get(i)[j] = calculateExcitation(elem.get(i), neuron.weights);
                j++;
            }
            i++;
        }
        this.activation_list = elem;
        return elem;
    }

    private double calculateExcitation(double selected[], double weights[]){
        double excitation = 0;
        for( int j=0; j<selected.length; j++){
            excitation += weights[j] * selected[j];
        }
        return excitation;
    }


    public void prediction(){
        for(int i=0; i<outputData.length; i++){
            LinkedList<double[]> elem = this.feed_forward(inputData[i]);
            System.out.println(" output " + outputData[i] +" prediction ---> " + elem.getLast()[0]);
        }

    }


    public void back_propagation(double output_value){
        LinkedList<LinkedList<Double>> small_delta_ar = new LinkedList<>();
        for (int i = layers.size() - 1; i >= 0; i--) {
            LinkedList<Double> small_delta_layer = new LinkedList<>();
            Layer layer = layers.get(i);
            for (int j = layer.neurons.size() - 1; j >= 0; j--) {
                double small_delta = 0;
                Neuron neuron = layer.neurons.get(j);
                if(i ==  layers.size() - 1){
                    double error = output_value - this.activation_list.getLast()[0];
                    double exitation = excitation_list.get(i)[j];
                    small_delta =  error * activationFunction.evaluateDer(exitation);

                }else{
                    double[] old_weights = neuron.weights;
                    LinkedList<Double> old_delta = small_delta_ar.get(this.layers.size() - 2 - i);
                    double exitation = excitation_list.get(i)[j];
                    double[] old_delta_array = new double[old_delta.size()];
                    for(int d=0; d<old_delta_array.length; d++){
                       old_delta_array[d] = old_delta.get(d);
                    }
                    small_delta =  activationFunction.evaluateDer(exitation) * Utils.DotProduct(old_weights,old_delta_array);
                }
                small_delta_layer.add(small_delta);
                for (int k = neuron.weights.length - 1; k >= 0; k--) {
                    double V = this.activation_list.get(i)[k];
                    double delta = this.learningRate * small_delta * V + momentum * this.delta_list.get(i)[j];
                    this.delta_list.get(i)[j] = delta;
                    neuron.weights[k] += delta;
                }
            }
            small_delta_ar.add(small_delta_layer);
        }
    }

}

