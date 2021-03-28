package ar.edu.itba.sia.Algorithm;

import ar.edu.itba.sia.Model.Character;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class Universal extends SelectionMethod {

    @Override
    public LinkedList<Character> select(LinkedList<Character> population, int size, long generations){
        LinkedList<Double> randoms = getRandUniversal(size);
        LinkedList<Double> accum = Selections.getFitnessAccum(population);
        return Roulette.calculateRoulette(randoms, accum, population);
    }

    private LinkedList<Double> getRandUniversal(int n){
        double rand = ThreadLocalRandom.current().nextDouble();
        LinkedList<Double> randoms = new LinkedList<>();
        for(int i =0; i<n; i++){
            randoms.add((rand + i - 1) / n);
        }
        return randoms;
    }

}
