package ar.edu.itba.sia.Algorithm;

import ar.edu.itba.sia.Model.Character;
import ar.edu.itba.sia.Model.ItemType;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class TwoPointCross extends Cross {

    @Override
    public LinkedList<Character> cross(Character ch1, Character ch2) {
        //5 items + height
        int LOCUS_QTY = 6;
        int pointA = ThreadLocalRandom.current().nextInt(0, LOCUS_QTY - 1);
        int pointB = ThreadLocalRandom.current().nextInt(pointA, LOCUS_QTY);
        ItemType[] itemTypes = ItemType.values();
        Character child1 = ch1.clone();
        Character child2 = ch2.clone();

        for (int i = pointA; i < pointB; i++) {
            if(i == LOCUS_QTY)
                swapHeight(child1, child2);
            else
                swapItem(child1, child2, itemTypes[i]);
        }
        return new LinkedList<>(Arrays.asList(child1, child2));
    }
}