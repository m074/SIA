package ar.edu.itba.sia.Algorithm;

import ar.edu.itba.sia.Model.Item;
import ar.edu.itba.sia.Model.ItemType;
import ar.edu.itba.sia.utils.Config;
import ar.edu.itba.sia.Model.Character;
import ar.edu.itba.sia.utils.SaveData;
import ar.edu.itba.sia.utils.TimeMetric;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.floor;

public class GeneticAlgorithm {




    double lastFitness = 0.0;
    long unchangedFitnessGens = 0;
    long unchangedPopulationGens = 0;

    LinkedList<Character> lastPopulation = new LinkedList<Character>();

    public Character calculate(Config config, HashMap<ItemType, ArrayList<Item>> items){

        long generation= 0;
        LinkedList<Character> population = initialize(items, config);
        double bestFitness = Collections.max(population).getFitness();
        System.out.println("Initializing with best fitness " + bestFitness);

        //
        //
        double selectionAPerc = config.getSelectionMethodAPercentage();
        double replaceAPerc = config.getReplacementMethodAPercentage();

        SelectionMethod selectionA = config.getSelectionMethodA();
        SelectionMethod selectionB = config.getSelectionMethodB();

        SelectionMethod replaceA = config.getReplacementMethodA();
        SelectionMethod replaceB = config.getReplacementMethodB();

        Cross crossOverMethod= config.getCrossOverMethod();

        MutationType mutationType = config.getMutationType();
        ImplementationOption implementationOption = config.getImplementationOption();

        int selA =(int) floor(population.size()*selectionAPerc);
        int selB = population.size() - selA;
        int repA =(int) floor(population.size()*replaceAPerc);
        int repB = population.size() - repA;


        TimeMetric m = new TimeMetric();
        long totalTime = 0L;


        try{
            SaveData.createFile("data.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }


        while(!isFinished(generation, totalTime, bestFitness, population, config)) {
            m.startTime();
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
            bestFitness = Collections.max(population).getFitness();
            System.out.println(bestFitness);

            try{
                SaveData.writeFile("data.csv", String.valueOf(generation-1), String.valueOf(bestFitness),String.valueOf(Collections.min(population).getFitness()));
            } catch (IOException e) {
                e.printStackTrace();
            }


            m.stopTime();
            totalTime += m.getTime();
            lastPopulation = population;
        }
        return population.getFirst(); //eliminar esto y desarrollar algoritmos vvv
    }

    //inicializa la poblacion con initialPopulation obtenido del archivo de config
    private LinkedList<Character> initialize(HashMap<ItemType, ArrayList<Item>> items, Config config){
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

    private boolean isFinished(long generation, long time, double bestFitness, LinkedList<Character> population, Config config){
        return generation >= config.getMaxGenerations() || time > config.getMaxTime()
                || Math.abs(config.getAcceptableFitness() - bestFitness) < config.getFitnessMargin()
                || fitnessUnchanged(bestFitness, config)
                || populationUnchanged(population, config);
    }

    private boolean fitnessUnchanged(double bestFitness, Config config){
        if(bestFitness > this.lastFitness){
            lastFitness = bestFitness;
            this.unchangedFitnessGens = 0;
        }else{
            this.unchangedFitnessGens++;
        }

        if(unchangedFitnessGens > config.getGensWithoutFitnessChange()){
            return true;
        }
        return false;
    }

    private boolean populationUnchanged(LinkedList<Character> population, Config config){
        LinkedList<Character> repeated = new LinkedList<>(this.lastPopulation);
        repeated.retainAll(population); //mantengo los que se repitan
        double unchangedPercentage = 1.0 - ((double)repeated.size()/population.size());
        if(config.getUnchangedPopulationMargin() > unchangedPercentage){
            this.unchangedPopulationGens++;
            if(unchangedPopulationGens > config.getGensWithoutPopulationChange()){
                return true;
            }
        }
        else
        {
            this.unchangedPopulationGens = 0;
        }
        this.lastPopulation = new LinkedList<>(population);
        return false;
    }

}
