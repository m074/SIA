package ar.edu.itba.sia.Model;

public class Item {
    private double strength;
    private double agility;
    private double expertise;
    private double resistance;
    private double vitality;
    private ItemType type;
    private long id;

    public Item(double strength, double agility, double expertise, double resistance, double vitality, ItemType type, long id) {
        this.strength = strength;
        this.agility = agility;
        this.expertise = expertise;
        this.resistance = resistance;
        this.vitality = vitality;
        this.type = type;
        this.id = id;
    }

    public double getStrength() {
        return strength;
    }

    public double getAgility() {
        return agility;
    }

    public double getExpertise() {
        return expertise;
    }

    public double getResistance() {
        return resistance;
    }

    public double getVitality() {
        return vitality;
    }

    public ItemType getType() {
        return type;
    }

    public long getId() {
        return id;
    }
}
