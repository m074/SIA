package ar.edu.itba.sia.Model;

import java.util.ArrayList;
import java.util.List;

public class Hopfield {

    List<int[]> patterns;
    int size;
    List<HopfieldNeuron> neurons = new ArrayList<>();


    public Hopfield(List<int[]> patterns) {
        this.patterns = patterns;
        this.size = patterns.get(0).length;

        for (int k = 0; k < size; k++) {
            neurons.add(new HopfieldNeuron(size));
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Double[] weights = neurons.get(i).weights;
                if (i == j) {
                    weights[j] = new Double(0);
                } else {
                    double aux = 0;
                    for (int pat = 0; pat < patterns.size(); pat++) {
                        aux += patterns.get(pat)[i] * patterns.get(pat)[j];
                    }
                    aux = aux * (1d/size);
                    weights[j] = aux ;
                }
            }
        }

        //for(HopfieldNeuron n : neurons){
        //    for(int z=0; z<size; z++){
        //        System.out.println(n.weights[z]);
        //    }
        //    System.out.println("\n");
        //}

    }


    public void consult(int[] consult){
        //initialization
        for( int in=0; in<neurons.size(); in++){
            neurons.get(in).state = consult[in];
        }

        //
        for(HopfieldNeuron n : neurons) {
            System.out.println(" initial " + n.state);
        }
        //

        for(HopfieldNeuron n : neurons){
            double st=0;
            for(int z=0; z<size; z++){
                st+= n.weights[z] * neurons.get(z).state;
            }
            n.state = sign(st);
        }

        //
        for(HopfieldNeuron n : neurons) {
            System.out.println(" 1 iteration " + n.state);
        }
        //
    }


    public int sign(double n){
        if(n>=0)
            return 1;
        return -1;
    }


}




class HopfieldNeuron{
    public Double[] weights;
    public int state;

    public HopfieldNeuron(int weightsSize){
        weights = new Double[weightsSize];
    }
}
