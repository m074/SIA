package ar.edu.itba.sia.Algorithm;

import ar.edu.itba.sia.Model.Character;
import ar.edu.itba.sia.Model.ItemType;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class UniformCross extends Cross {

    @Override
    public LinkedList<Character> cross(Character ch1, Character ch2){

        ThreadLocalRandom rand = ThreadLocalRandom.current();
        ItemType[] itemTypes = ItemType.values();

        Character child1 = ch1.clone();
        Character child2 = ch2.clone();

        for(int i=0; i<=itemTypes.length; i++) {
            if (rand.nextDouble(0, 1) >= 0.5) {
                swapItem(child1, child2, itemTypes[i]);
            }
        }
        if(rand.nextDouble(0,1) >= 0.5){
            swapHeight(child1, child2);
        }

        return new LinkedList<>(Arrays.asList(child1, child2));
    }
}
