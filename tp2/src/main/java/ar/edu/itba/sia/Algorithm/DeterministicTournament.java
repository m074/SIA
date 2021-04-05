package ar.edu.itba.sia.Algorithm;

import ar.edu.itba.sia.Model.Character;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DeterministicTournament extends SelectionMethod{
    private long m_value;

    public DeterministicTournament(long m_value) {
        this.m_value = m_value;
    }

    @Override
    public LinkedList<Character> select(LinkedList<Character> population, int size, long generations) {
        if(m_value >= size)
            m_value = size - 1;
        LinkedList<Character> selected = new LinkedList<>();
        Random rand = ThreadLocalRandom.current();
        for(int i=0; i<m_value; i++){
            double max_fitness = -1;
            int pos_max_fitness = -1;
            HashSet<Integer> positions = new HashSet<>();
            for(int j=0; j<size; j++){
                int pos_char = -1;
                while(pos_char==-1 || positions.contains(pos_char)){
                    pos_char = rand.nextInt(population.size());
                }
                positions.add(pos_char);
                Character character = population.get(pos_char);
                if(max_fitness< character.getFitness()){
                    max_fitness = character.getFitness();
                    pos_max_fitness = pos_char;
                }
            }
            selected.add(population.get(pos_max_fitness));
            population.remove(pos_max_fitness);
        }

        return selected;
    }



}
