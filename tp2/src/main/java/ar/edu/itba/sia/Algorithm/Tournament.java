package ar.edu.itba.sia.Algorithm;

import ar.edu.itba.sia.Model.Character;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Tournament extends SelectionMethod{
    private double t_threshold;

    public Tournament(double tournamentThreshold) {
        this.t_threshold = tournamentThreshold;
    }

    @Override
    public LinkedList<Character> select(LinkedList<Character> population, int size, long generations) {
        LinkedList<Character> selected = new LinkedList<>();
        for(int i=0; i<size; i++){
            Random rand = ThreadLocalRandom.current();
            int pos_c1 = rand.nextInt(population.size());
            int pos_c2 = rand.nextInt(population.size());
            Character c1 = population.get(pos_c1);
            Character c2 = population.get(pos_c2);
            double r = rand.nextDouble();
            if(c1.getFitness() < c2.getFitness()){ // Coloco al de mejor fitness en c1
                Character cache_character = c2;
                int pos_cache = pos_c2;
                c2 = c1;
                c1 = cache_character;
                pos_c2 = pos_c1;
                pos_c1 =  pos_cache;
            }
            if(r < this.t_threshold){ //selecciona el mas apto
                selected.add(c1);
                population.remove(pos_c1);
            }else{
                selected.add(c2);
                population.remove(pos_c2);
            }

        }

        return selected;
    }



}
