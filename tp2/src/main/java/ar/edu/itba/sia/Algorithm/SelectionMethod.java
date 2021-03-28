package ar.edu.itba.sia.Algorithm;

import java.util.LinkedList;
import ar.edu.itba.sia.Model.Character;

public abstract class SelectionMethod {

    public abstract LinkedList<Character> select(LinkedList<Character> population, int size, long generations);

}
