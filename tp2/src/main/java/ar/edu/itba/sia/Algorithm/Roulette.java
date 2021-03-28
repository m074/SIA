package ar.edu.itba.sia.Algorithm;

import java.util.Comparator;
import ar.edu.itba.sia.Model.Character;
import java.util.LinkedList;
import java.util.Random;

public class Roulette extends SelectionMethod {

    @Override
    public  LinkedList<Character> select(LinkedList<Character> population, int size, long generations){
        LinkedList<Double> randoms = Selections.getRandoms(size);
        LinkedList<Double> accum = Selections.getFitnessAccum(population);
        return calculateRoulette(randoms, accum, population);

    }

    public static LinkedList<Character> calculateRoulette(LinkedList<Double> randoms, LinkedList<Double> accum, LinkedList<Character> population){
        LinkedList<Character> selected = new LinkedList<>();
        for(Double r: randoms){
            Double lastAccum = 0.0;
            for(int i=0; i<accum.size(); i++){
                Double currAcum = accum.get(i);
                if(r > lastAccum && r < currAcum){
                    selected.add(population.get(i));
                    break;
                }
                lastAccum = currAcum;
            }
        }
        return selected;
    }
}
