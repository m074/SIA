package ar.edu.itba.sia.Model;

import java.util.Random;

public abstract class Connection {
    protected Neuron output;
    protected double weight;
    protected double deltaWeight = 0;
    protected double velocity=0;

    Random r = new Random();
    protected Connection(Neuron output){
        this.output = output;
        this.weight = -0.2 + 0.4 * r.nextDouble();
    }

    public double getWeight(){
        return weight;
    }
    public void setWeight(double weight){
        this.weight = weight;
    }

    public void updateWeightWithMomentum(double momentum){
        velocity = momentum * velocity + (1 - momentum) * deltaWeight;
        weight += velocity;
        deltaWeight = 0;
    }

    public abstract double getWeightedVal();
    public abstract void updateDeltaWeight();

    public Neuron getOutput(){
        return output;
    }


}
