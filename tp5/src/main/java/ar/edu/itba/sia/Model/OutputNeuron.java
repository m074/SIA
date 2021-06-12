package ar.edu.itba.sia.Model;

import ar.edu.itba.sia.Activation.ActivationFunction;

public class OutputNeuron extends Neuron {

    public OutputNeuron(int layerIdx, ActivationFunction actFunc, double learningRate, Integer bias) {
        super(layerIdx, actFunc, learningRate, bias);
    }

    @Override
    public void calculateDelta(double expectedValue){
        delta = (expectedValue - V) * actFunc.evaluateDer(h);
    }
}
