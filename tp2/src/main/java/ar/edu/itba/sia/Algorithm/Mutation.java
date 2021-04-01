package ar.edu.itba.sia.Algorithm;

import ar.edu.itba.sia.Model.Character;
import ar.edu.itba.sia.Model.Item;
import ar.edu.itba.sia.Model.ItemType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Mutation {
    public static void mutate(LinkedList<Character> population, double probability, long generations, MutationType type){

        for(Character c: population){
            ArrayList genes = new ArrayList();
            genes.add(c.getItem(ItemType.WEAPON));
            genes.add(c.getItem(ItemType.BOOTS));
            genes.add(c.getItem(ItemType.HELMET));
            genes.add(c.getItem(ItemType.GLOVES));
            genes.add(c.getItem(ItemType.VEST));
            genes.add(new Double(c.getHeight()));

            mutate(genes, type, probability, c);
        }
        return;
    }



    private static void mutate(ArrayList genes, MutationType type, double probability, Character c){

        if (type == MutationType.GEN){
            Collections.shuffle(genes);

            double rand = Math.random();

            if(rand <= probability) {
                if (genes.get(0).getClass() == Double.class) {
                    c.setHeight(getRandoHeight());

                } else {
                    Item item = (Item) genes.get(0);
                    c.setItem(getRandomItem(item.getType(), item.getId()));

                }
            }
        }
        if (type == MutationType.LIMITEDMULTI) {
            Collections.shuffle(genes);
            int limitMut = ThreadLocalRandom.current().nextInt(1, genes.size() + 1);

            for(int i = 1; i<= limitMut; i++){
                double rand = Math.random();
                if(rand <= probability) {
                    if (genes.get(0).getClass() == Double.class) {
                        c.setHeight(getRandoHeight());
                    } else {
                        Item item = (Item) genes.get(0);
                        c.setItem(getRandomItem(item.getType(), item.getId()));
                    }
                }
            }

        }
        if (type == MutationType.UNIFORMMULTI) {
            for(int i = 1; i<= genes.size(); i++){
                double rand = Math.random();
                if(rand <= probability) {
                    if (genes.get(0).getClass() == Double.class) {
                        c.setHeight(getRandoHeight());
                    } else {
                        Item item = (Item) genes.get(0);
                        c.setItem(getRandomItem(item.getType(), item.getId()));
                    }
                }
            }
        }
        if (type == MutationType.COMPLETE) {
            double rand = Math.random();
            if(rand <= probability) {
                for(int i = 1; i<= genes.size(); i++){
                        if (genes.get(0).getClass() == Double.class) {
                            c.setHeight(getRandoHeight());
                        } else {
                            Item item = (Item) genes.get(0);
                            c.setItem(getRandomItem(item.getType(), item.getId()));
                        }
                }
            }
        }

    }



    //values ∈ [0 − 50] ??
    public static Item getRandomItem(ItemType type, long id){

        Random r = new Random();
        Item newItem = new Item(r.nextDouble()*50,r.nextDouble()*50,r.nextDouble()*50,r.nextDouble()*50,r.nextDouble()*50,type, id);

        return newItem;
    }

    //h ∈ [1,3m − 2,0m]
    public static Double getRandoHeight(){
        Random r = new Random();
        double newHeight = r.nextDouble()*0.7 + 1.3;
        return newHeight;
    }


}
