package ar.edu.itba.sia.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class KohonenNeuron {
    public double weights[];
    public int count;
    public int lastCount;
    public List<String> countryHits = new ArrayList<>();
    public List<String> countryLastHits = new ArrayList<>();
    public KohonenNeuron(double[][] inputData){
        this.count=0;
        this.lastCount=0;
        int idx= ThreadLocalRandom.current().nextInt(0,inputData.length);
        weights = inputData[idx].clone();
    }

    public void updateWeights(double[] input, double eta){
        for(int i=0; i<weights.length; i++){
            weights[i] += eta * (input[i] - weights[i]);
        }
    }
}
