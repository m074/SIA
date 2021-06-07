package ar.edu.itba.sia.Model;

import ar.edu.itba.sia.utils.Utils;
import org.apache.commons.math3.ml.neuralnet.Neuron;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Kohonen {
    private double radius;
    private int iterations;
    private double eta = 0.9;
    private double[][] inputData;
    private KohonenNeuron neuronMatrix[][];
    private Map<double[], String> countryData;
    public Kohonen(double[][] inputData, double starting_radius, int k, String[] names) {
        this.radius = starting_radius;
        this.inputData = inputData.clone();
        this.neuronMatrix = initMatrix(inputData, k);
        this.iterations = 50*k;
        countryData = new HashMap<>();
        for(int i=0; i<inputData.length;i++){
            countryData.put(inputData[i], names[i]);
        }
    }

    public KohonenNeuron[][] processData(){
        for(int i = 0; i<iterations; i++){
            double[][] input_aux = inputData.clone();
            Utils.shuffleArray(input_aux);
            for(int j=0; j<input_aux.length; j++){
                processInput(input_aux[j]);
            }
            updateEta(i);
            updateRadius(i);
           
            //int idx = ThreadLocalRandom.current().nextInt(0, inputData.length-1);

        }
        return this.neuronMatrix;
    }
    public void updateRadius(int i){
        if(radius-1>1)
            radius-=1; //probar otra cosa si no*/
    }
    public KohonenNeuron[][] getResult(){
        double[][] input_aux = inputData.clone();
        Utils.shuffleArray(input_aux);
        for(int j=0; j<input_aux.length; j++){
            processInputFinal(input_aux[j]);
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
    private void processInputFinal(double[] input){
        int[] i_j = findBestNeuron(input);
        String country = countryData.get(input);
        neuronMatrix[i_j[0]][i_j[1]].lastCount++;
        neuronMatrix[i_j[0]][i_j[1]].countryLastHits.add(country);
        updateNeighbours(i_j, input);
    }
    private void processInput(double[] input){
        int[] i_j = findBestNeuron(input);
        String country = countryData.get(input);
        neuronMatrix[i_j[0]][i_j[1]].count++;
        neuronMatrix[i_j[0]][i_j[1]].countryHits.add(country);
        updateNeighbours(i_j, input);
    }

    private void updateNeighbours(int[] i_j, double[] input){
        List<KohonenNeuron> neighbours = getNeighbours(i_j);
        for(KohonenNeuron n : neighbours){
            n.updateWeights(input, this.eta);
        }
    }
    private List<KohonenNeuron> getNeighbours(int[] i_j){
        int rowMin = Math.max((int) (i_j[0] - radius), 0);
        int rowMax = Math.min((int) (i_j[0] + radius), neuronMatrix.length);
        int colMin = Math.max((int) (i_j[1] - radius), 0);
        int colMax = Math.min((int) (i_j[1] + radius), neuronMatrix.length);



        List<KohonenNeuron> l = new ArrayList<>();
        for(int i=rowMin; i<rowMax; i++){
            for(int j=colMin; j<colMax; j++){
                double d = Math.sqrt(Math.pow(i_j[0] - i, 2) + Math.pow(i_j[1] - j, 2));
                if(d <= radius){
                    l.add(neuronMatrix[i][j]);
                }
            }
        }
        System.out.println(l.size());
        return l;
    }
    private int[] findBestNeuron(double[] input){
        int best_i=0; int best_j=0;
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
        if(i==0)
            return;
        this.eta= 1.0/i;
    }

    public double[][] getUMatrix(){
        int k=this.neuronMatrix.length;
        double[][] uMatrix = new double[k][k];
        for(int i =0; i<k; i++){
            for(int j=0; j<k; j++){
                int[] i_j = {i, j};
                List<KohonenNeuron> neighbours = getNeighbours(i_j);
                int n = neighbours.size();
                double dist = 0.0;
                for(KohonenNeuron neu : neighbours){
                    dist+=calculateDistance(neuronMatrix[i][j], neu.weights);
                }
                uMatrix[i][j] = dist/n;
            }
        }
        return uMatrix;
    }
}
