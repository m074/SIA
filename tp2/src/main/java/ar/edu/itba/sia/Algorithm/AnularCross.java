package ar.edu.itba.sia.Algorithm;

import ar.edu.itba.sia.Model.Character;
import ar.edu.itba.sia.Model.ItemType;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class AnularCross extends Cross {

    @Override
    public LinkedList<Character> cross(Character ch1, Character ch2) {
        //5 items + height
        int LOCUS_QTY = 6;
        int point = ThreadLocalRandom.current().nextInt(0, LOCUS_QTY-1);
        int length = ThreadLocalRandom.current().nextInt(0, LOCUS_QTY-2);
        ItemType[] itemTypes = ItemType.values();
        Character child1 = ch1.clone();
        Character child2 = ch2.clone();

        for (int i = point; i <= point+length; i++) {
            if(i%(LOCUS_QTY) != LOCUS_QTY-1)
                swapItem(child1, child2, itemTypes[i]);
            else
                swapHeight(child1, child2);
        }
        return new LinkedList<>(Arrays.asList(child1, child2));
    }
}
