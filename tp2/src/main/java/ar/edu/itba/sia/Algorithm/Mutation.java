package ar.edu.itba.sia.Algorithm;

import ar.edu.itba.sia.Model.Character;
import ar.edu.itba.sia.Model.Item;
import ar.edu.itba.sia.Model.ItemType;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Mutation {



    public static void mutate(LinkedList<Character> population, double probability, int itemVariation, double heightVariation, MutationType type, HashMap<ItemType, ArrayList<Item>> items){


        for(Character c: population){
            ArrayList genes = new ArrayList();
            genes.add(c.getItem(ItemType.WEAPON));
            genes.add(c.getItem(ItemType.BOOTS));
            genes.add(c.getItem(ItemType.HELMET));
            genes.add(c.getItem(ItemType.GLOVES));
            genes.add(c.getItem(ItemType.VEST));
            genes.add(c.getHeight());
            mutate(genes, type, probability, c, itemVariation, heightVariation, items);
        }
        return;
    }



    private static void mutate(ArrayList genes, MutationType type, double probability, Character c, int itemVar, double heightVar, HashMap<ItemType, ArrayList<Item>> items){
        Random random_generator = ThreadLocalRandom.current();
        if (type == MutationType.GEN){
            Collections.shuffle(genes);

            double rand = random_generator.nextDouble();

            if(rand <= probability) {
                if (genes.get(0).getClass() == Double.class) {
                    Double oldH = (Double) genes.get(0);
                    c.setHeight(getRandomHeight(oldH, heightVar));
                } else {
                    Item item = (Item) genes.get(0);
                    c.setItem(getRandomItem(item, itemVar, items));

                }
            }
        }
        if (type == MutationType.LIMITEDMULTI) {
            Collections.shuffle(genes);
            int limitMut = ThreadLocalRandom.current().nextInt(1, genes.size() + 1);

            for(int i = 0; i< limitMut; i++){
                double rand = random_generator.nextDouble();
                if(rand <= probability) {
                    if (genes.get(i).getClass() == Double.class) {
                        Double oldH = (Double) genes.get(i);
                        c.setHeight(getRandomHeight(oldH, heightVar));
                    } else {
                        Item item = (Item) genes.get(i);
                        c.setItem(getRandomItem(item, itemVar, items));
                    }
                }
            }

        }
        if (type == MutationType.UNIFORMMULTI) {
            for(int i = 0; i< genes.size(); i++){
                double rand = random_generator.nextDouble();
                if(rand <= probability) {
                    if (genes.get(i).getClass() == Double.class) {
                        Double oldH = (Double) genes.get(i);
                        c.setHeight(getRandomHeight(oldH, heightVar));
                    } else {
                        Item item = (Item) genes.get(i);
                        c.setItem(getRandomItem(item, itemVar, items));
                    }
                }
            }
        }
        if (type == MutationType.COMPLETE) {
            double rand = random_generator.nextDouble();
            if(rand <= probability) {
                for(int i = 0; i< genes.size(); i++){
                        if (genes.get(i).getClass() == Double.class) {
                            Double oldH = (Double) genes.get(i);
                            c.setHeight(getRandomHeight(oldH, heightVar));
                        } else {
                            Item item = (Item) genes.get(i);
                            c.setItem(getRandomItem(item, itemVar, items));
                        }
                }
            }
        }

    }



    //+- variation mutation
    public static Item getRandomItem(Item oldItem, int variation, HashMap<ItemType, ArrayList<Item>> items){
        ItemType type = oldItem.getType();
        return(items.get(type).get((int) ((oldItem.getId() + variation) % items.get(type).size())));
    }

    //+- variation mutation

    public static Double getRandomHeight(double oldHeight, double variation){
        Random rand = ThreadLocalRandom.current();
        double min = oldHeight - variation;
        double max = oldHeight + variation;
        double newHeight = min + (max - min) * rand.nextDouble();

        if(newHeight < 1.3)
            newHeight = 1.3;
        if(newHeight > 2.0)
            newHeight = 2.0;

        return newHeight;
    }




}
