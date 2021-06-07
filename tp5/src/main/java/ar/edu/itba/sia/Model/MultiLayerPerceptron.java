package ar.edu.itba.sia.Model;

import ar.edu.itba.sia.Activation.ActivationFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MultiLayerPerceptron {

    List<List<Neuron2>> layers = new ArrayList<>();

    int entriesSize;


    ActivationFunction actFunc;
    double momentum = 0.5;
    double learningRate;

    double error_eps;

    double[][] inputData;
    double[] outputData;
    double minEta = 0.0001;
    boolean adaptLR;
    double LRincrement;
    double LRdecrement;
    double minErr;
    double lastErr = 0.0;
    int incErrors=0;
    int decErrors=0;
    int updateLRits;

    public MultiLayerPerceptron(double error_eps, double learningRate, double[][] data, double[] outputData, ActivationFunction actFunc, int[] layersNumber, boolean adaptLR, int updateLRits, double LRincrement, double LRdecrement){
        this.error_eps = error_eps;
        this.learningRate = learningRate;
        this.actFunc = actFunc;
        this.adaptLR = adaptLR;
        this.LRdecrement = LRdecrement;
        this.LRincrement= LRincrement;
        this.updateLRits = updateLRits;
        entriesSize =1 + data[0].length;
        inputData = new double[data.length][entriesSize];
        this.outputData = outputData;
        Random r = new Random();
        minErr = Double.MAX_VALUE;


        for( int i=0; i<inputData.length; i++){
            for( int j=0; j<inputData[0].length; j++){
                if(j==0){
                    inputData[i][j] = 1;
                }
                else{
                    inputData[i][j] = data[i][j-1];
                }
            }
        }
        for(int l = 0; l<layersNumber.length; l++){
            ArrayList<Neuron2> lay = new ArrayList<>();

            if(l==layersNumber.length-1){

                if(l==0) {
                    for (int k = 0; k < layersNumber[l]; k++) {
                        double weights[] = new double[entriesSize];
                        for (int i = 0; i < weights.length; i++) {
                            weights[i]  = -0.2 + (0.4) * r.nextDouble();
                        }
                        Neuron2 neuron = new Neuron2(weights);
                        lay.add(neuron);
                    }
                } else {
                    for (int k = 0; k < layersNumber[l]; k++) {
                        double weights[] = new double[layers.get(l - 1).size()];
                        for (int i = 0; i < weights.length; i++) {
                            weights[i]  = -0.2 + (0.4) * r.nextDouble();
                        }
                        Neuron2 neuron = new Neuron2(weights);
                        lay.add(neuron);
                    }
                }
            } else{
                if(l==0) {
                    for (int k = 0; k <= layersNumber[l]; k++) {
                        double weights[] = new double[entriesSize];
                        for (int i = 0; i < entriesSize; i++) {
                            weights[i]  = -0.2 + (0.4) * r.nextDouble();
                        }
                        Neuron2 neuron = new Neuron2(weights);
                        lay.add(neuron);
                    }
                } else {
                    for (int k = 0; k <= layersNumber[l]; k++) {
                        double weights[] = new double[layers.get(l - 1).size()];
                        for (int i = 0; i < weights.length; i++) {
                            weights[i]  = -0.2 + (0.4) * r.nextDouble();
                        }
                        Neuron2 neuron = new Neuron2(weights);
                        lay.add(neuron);
                    }
                }
            }
            layers.add(lay);
        }
    }



    public void train(int limit, int sameBiasIterations){
        int iterations = 0;
        while(iterations < limit && error_eps<minErr ){


            int i = ThreadLocalRandom.current().nextInt(0, inputData.length);

                propagation(inputData[i]);
                backpropagation(i);

                if(minErr > calculateError()){
                    minErr = calculateError();
                }
            if(adaptLR)
                adaptLearningRate(calculateError());
            iterations++;
        }
        System.out.println("MINERR " + minErr + " epochs: " + iterations);
    }


    public void propagation(double selected[]) {
        for (int layer = 0; layer < layers.size(); layer++) {
            if (layer == 0) {
                for (int neuron = 0; neuron < layers.get(layer).size(); neuron++) {
                    Neuron2 n = layers.get(layer).get(neuron);
                    if (neuron == 0) {
                        n.V = 1;
                    } else {
                        double excitation = n.calExcitation(selected);
                        n.V = actFunc.evaluate(excitation);
                    }
                }
            }else if(layer!=layers.size()-1){
                for(int neuron=0; neuron<layers.get(layer).size(); neuron++) {
                    Neuron2 n = layers.get(layer).get(neuron);
                    if (neuron == 0) {
                        n.V = 1;
                    } else {
                        double v[] = new double[layers.get(layer-1).size()];
                        for (int j = 0; j < layers.get(layer-1).size(); j++) {
                            v[j] = layers.get(layer-1).get(j).V;
                        }
                        double excitation = n.calExcitation(v);
                        n.V = actFunc.evaluate(excitation);
                    }
                }
            }else {
                for(int neuron=0; neuron<layers.get(layer).size(); neuron++) {
                    Neuron2 n = layers.get(layer).get(neuron);

                    double v[] = new double[layers.get(layer-1).size()];
                    for (int j = 0; j < layers.get(layer-1).size(); j++) {
                        v[j] = layers.get(layer-1).get(j).V;
                    }
                    double excitation = n.calExcitation(v);
                    n.V = actFunc.evaluate(excitation);
                }
            }
        }
    }



    public void backpropagation(int index){

        for(int layer=layers.size()-1; layer>=0; layer--){
            if(layer == layers.size()-1){
                for(int neuron = 0; neuron <layers.get(layer).size(); neuron++){
                    Neuron2 n = layers.get(layer).get(neuron);

                    double v[] = new double[layers.get(layer-1).size()];
                    for (int j = 0; j < layers.get(layer-1).size(); j++) {
                        v[j] = layers.get(layer-1).get(j).V;
                    }
                    n.delta =  (outputData[index]- actFunc.evaluate(n.calExcitation(v))) * actFunc.evaluateDer(n.calExcitation(v) ) ;
                    for(int w=0; w<n.weights.length; w++){
                        n.weights[w] += learningRate * n.delta * v[w];
                    }
                }
            }
            else{

                for(int neuron = 0; neuron <layers.get(layer).size(); neuron++){
                    Neuron2 n = layers.get(layer).get(neuron);

                    double v[];
                    if(layer != 0){
                        v = new double[layers.get(layer-1).size()];
                        for (int j = 0; j < layers.get(layer-1).size(); j++) {
                            v[j] = layers.get(layer-1).get(j).V;
                        }
                    }
                    else{
                        v = inputData[index];
                    }
                    double delta = 0;
                    for(int sup = 0; sup<layers.get(layer+1).size(); sup++){
                        Neuron2 supNeuron = layers.get(layer+1).get(sup);

                        delta += supNeuron.weights[neuron] * supNeuron.delta;
                    }

                    n.delta = delta * actFunc.evaluateDer(n.calExcitation(v)) ;

                    for(int w=0; w<n.weights.length; w++){
                        n.weights[w] += learningRate* n.delta * v[w];
                    }
                }
            }

        }
    }


    public double calculateError(){

        int layer = layers.size()-1;
        double error = 0;

        for(int i=0; i<outputData.length; i++) {
            for (int neuron = 0; neuron < layers.get(layer).size(); neuron++) {
                Neuron2 n = layers.get(layer).get(neuron);
                propagation(inputData[i]);
                double v[] = new double[layers.get(layer - 1).size()];
                for (int j = 0; j < layers.get(layer - 1).size(); j++) {
                    v[j] = layers.get(layer - 1).get(j).V;
                }
                error += Math.pow(outputData[i] - actFunc.evaluate(n.calExcitation(v)), 2);
            }
        }
        error = 0.5 * error;

        return error;
    }

    private void adaptLearningRate(double error){
        if(lastErr == 0.0){
            lastErr = error;
        }
        else if(lastErr < error){
            decErrors = 0;
            incErrors++;
        }else{
            incErrors = 0;
            decErrors++;
        }

        if(decErrors>=updateLRits){
            learningRate+=LRincrement;
            decErrors=0;
        }
        if(incErrors>=updateLRits){
            learningRate*=LRdecrement;
            if(learningRate<=minEta){
                learningRate=minEta;
            }
            incErrors=0;
        }
        lastErr=error;
    }


    public void prediction(){
        for(int i=0; i<outputData.length; i++){

            propagation(inputData[i]);

            int layer = layers.size()-1;
            double pred = 0;

            for(int neuron = 0; neuron <layers.get(layer).size(); neuron++) {
                Neuron2 n = layers.get(layer).get(neuron);
                double v[] = new double[layers.get(layer - 1).size()];
                for (int j = 0; j < layers.get(layer - 1).size(); j++) {
                    v[j] = layers.get(layer - 1).get(j).V;
                }
                pred = actFunc.evaluate(n.calExcitation(v));
            }
            System.out.println(" output " + outputData[i] +" prediction ---> " + pred );
        }
    }





}


class Neuron2{
   double delta = 0;
   double V = 0;
   double[] weights;

   public Neuron2(double[] weights) {
       this.weights = weights;
   }

   public double calExcitation(double input[]){
       double excitation = 0;
       for( int j=0; j<input.length; j++){
           excitation += weights[j] * input[j];
       }
       return excitation;
   }

}