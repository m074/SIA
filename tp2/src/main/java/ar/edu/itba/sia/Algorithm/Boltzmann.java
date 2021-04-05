package ar.edu.itba.sia.Algorithm;

import ar.edu.itba.sia.Model.Character;

import java.util.LinkedList;

public class Boltzmann extends SelectionMethod{

    double k = 0.1;

    @Override
    public LinkedList<Character> select(LinkedList<Character> population, int size, long generations) {

        //tc 1 - to 100
        double temp = 1+(100-1)*Math.exp((-k*1)*generations);
        double avgPop = 0.0;
      
        for(Character c: population){
            avgPop += Math.exp(c.getFitness()/temp);
        }
        avgPop = avgPop/population.size();

        LinkedList<Double> boltFit = new LinkedList<>();

        double bfsum = 0.0;
        for(Character c: population){
            double bfit = Math.exp(c.getFitness()/temp)/avgPop;
            boltFit.add(bfit);
            bfsum += bfit;
        }

        LinkedList<Character> selected = new LinkedList<>();
        LinkedList<Double> randoms = Selections.getRandoms(size);
        LinkedList<Double> accum = new LinkedList<>();

        Double auxacc = 0.0;
        for(int i=0; i<boltFit.size(); i++){
            Double pfRel = boltFit.get(i)/bfsum;
            auxacc += pfRel;
            accum.add(auxacc);
        }

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
