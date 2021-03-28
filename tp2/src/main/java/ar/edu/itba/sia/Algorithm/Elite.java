package ar.edu.itba.sia.Algorithm;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import ar.edu.itba.sia.Model.Character;

public class Elite extends SelectionMethod {

    //Selects individuals with best fitness
    @Override
    public LinkedList<Character> select(LinkedList<Character> population, int size, long generations){
        LinkedList<Character> aux = new LinkedList<>(population);
        aux.sort(Comparator.reverseOrder());
        return new LinkedList<>(aux.subList(0, size));
    }
}
