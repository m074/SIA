package ar.edu.itba.sia.Algorithm;

import ar.edu.itba.sia.Model.Item;
import ar.edu.itba.sia.Model.ItemType;
import ar.edu.itba.sia.utils.Config;
import ar.edu.itba.sia.Model.Character;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GeneticAlgorithm {


    public static Character calculate(Config config, HashMap<ItemType, ArrayList<Item>> items){
        long generation= 0;
        LinkedList<Character> population = initialize(items, config);
        System.out.println("Initializing with best fitness " + Collections.max(population).getFitness());
        while(!isFinished(generation, config)) {

        }
        return population.getFirst(); //eliminar esto y desarrollar algoritmos vvv
    }

    //inicializa la poblacion con initialPopulation obtenido del archivo de config
    private static LinkedList<Character> initialize(HashMap<ItemType, ArrayList<Item>> items, Config config){
        LinkedList<Character> population = new LinkedList<>();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int weaponAmount = items.get(ItemType.WEAPON).size();
        int bootsAmount = items.get(ItemType.BOOTS).size();
        int helmetAmount = items.get(ItemType.WEAPON).size();
        int glovesAmount = items.get(ItemType.GLOVES).size();
        int vestAmount = items.get(ItemType.VEST).size();

        long initialSize = config.getInitialPopulation();
        for(int i = 0; i<initialSize;i++){
            population.add(new Character(
                items.get(ItemType.WEAPON).get(random.nextInt(0, weaponAmount)),
                items.get(ItemType.BOOTS).get(random.nextInt(0, bootsAmount)),
                items.get(ItemType.HELMET).get(random.nextInt(0, helmetAmount)),
                items.get(ItemType.GLOVES).get(random.nextInt(0, glovesAmount)),
                items.get(ItemType.VEST).get(random.nextInt(0, vestAmount)),
                config.getCharacterClass()
            )
            );
        }
        return population;
    }

    public static boolean isFinished(long generation, Config config){
        return generation >= config.getMaxGenerations();
    }
}
