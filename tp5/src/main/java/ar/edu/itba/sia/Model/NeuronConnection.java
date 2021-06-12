package ar.edu.itba.sia.Model;

public class NeuronConnection extends Connection {
    private Neuron input;

    public NeuronConnection(Neuron input, Neuron output){
        super(output);
        this.input = input;
    }

    @Override
    public double getWeightedVal(){
        return input.getValue() * weight;
    }

    @Override
    public void updateDeltaWeight(){
        deltaWeight += output.learningRate * output.getDelta() * input.getValue();
    }
}
