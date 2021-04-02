package ar.edu.itba.sia.utils;


import ar.edu.itba.sia.Algorithm.*;
import ar.edu.itba.sia.Model.CharacterClass;

import java.util.Properties;

public class Config {
    private long initialPopulation;
    private CharacterClass characterClass;
    private long maxGenerations;
    private double crossOverProbability;
    private String selectionMethodA;
    private String selectionMethodB;
    private String replacementMethodA;
    private String replacementMethodB;
    private double selectionMethodAPercentage;
    private double replacementMethodAPercentage;
    private String crossOverMethod;
    private String mutationType;
    private String implementation;
    private long maxTime;
    private double itemVariation;
    private double heightVariation;
    private double mutationProbability;
    private double acceptableFitness;
    private double fitnessMargin;
    private long gensWithoutFitnessChange;
    private double unchangedPopulationMargin;
    private long gensWithoutPopulationChange;
    public Config(Properties props){
        this.initialPopulation = Long.parseLong(props.getProperty("initialPopulation"));
        this.characterClass = getCharClass(props.getProperty("characterClass"));
        this.maxGenerations = Long.parseLong(props.getProperty("maxGenerations"));
        this.crossOverProbability = Double.parseDouble(props.getProperty("crossOverProbability"));
        this.selectionMethodA = props.getProperty("selectionA");
        this.selectionMethodB = props.getProperty("selectionB");
        this.replacementMethodA = props.getProperty("replacementA");
        this.replacementMethodB = props.getProperty("replacementB");
        this.selectionMethodAPercentage = Double.parseDouble(props.getProperty("selectionMethodAPercentage"));
        this.replacementMethodAPercentage = Double.parseDouble(props.getProperty("replacementMethodAPercentage"));
        this.crossOverMethod = props.getProperty("crossover");
        this.mutationType = props.getProperty("mutation");
        this.implementation = props.getProperty("implementation");
        this.maxTime = Long.parseLong(props.getProperty("time"));
        this.itemVariation = Double.parseDouble(props.getProperty("itemVariation"));
        this.heightVariation = Double.parseDouble(props.getProperty("heightVariation"));
        this.mutationProbability = Double.parseDouble(props.getProperty("mutationProbability"));
        this.acceptableFitness = Double.parseDouble(props.getProperty("acceptableFitness"));
        this.fitnessMargin = Double.parseDouble(props.getProperty("fitnessMargin"));
        this.gensWithoutFitnessChange = Long.parseLong(props.getProperty("gensWithoutFitnessChange"));
        this.unchangedPopulationMargin = Double.parseDouble(props.getProperty("unchangedPopulationMargin"));
        this.gensWithoutPopulationChange = Long.parseLong(props.getProperty("gensWithoutPopulationChange"));
    }

    public CharacterClass getCharClass(String charClass){
        switch(charClass){
            case "warrior":
                return CharacterClass.WARRIOR;
            case "archer":
                return CharacterClass.ARCHER;
            case "infiltrator":
                return CharacterClass.INFILTRATOR;
            default:return CharacterClass.DEFENDER;
        }
    }

    public long getInitialPopulation() {
        return initialPopulation;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public long getMaxGenerations(){
        return maxGenerations;
    }

    public long getMaxTime(){
        return maxTime;
    }

    public double getCrossOverProbability(){
        return crossOverProbability;
    }

    public double getMutationProbability(){
        return mutationProbability;
    }

    public SelectionMethod getSelectionMethodA(){
        switch(this.selectionMethodA){
            case "roulette": return new Roulette();
            case "elite": return new Elite();
            case "universal": return new Universal();
            case "ranking": return new Ranking();
            case "boltzmann": return new Boltzmann();
            default:return null;
        }
    }

    public SelectionMethod getSelectionMethodB(){
        switch(this.selectionMethodB){
            case "roulette": return new Roulette();
            case "elite": return new Elite();
            case "universal": return new Universal();
            case "ranking": return new Ranking();
            case "boltzmann": return new Boltzmann();
            default:return null;
        }
    }

    public SelectionMethod getReplacementMethodA(){
        switch(this.replacementMethodA){
            case "roulette": return new Roulette();
            case "elite": return new Elite();
            case "universal": return new Universal();
            case "ranking": return new Ranking();
            case "boltzmann": return new Boltzmann();
            default:return null;
        }
    }

    public SelectionMethod getReplacementMethodB(){
        switch(this.replacementMethodB){
            case "roulette": return new Roulette();
            case "elite": return new Elite();
            case "universal": return new Universal();
            case "ranking": return new Ranking();
            case "boltzmann": return new Boltzmann();
            default:return null;
        }
    }

    public Cross getCrossOverMethod(){
        switch(this.crossOverMethod){
            case "onepointcross": return new OnePointCross();
            case "twopointcross": return new TwoPointCross();
            case "anularcross": return new AnularCross();
            case "uniformcross": return new UniformCross();
            default:return null;
        }
    }

    public MutationType getMutationType(){
        switch(this.mutationType){
            case "gen": return MutationType.GEN;
            case "limitedmulti": return MutationType.LIMITEDMULTI;
            case "uniformmulti": return MutationType.UNIFORMMULTI;
            case "complete": return MutationType.COMPLETE;
            default:return null;
        }
    }

    public double getItemVariation(){
        return itemVariation;
    }

    public double getHeightVariation(){
        return heightVariation;
    }

    public ImplementationOption getImplementationOption(){
        switch(this.implementation){
            case "fillall": return ImplementationOption.FILLALL;
            case "fillparent": return ImplementationOption.FILLPARENT;
            default:return null;
        }
    }

    public double getSelectionMethodAPercentage(){
        return this.selectionMethodAPercentage;
    }

    public double getReplacementMethodAPercentage(){
        return this.replacementMethodAPercentage;
    }

    public double getAcceptableFitness() {
        return acceptableFitness;
    }

    public double getFitnessMargin() {
        return fitnessMargin;
    }

    public long getGensWithoutFitnessChange() {
        return gensWithoutFitnessChange;
    }

    public double getUnchangedPopulationMargin() {
        return unchangedPopulationMargin;
    }

    public long getGensWithoutPopulationChange() {
        return gensWithoutPopulationChange;
    }
}
