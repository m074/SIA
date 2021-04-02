package ar.edu.itba.sia.Algorithm;

import ar.edu.itba.sia.Model.Item;
import ar.edu.itba.sia.Model.ItemType;
import ar.edu.itba.sia.utils.Config;
import ar.edu.itba.sia.Model.Character;
import com.sun.org.apache.bcel.internal.generic.Select;

import javax.security.auth.login.Configuration;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.floor;

public class GeneticAlgorithm {


    public static Character calculate(Config config, HashMap<ItemType, ArrayList<Item>> items){
        long generation= 0;
        LinkedList<Character> population = initialize(items, config);
        System.out.println("Initializing with best fitness " + Collections.max(population).getFitness());

        //
        //
        double selectionAPerc = config.getSelectionMethodAPercentage();
        double replaceAPerc = config.getReplacementMethodAPercentage();

        SelectionMethod selectionA = config.getSelectionMethodA();
        SelectionMethod selectionB = config.getSelectionMethodA();

        SelectionMethod replaceA = config.getReplacementMethodA();
        SelectionMethod replaceB = config.getReplacementMethodB();

        Cross crossOverMethod= config.getCrossOverMethod();

        MutationType mutationType = config.getMutationType();
        ImplementationOption implementationOption = config.getImplementationOption();

        int selA =(int) floor(population.size()*selectionAPerc);
        int selB = population.size() - selA;
        int repA =(int) floor(population.size()*replaceAPerc);
        int repB = population.size() - repA;



        while(!isFinished(generation, config)) {
            //cross
            LinkedList<Character> childs = crossOverMethod.crossOver(population, config);
            //mutate childs
            Mutation.mutate(childs,config.getMutationProbability(), config.getItemVariation(), config.getHeightVariation(), mutationType);

            LinkedList<Character> newGen = new LinkedList<>();

            if(implementationOption == ImplementationOption.FILLALL){
                //selected fathers
                LinkedList<Character> selected = new LinkedList<>();
                selected.addAll(selectionA.select(population, selA, generation));
                selected.addAll(selectionB.select(population, selB, generation));

                //add all childs
                selected.addAll(childs);

                //select replacements
                newGen.addAll(replaceA.select(selected, repA, generation));
                newGen.addAll(replaceB.select(selected, repB, generation));

                population = newGen;
            }
            else{
                population = childs;
                newGen.addAll(replaceA.select(population, repA, generation));
                newGen.addAll(replaceB.select(population, repB, generation));
            }
            population = newGen;
            generation +=1;

            System.out.println(Collections.max(population).getFitness());
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

    public LinkedList<Character> newGeneration(Config config, LinkedList<Character> population){
        //selecciono padres
        SelectionMethod methodA = config.getSelectionMethodA();
        SelectionMethod methodB = config.getReplacementMethodB();
        //cruzo con crossover
        //muto
        //selecciono hijos (reemplazo)
        return null; //cuando termine saco esto
    }
}
