package ar.edu.itba.sia.Model;

import ar.edu.itba.sia.Activation.ActivationFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Neuron {
    protected double learningRate;
    protected ActivationFunction actFunc;
    public List<Connection> input = new ArrayList<>();
    public List<NeuronConnection> output = new ArrayList<>();
    protected double V;
    protected double h;
    protected double delta;
    public int layerIdx;

    public Neuron(int layerIdx, ActivationFunction actFunc, double learningRate, Integer bias){
        this.learningRate = learningRate;
        this. actFunc = actFunc;
        this.layerIdx = layerIdx;

        if(bias != null){
            input.add(new BiasConnection(this, bias));
        }
    }

    public double getWeightedInput(){
        double value = 0;
        for(Connection c : input){
            value += c.getWeightedVal();
        }
        return value;
    }

    public void evaluate(){
        this.h = getWeightedInput();
        this.V = actFunc.evaluate(h);
    }

    public void makeConnection(Neuron target){
        NeuronConnection connection = new NeuronConnection(this, target);
        output.add(connection);
        target.addInput(connection);
    }

    public void addInput(Connection c){
        input.add(c);
    }

    public void calculateDelta(double expectedValue){

        double sum = 0;
        for(NeuronConnection nc : output){
            sum += nc.getWeight() * nc.getOutput().getDelta();
        }
        delta = actFunc.evaluateDer(h) * sum;

        //Gradiente descendiente estocastico:
        /*
        double sum = 0;
        List<NeuronConnection> randomConnections = new ArrayList<>();
        Random r = new Random();
        for(int i = 0; i < 15; i++){
            randomConnections.add(output.get(r.nextInt(output.size())));
        }
        for(NeuronConnection nc : randomConnections){
            sum += nc.getWeight() * nc.getOutput().getDelta();
        }
        delta = actFunc.evaluateDer(h) * sum;
        */
    }

    public double getDelta(){
        return delta;
    }

    public double getValue(){
        return V;
    }

    public void setValue(double v){
        this.V = v;
    }


}
