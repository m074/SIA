package ar.edu.itba.sia.Algorithm;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import ar.edu.itba.sia.Model.Character;

//Library for selection functions
public class Selections {

    public static LinkedList<Double> getRandoms(int n){
        LinkedList<Double> randoms = new LinkedList<>();
        Random r = ThreadLocalRandom.current();
        for(int i = 0; i<n; i++)
            randoms.add(r.nextDouble());
        return randoms;
    }

    public static LinkedList<Double> getFitnessAccum(LinkedList<Character> population){
        LinkedList<Double> accum = new LinkedList<>();
        int n = population.size();
        for(int i = 0; i<n; i++){
            accum.add(population.get(i).getFitness() + (i==0 ? 0.0 : accum.get(i-1)));
        }
        double total = accum.get(accum.size() - 1);
        return accum.stream().map(a -> a /= total).collect(Collectors.toCollection(LinkedList::new));
    }
}
