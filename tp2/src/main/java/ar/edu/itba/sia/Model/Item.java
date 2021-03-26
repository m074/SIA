package ar.edu.itba.sia.Model;

public class Item {
    private double strength;
    private double agility;
    private double expertise;
    private double resistance;
    private double health;
    private ItemType type;
    private long id;

    public Item(double strength, double agility, double expertise, double resistance, double health, ItemType type, long id) {
        this.strength = strength;
        this.agility = agility;
        this.expertise = expertise;
        this.resistance = resistance;
        this.health = health;
        this.type = type;
        this.id = id;
    }
}
