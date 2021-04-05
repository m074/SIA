package ar.edu.itba.sia.Algorithm;

import ar.edu.itba.sia.Model.Item;
import ar.edu.itba.sia.Model.ItemType;
import ar.edu.itba.sia.utils.Config;
import ar.edu.itba.sia.Model.Character;
import ar.edu.itba.sia.utils.Plot;
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
    LinkedList<Double> maxFitnesses = new LinkedList<>();
    LinkedList<Double> averageFitnesses = new LinkedList<>();
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
            Mutation.mutate(childs,config.getMutationProbability(), config.getItemVariation(), config.getHeightVariation(), mutationType, items);

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
            System.out.println("Best fitness: " + bestFitness + '\n');
            this.maxFitnesses.add(bestFitness);
            this.averageFitnesses.add(population.stream().map(Character::getFitness).reduce(Double::sum).get()/population.size());
            try{
                double mean = get_mean_characters(population);
                double std = get_std_characters(population, mean);
                SaveData.writeFile("data.csv",
                        String.valueOf(generation-1),
                        String.valueOf(bestFitness),
                        String.valueOf(Collections.min(population).getFitness()),
                        String.valueOf(mean),
                        String.valueOf(std)
                );
            } catch (IOException e) {
                e.printStackTrace();
            }


            m.stopTime();
            totalTime += m.getTime();
            m.restartTime();


        }
        plot(maxFitnesses, "Max Fitness Through Generations");
        plot(averageFitnesses, "Average Fitness Through Generations");
        return Collections.max(population);
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
    private void plot(List<Double> fitnessValues, String title){
        Plot plot = new Plot(title);
        plot.plotValues(fitnessValues, title);
        plot.pack();
        plot.setVisible(true);
    }
    private boolean isFinished(long generation, long time, double bestFitness, LinkedList<Character> population, Config config){
        return maxGenerations(generation, config) || maxTime(time, config)
                || optimalFitness(config, bestFitness)
                || fitnessUnchanged(bestFitness, config)
                || populationUnchanged(population, config);
    }

    private boolean maxGenerations(long generation, Config config){
        if(generation >= config.getMaxGenerations()){
            System.out.println("Max generations limit reached.\n");
            return true;
        }
        return false;
    }

    private boolean maxTime(long time, Config config){
        if(time > config.getMaxTime()){
            System.out.println("Max time limit reached.\n");
            return true;
        }
        return false;
    }

    private boolean optimalFitness(Config config, double bestFitness){
        if(Math.abs(config.getAcceptableFitness() - bestFitness) < config.getFitnessMargin() || config.getAcceptableFitness() < bestFitness){
            System.out.println("Optimal fitness limit reached.\n");
            return true;
        }
        return false;
    }

    private boolean fitnessUnchanged(double bestFitness, Config config){
        if(Math.abs(bestFitness - this.lastFitness) > config.getFitnessMargin()){
            lastFitness = bestFitness;
            this.unchangedFitnessGens = 0;
        }else{
            this.unchangedFitnessGens++;
        }

        if(unchangedFitnessGens > config.getGensWithoutFitnessChange()){
            System.out.println("Limit of generations without fitness change reached.\n");
            return true;
        }
        return false;
    }

    private boolean populationUnchanged(LinkedList<Character> population, Config config){
        LinkedList<Character> repeated = new LinkedList<>(this.lastPopulation);
        repeated.retainAll(population); //mantengo los que se repitan
        double unchangedPercentage =  ((double)repeated.size()/(double) population.size());
        if(config.getUnchangedPopulationMargin() < unchangedPercentage){
            this.unchangedPopulationGens++;
            if(unchangedPopulationGens > config.getGensWithoutPopulationChange()){
                System.out.println("Limit of unchanged population reached.\n");
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


    private static double get_mean_characters(LinkedList<Character> population){
        double sum = 0;
        for(Character c: population){
            sum += c.getFitness();
        }
        return sum / population.size();
    }

    private static double get_std_characters(LinkedList<Character> population, double mean){
        double sum = 0;
        for(Character c: population){
            sum += Math.pow(c.getFitness() - mean, 2) ;
        }
        return Math.sqrt(sum / population.size());
    }
}
