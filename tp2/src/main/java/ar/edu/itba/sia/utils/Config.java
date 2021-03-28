package ar.edu.itba.sia.utils;


import ar.edu.itba.sia.Model.CharacterClass;

import java.util.Properties;

public class Config {
    private long initialPopulation;
    private CharacterClass characterClass;
    private long maxGenerations;
    private double crossOverProbability;
    public Config(Properties props){
        this.initialPopulation = Long.parseLong(props.getProperty("initialPopulation"));
        this.characterClass = getCharClass(props.getProperty("characterClass"));
        this.maxGenerations = Long.parseLong(props.getProperty("maxGenerations"));
        this.crossOverProbability = Double.parseDouble(props.getProperty("crossOverProbability"));
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
}
