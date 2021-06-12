package ar.edu.itba.sia.Model;

import ar.edu.itba.sia.Activation.ActivationFunction;
import ar.edu.itba.sia.Activation.TanH;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AutoEncoder {

    private ActivationFunction actFunc;
    private List<Neuron>[] layers;
    private double learningRate;


    public AutoEncoder(double learningRate, int inputLayerSize, int outputLayerSize, int[] hiddenLayersSizes, ActivationFunction actFunc) {
        this.learningRate = learningRate;
        this.actFunc = actFunc;
        int layerAmount = hiddenLayersSizes.length+2;
        layers = new ArrayList[layerAmount];

        //capa inicial
        layers[0] = addLayer(inputLayerSize, null, false);

        //capas escondidas
        for(int i=0; i < hiddenLayersSizes.length; i++){
            layers[i+1] = addLayer(hiddenLayersSizes[i], layers[i], false);
        }

        //capa final
        layers[layers.length - 1] = addLayer(outputLayerSize, layers[layers.length - 2], true);
    }

    private List<Neuron> addLayer(int size, List<Neuron> prev, boolean isLast){
        List<Neuron> layer = new ArrayList<>();
        for(int i=0; i<size; i++){
            Neuron n;
            if(!isLast){
                n = new Neuron(i, actFunc, learningRate, 1);
            }else{
                n = new OutputNeuron(i, actFunc, learningRate, 1);
            }

            if(prev != null){
                for(Neuron pn:prev){
                    pn.makeConnection(n);
                }
            }
            layer.add(n);
        }
        return layer;
    }

    public List<Double> evaluate(double[] entry){
        return evaluate(entry, 0, layers.length-1);
    }

    public List<Double> evaluateLatentSpace(double[] entry){
        return evaluate(entry, 0, layers.length/2);
    }

    public List<Double> evaluate(double[] entry, int from, int to){
        if(entry.length != layers[from].size())
            return null;

        for(int i = 0; i < layers[from].size(); i++){
            layers[from].get(i).setValue(entry[i]);
        }

        for(int i = from+1; i < to+1; i++){
            for(Neuron n: layers[i]){
                n.evaluate();
            }
        }
        List<Double> results = new ArrayList<>();
        for(Neuron n : layers[to]){
            results.add(n.getValue());
        }
        return results;
    }

    public double getError(double[][] input, double[][] output){
        double err = 0;
        for (int i = 0; i<input.length; i++) {
            List<Double> res = evaluate(input[i]);
            for(int j = 0; j<layers[layers.length-1].size(); j++){
                err += Math.pow(output[i][j] - res.get(j), 2);
            }
        }
        return err/input.length;
    }

    public void train(double[][] input, double[][] output, int epochs){
        train(input, output, epochs, 0.0);
    }
    public void train(double[][] input, double[][] output, int epochs, double momentum){
        if(input.length != output.length || input[0].length != layers[0].size()){
            return;
        }

        List<Integer> trainOrder = new ArrayList<>();
        for(int i=0; i<input.length; i++){
            trainOrder.add(i);
        }

        for(int curr=1; curr<=epochs; curr++){
            Collections.shuffle(trainOrder);
            for(Integer i : trainOrder){
                double[] entry = input[i];
                double[] expectedResult = output[i];

                evaluate(entry);

                //back propagation
                for(int j = layers.length-1; j >= 1; j--) {
                    for(int k=0; k < layers[j].size(); k++) {
                        Neuron n = layers[j].get(k);
                        if (j == layers.length - 1)
                            n.calculateDelta(expectedResult[k]);
                        else
                            n.calculateDelta(0.0);

                        for(Connection c : n.input) {
                            c.updateDeltaWeight();
                            if(momentum != 0)
                                c.updateWeightWithMomentum(momentum);
                        }
                    }
                }
            }
        }
        if(momentum != 0){
            for(int i = layers.length - 1; i >= 1; i--) {
                for(int j = 0; j < layers[i].size(); j++) {
                    Neuron n = layers[i].get(j);
                    for(Connection c : n.input)
                        c.updateWeightWithMomentum(momentum);
                }
            }
        }
    }

}
