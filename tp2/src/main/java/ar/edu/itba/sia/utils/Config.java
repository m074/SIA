package ar.edu.itba.sia.utils;


import ar.edu.itba.sia.Algorithm.Elite;
import ar.edu.itba.sia.Algorithm.Roulette;
import ar.edu.itba.sia.Algorithm.SelectionMethod;
import ar.edu.itba.sia.Algorithm.Universal;
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

    public double getCrossOverProbability(){
        return crossOverProbability;
    }

    public SelectionMethod getSelectionMethodA(){
        switch(this.selectionMethodA){
            case "roulette": return new Roulette();
            case "elite": return new Elite();
            case "universal": return new Universal();
            default:return null;
        }
    }

    public SelectionMethod getSelectionMethodB(){
        switch(this.selectionMethodB){
            case "roulette": return new Roulette();
            case "elite": return new Elite();
            case "universal": return new Universal();
            default:return null;
        }
    }

    public SelectionMethod getReplacementMethodA(){
        switch(this.replacementMethodA){
            case "roulette": return new Roulette();
            case "elite": return new Elite();
            case "universal": return new Universal();
            default:return null;
        }
    }

    public SelectionMethod getReplacementMethodB(){
        switch(this.replacementMethodB){
            case "roulette": return new Roulette();
            case "elite": return new Elite();
            case "universal": return new Universal();
            default:return null;
        }
    }

    public double getSelectionMethodAPercentage(){
        return this.selectionMethodAPercentage;
    }

    public double getReplacementMethodAPercentage(){
        return this.replacementMethodAPercentage;
    }
}
