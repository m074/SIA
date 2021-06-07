package ar.edu.itba.sia.Model;

import ar.edu.itba.sia.Activation.TanH;

public class AutoEncoder {
    private MultiLayerPerceptron MLPin;
    private MultiLayerPerceptron MLPout;

    public AutoEncoder(double learningRate, double[][] inputData, double[] outputData) {
        MLPin = new MultiLayerPerceptron(0.001,0.01,inputData,outputData, new TanH(), new int[]{2,1}, false, 5, 0.01, 0.1);
        //MLPout = new MultiLayerPerceptron(0.001,0.01,outputData,inputData, new TanH(), new int[]{2,1}, false, 5, 0.01, 0.1);
    }
}
