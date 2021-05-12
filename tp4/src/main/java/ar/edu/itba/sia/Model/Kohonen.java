package ar.edu.itba.sia.Model;

import org.apache.commons.math3.ml.neuralnet.Neuron;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Kohonen {
    private double radius;
    private int iterations;
    private double eta = 1.0;
    private double[][] inputData;
    private KohonenNeuron neuronMatrix[][];

    public Kohonen(double[][] inputData, double starting_radius, int k) {
        this.radius = starting_radius;
        this.inputData = inputData.clone();
        this.neuronMatrix = initMatrix(inputData, k);
        this.iterations = 50*inputData.length;
    }

    public KohonenNeuron[][] processData(){
        for(int i = 0; i<iterations; i++){
            updateEta(i);
            int idx = ThreadLocalRandom.current().nextInt(0, inputData.length-1);
            processInput(inputData[idx]);
        }
        return this.neuronMatrix;
    }

    private KohonenNeuron[][] initMatrix(double[][] inputData, int k){
        KohonenNeuron[][] matrix = new KohonenNeuron[k][k];
        for(int i=0; i < k; i++){
            for(int j=0; j<k; j++){
                matrix[i][j] = new KohonenNeuron(inputData);
            }
        }
        return matrix;
    }

    private void processInput(double[] input){
        int[] i_j = findBestNeuron(input);
        neuronMatrix[i_j[0]][i_j[1]].count++;
        updateNeighbours(i_j, input);
    }

    private void updateNeighbours(int[] i_j, double[] input){
        List<KohonenNeuron> neighbours = getNeighbours(i_j);
        for(KohonenNeuron n : neighbours){
            n.updateWeights(input, this.eta);
        }
    }
    private List<KohonenNeuron> getNeighbours(int[] i_j){
        int botLimit = (i_j[0] + radius) < neuronMatrix.length ?  (int) (i_j[0] + radius) : neuronMatrix.length-1;
        int topLimit = (i_j[0] - radius) > 0 ?  (int) (i_j[0] + radius) : 0;
        int rightLimit = (i_j[1] + radius) < neuronMatrix.length ?  (int) (i_j[1] + radius) : neuronMatrix.length-1;
        int leftLimit = (i_j[1] - radius) > 0 ?  (int) (i_j[1] + radius) : 0;

        List<KohonenNeuron> l = new ArrayList<>();
        for(int i=topLimit; i<=botLimit; i++){
            for(int j= leftLimit; j<=rightLimit; j++){
                double d = Math.sqrt(Math.pow(i_j[0] - i, 2) + Math.pow(i_j[1] - j, 2));
                if(d <= radius){
                    l.add(neuronMatrix[i][j]);
                }
            }
        }
        return l;
    }
    private int[] findBestNeuron(double[] input){
        int best_i=-1; int best_j=-1;
        double dist = Double.MAX_VALUE;
        for(int i =0; i<neuronMatrix.length; i++){
            for(int j=0;j<neuronMatrix.length;j++){
                double aux_dist = calculateDistance(neuronMatrix[i][j], input);
                if(aux_dist < dist){
                    dist = aux_dist;
                    best_i = i;
                    best_j = j;
                }
            }
        }
        int[] res = new int[2];
        res[0] = best_i; res[1] = best_j;
        return res;
    }

    private double calculateDistance(KohonenNeuron neuron, double[] input){ // dist euclidea
        double dist = 0.0;
        double[] weights = neuron.weights.clone();
        for(int i = 0; i < weights.length; i++){
            dist+= Math.pow((weights[i] - input[i]), 2);
        }
        return Math.sqrt(dist);
    }

    void updateEta(int i){
        this.eta= 1.0/i;
    }
}
