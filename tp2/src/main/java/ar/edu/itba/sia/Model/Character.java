package ar.edu.itba.sia.Model;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Character implements Comparable<Character> {
    private Item weapon;
    private Item boots;
    private Item helmet;
    private Item gloves;
    private Item vest;
    private double height;
    private CharacterClass characterClass;
    private double ATM;
    private double DEM;

    public Character(Item weapon, Item boots, Item helmet, Item gloves, Item vest, CharacterClass characterClass) {
        this.weapon = weapon;
        this.boots = boots;
        this.helmet = helmet;
        this.gloves = gloves;
        this.vest = vest;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        this.height = random.nextDouble(1.3, 2.0);
        this.characterClass = characterClass;
        ATM = getATM();
        DEM = getDEM();
    }

    private double getATM(){
        return 0.7-Math.pow(3.0*height-5.0, 4.0) + Math.pow(3.0*height-5.0, 2.0)+height/4.0;
    }

    private double getDEM(){
        return 1.9+Math.pow(2.5*height-4.16, 4.0) - Math.pow(2.5*height-4.16, 2.0)+(3*height)/10.0;
    }

    private double getStrength(){
        return 100.0*Math.tanh(0.01*(weapon.getStrength()+boots.getStrength()+helmet.getStrength()+gloves.getStrength()+vest.getStrength()));
    }

    private double getAgility(){
        return Math.tanh(0.01*(weapon.getAgility()+boots.getAgility()+helmet.getAgility()+gloves.getAgility()+vest.getAgility()));
    }

    private double getExpertise(){
        return 0.6*Math.tanh(0.01*(weapon.getExpertise()+boots.getExpertise()+helmet.getExpertise()+gloves.getExpertise()+vest.getExpertise()));
    }

    private double getResistance(){
        return Math.tanh(0.01*(weapon.getResistance()+boots.getResistance()+helmet.getResistance()+gloves.getResistance()+vest.getResistance()));
    }

    private double getVitality(){
        return 100.0*Math.tanh(0.01*(weapon.getVitality()+boots.getVitality()+helmet.getVitality()+gloves.getVitality()+vest.getVitality()));
    }

    private double getAttack(){
        return (getAgility()+getExpertise())*getStrength()*ATM;
    }

    private double getDefense(){
        return (getResistance()+getExpertise())*getVitality()*DEM;
    }

    public double getFitness(){
        switch(characterClass){
            case WARRIOR:
                return 0.6*getAttack() + 0.6*getDefense();
            case ARCHER:
                return 0.9*getAttack() + 0.1*getDefense();
            case DEFENDER:
                return 0.3*getAttack() + 0.8*getDefense();
            case INFILTRATOR:
                return 0.8*getAttack() + 0.3*getDefense();
            default:return 0.0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return Double.compare(character.height, height) == 0 &&
                Objects.equals(weapon, character.weapon) &&
                Objects.equals(boots, character.boots) &&
                Objects.equals(helmet, character.helmet) &&
                Objects.equals(gloves, character.gloves) &&
                Objects.equals(vest, character.vest) &&
                characterClass == character.characterClass;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weapon, boots, helmet, gloves, vest, height, characterClass, ATM, DEM);
    }

    @Override
    public int compareTo(Character  o){
        if(o != null)
            return Double.compare(this.getFitness(), ((Character) o).getFitness());
        return -1;
    }
}
