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



    public static void mutate(LinkedList<Character> population, double probability, double itemVariation, double heightVariation, MutationType type){


        for(Character c: population){
            ArrayList genes = new ArrayList();
            genes.add(c.getItem(ItemType.WEAPON));
            genes.add(c.getItem(ItemType.BOOTS));
            genes.add(c.getItem(ItemType.HELMET));
            genes.add(c.getItem(ItemType.GLOVES));
            genes.add(c.getItem(ItemType.VEST));
            genes.add(new Double(c.getHeight()));

            mutate(genes, type, probability, c, itemVariation, heightVariation);
        }
        return;
    }



    private static void mutate(ArrayList genes, MutationType type, double probability, Character c, double itemVar, double heightVar){

        if (type == MutationType.GEN){
            Collections.shuffle(genes);

            double rand = Math.random();

            if(rand <= probability) {
                if (genes.get(0).getClass() == Double.class) {
                    Double oldH = new Double((Double) genes.get(0));
                    c.setHeight(getRandomHeight(oldH, heightVar));
                } else {
                    Item item = (Item) genes.get(0);
                    c.setItem(getRandomItem(item, itemVar));

                }
            }
        }
        if (type == MutationType.LIMITEDMULTI) {
            Collections.shuffle(genes);
            int limitMut = ThreadLocalRandom.current().nextInt(1, genes.size() + 1);

            for(int i = 0; i< limitMut; i++){
                double rand = Math.random();
                if(rand <= probability) {
                    if (genes.get(i).getClass() == Double.class) {
                        Double oldH = new Double((Double) genes.get(i));
                        c.setHeight(getRandomHeight(oldH, heightVar));
                    } else {
                        Item item = (Item) genes.get(i);
                        c.setItem(getRandomItem(item, itemVar));
                    }
                }
            }

        }
        if (type == MutationType.UNIFORMMULTI) {
            for(int i = 0; i< genes.size(); i++){
                double rand = Math.random();
                if(rand <= probability) {
                    if (genes.get(i).getClass() == Double.class) {
                        Double oldH = new Double((Double) genes.get(i));
                        c.setHeight(getRandomHeight(oldH, heightVar));
                    } else {
                        Item item = (Item) genes.get(i);
                        c.setItem(getRandomItem(item, itemVar));
                    }
                }
            }
        }
        if (type == MutationType.COMPLETE) {
            double rand = Math.random();
            if(rand <= probability) {
                for(int i = 0; i< genes.size(); i++){
                        if (genes.get(i).getClass() == Double.class) {
                            Double oldH = new Double((Double) genes.get(i));
                            c.setHeight(getRandomHeight(oldH, heightVar));
                        } else {
                            Item item = (Item) genes.get(i);
                            c.setItem(getRandomItem(item, itemVar));
                        }
                }
            }
        }

    }



    //+- variation mutation
    public static Item getRandomItem(Item oldItem, double variation){
        Random r = new Random();
        double str = oldItem.getStrength()-variation + ((oldItem.getStrength()+variation) - (oldItem.getStrength()-variation)) * r.nextDouble();
        double agi = oldItem.getAgility()-variation + ((oldItem.getAgility()+variation) - (oldItem.getAgility()-variation)) * r.nextDouble();
        double exp = oldItem.getExpertise()-variation + ((oldItem.getExpertise()+variation) - (oldItem.getExpertise()-variation)) * r.nextDouble();
        double res = oldItem.getResistance()-variation + ((oldItem.getResistance()+variation) - (oldItem.getResistance()-variation)) * r.nextDouble();
        double vit = oldItem.getVitality()-variation + ((oldItem.getVitality()+variation) - (oldItem.getVitality()-variation)) * r.nextDouble();

        Item newItem = new Item(str>0?str:0,agi>0?str:0,exp>0?str:0,res>0?str:0,vit>0?str:0,oldItem.getType(), oldItem.getId());

        return newItem;
    }

    //+- variation mutation
    public static Double getRandomHeight(double oldHeight, double variation){
        Random r = new Random();
        double min = oldHeight - variation;
        double max = oldHeight + variation;
        double newHeight = min + (max - min) * r.nextDouble();

        if(newHeight < 1.3)
            newHeight = 1.3;
        if(newHeight > 2.0)
            newHeight = 2.0;

        return newHeight;
    }




}
