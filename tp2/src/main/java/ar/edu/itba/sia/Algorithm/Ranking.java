package ar.edu.itba.sia.Algorithm;

import ar.edu.itba.sia.Model.Character;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;


public class Ranking extends SelectionMethod{
    @Override
    public LinkedList<Character> select(LinkedList<Character> population, int size, long generations) {

        LinkedList<Character> sorted = new LinkedList<>(population);
        sorted.sort(Comparator.reverseOrder());

        LinkedList<Double> pFitness = new LinkedList<>();

        Double pfSum = 0.0;
        double rank = 1;
        for(Character c: sorted){
            Double pf = (population.size() - rank+1)/size;
            pfSum += pf;
            pFitness.add(pf);
            rank +=1;
        }

        LinkedList<Character> selected = new LinkedList<>();
        LinkedList<Double> randoms = Selections.getRandoms(size);
        LinkedList<Double> accum = new LinkedList<>();

        Double auxacc = 0.0;
        for(int i=0; i<pFitness.size(); i++){
            Double pfRel = pFitness.get(i)/pfSum;
            auxacc += pfRel;
            accum.add(auxacc);
        }

        for(Double r: randoms){
            Double lastAccum = 0.0;
            for(int i=0; i<accum.size(); i++){
                Double currAcum = accum.get(i);
                if(r > lastAccum && r < currAcum){
                    selected.add(sorted.get(i));
                    break;
                }
                lastAccum = currAcum;
            }
        }
        return selected;
    }


}









