package ar.edu.itba.sia.Model;

import ar.edu.itba.sia.Activation.ActivationFunction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MultiLayerPerceptron {

    List<List<Neuron2>> layers = new ArrayList<>();

    int entriesSize;


    ActivationFunction actFunc;
    double momentum = 0.5;
    double learningRate;

    double error_eps;

    double[][] inputData;
    double[] outputData;


    double minErr;
    double[] minWeights;


    LinkedList<double[]> activation_list;
    LinkedList<double[]> excitation_list;
    LinkedList<double[]> delta_list;


    public MultiLayerPerceptron(double error_eps, double learningRate, double[][] data, double[] outputData, ActivationFunction actFunc, int[] layersNumber){
        this.error_eps = error_eps;
        this.learningRate = learningRate;
        this.actFunc = actFunc;
        entriesSize =1 + data[0].length;
        inputData = new double[data.length][entriesSize];
        this.outputData = outputData;
        Random r = new Random();


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
                            //weights[i] = 0;
                            weights[i]  = -0.2 + (0.4) * r.nextDouble();
                        }
                        Neuron2 neuron = new Neuron2(weights);
                        lay.add(neuron);
                    }
                } else {
                    for (int k = 0; k < layersNumber[l]; k++) {
                        double weights[] = new double[layers.get(l - 1).size()];
                        for (int i = 0; i < weights.length; i++) {
                            //weights[i] = 0;
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
                            //weights[i] = 0;
                            weights[i]  = -0.2 + (0.4) * r.nextDouble();
                        }
                        Neuron2 neuron = new Neuron2(weights);
                        lay.add(neuron);
                    }
                } else {
                    for (int k = 0; k <= layersNumber[l]; k++) {
                        double weights[] = new double[layers.get(l - 1).size()];
                        for (int i = 0; i < weights.length; i++) {
                            //weights[i] = 0;
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
        while(iterations < limit){


            for(int i=0; i<inputData.length; i++){


                System.out.println("---prop---");
                propagation(inputData[i]);
                //updateDeltas(i);
               // updateWeights(i);

                //update deltas & weights
                System.out.println("---back prop---");
                backpropagation(i);

              //  System.out.println("ERROR " +calculateError(i));

            }

            iterations++;
        }
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
                    System.out.println("capa " + layer + " " + " neurona "+ neuron + " " +"V " + n.V + " conexiones " + n.weights.length);

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
                    System.out.println("capa " + layer + " " + " neurona "+ neuron + " " +"V " + n.V + " conexiones " + n.weights.length);

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
                    System.out.println("capa " + layer + " " + " neurona "+ neuron + " " +"V " + n.V + " conexiones  " + n.weights.length);

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
                    System.out.println("delta salida " + n.delta);

                    for(int w=0; w<n.weights.length; w++){
                        n.weights[w] += learningRate * n.delta * v[w];
                        System.out.println("weight capa "+ layer + " " + " neurona "+ neuron + " " +  n.weights[w]);

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

                    System.out.println("delta capa "+ layer + " " + " neurona "+ neuron + " " + n.delta);

                    for(int w=0; w<n.weights.length; w++){
                        n.weights[w] += learningRate* n.delta * v[w];

                        System.out.println("weight capa "+ layer + " " + " neurona "+ neuron + " " +  n.weights[w]);
                    }

                }

            }

        }
    }






    public void updateDeltas(int index){

        for(int layer=layers.size()-1; layer>=0; layer--){

            if(layer == layers.size()-1){
                for(int neuron = 0; neuron <layers.get(layer).size(); neuron++){
                    Neuron2 n = layers.get(layer).get(neuron);

                    double v[] = new double[layers.get(layer-1).size()];
                    for (int j = 0; j < layers.get(layer-1).size(); j++) {
                        v[j] = layers.get(layer-1).get(j).V;
                    }

                    n.delta =  (outputData[index]- actFunc.evaluate(n.calExcitation(v))) * actFunc.evaluateDer(n.calExcitation(v) ) ;
                    System.out.println("delta salida " + n.delta);
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


                        delta += supNeuron.weights[neuron] * supNeuron.delta ;
                    }

                    n.delta = delta * actFunc.evaluateDer(n.calExcitation(v)) ;
                    System.out.println("delta capa "+ layer + " " + " neurona "+ neuron + " " + n.delta);
                }

            }

        }
    }

    public void updateWeights(int index){
        for (int layer = 0; layer < layers.size(); layer++) {

            if (layer == 0) {
                for (int neuron = 0; neuron < layers.get(layer).size(); neuron++) {
                    Neuron2 n = layers.get(layer).get(neuron);

                    for( int i=0; i< n.weights.length; i++){
                        n.weights[i] += learningRate * n.delta * inputData[index][i];

                        System.out.println("weight capa "+ layer + " " + " neurona "+ neuron + " " +  n.weights[i]);

                    }
                }

            } else{
                for (int neuron = 0; neuron < layers.get(layer).size(); neuron++) {
                    Neuron2 n = layers.get(layer).get(neuron);

                    double v[] = new double[layers.get(layer-1).size()];
                    for (int j = 0; j < layers.get(layer-1).size(); j++) {
                        v[j] = layers.get(layer-1).get(j).V;
                    }

                    for( int i=0; i< n.weights.length; i++){
                        n.weights[i] += learningRate * n.delta * v[i];

                        System.out.println("weight capa "+ layer + " " + " neurona "+ neuron + " " +  n.weights[i]);

                    }
                }
            }


        }
    }



    public double calculateError(int index){

        int layer = layers.size()-1;
        double error = 0;

        for(int neuron = 0; neuron <layers.get(layer).size(); neuron++) {
            Neuron2 n = layers.get(layer).get(neuron);

            double v[] = new double[layers.get(layer - 1).size()];
            for (int j = 0; j < layers.get(layer - 1).size(); j++) {
                v[j] = layers.get(layer - 1).get(j).V;
            }
            error += Math.pow(outputData[index] - actFunc.evaluate(n.calExcitation(v)), 2);
        }
        error = 0.5 * error;

        return error;
    }



    public void prediction(){
        for(int i=0; i<outputData.length; i++){
            System.out.println("--------------");
            propagation(inputData[i]);

            int layer = layers.size()-1;
            double pred = 0;

            for(int neuron = 0; neuron <layers.get(layer).size(); neuron++) {
                Neuron2 n = layers.get(layer).get(neuron);
                System.out.println("weights salida " + n.weights[0] + " " + n.weights[1] + " " +  n.weights[2]);


                double v[] = new double[layers.get(layer - 1).size()];
                for (int j = 0; j < layers.get(layer - 1).size(); j++) {
                    v[j] = layers.get(layer - 1).get(j).V;
                    System.out.println(" V " + j +" " + v[j] );
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