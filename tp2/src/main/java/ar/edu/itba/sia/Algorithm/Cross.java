package ar.edu.itba.sia.Algorithm;

import ar.edu.itba.sia.Model.Character;
import ar.edu.itba.sia.Model.Item;
import ar.edu.itba.sia.Model.ItemType;
import ar.edu.itba.sia.utils.Config;

import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Cross {

    public abstract LinkedList<Character> cross(Character ch1, Character ch2); //one point, two point, etc

    public LinkedList<Character> crossOver(LinkedList<Character> selected, Config config){ //cross individuals in population
        LinkedList<Character> children = new LinkedList<>();
        Collections.shuffle(selected);
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int size = selected.size();

        for(int i = 0; i < size/2; i++){
            if(rand.nextDouble(0, 1) < config.getCrossOverProbability()){   //probability to cross
                LinkedList<Character> cross = cross(selected.get(i).clone(), selected.get(size-i-1).clone());
                children.addAll(cross); //add crossed children
            }else{
                children.add(selected.get(i).clone());
                children.add(selected.get(size-i-1).clone());
            }
        }
        return children;
    }

    public void swapHeight(Character ch1, Character ch2){
        double aux = ch1.getHeight();
        ch1.setHeight(ch2.getHeight());
        ch2.setHeight(aux);
    }

    public void swapItem(Character ch1, Character ch2, ItemType type){
        Item aux = ch1.getItem(type);
        ch1.setItem(ch2.getItem(type));
        ch2.setItem(aux);
    }
}
