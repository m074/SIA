package ar.edu.itba.sia.Algorithm;

import ar.edu.itba.sia.Model.Character;

import java.util.Collections;
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
        LinkedList<Character> selected = new LinkedList<>();
        if(m_value >= size)
            m_value = size;
        for(int i =0; i<size; i++){
            LinkedList<Character> tournament = new LinkedList<>();
            for(int j =0; j <  m_value; j++){
                tournament.add(population.get(ThreadLocalRandom.current().nextInt(population.size())));
            }
            tournament.sort(Collections.reverseOrder());
            selected.add(tournament.get(0));
        }
        return selected;
    }



}
