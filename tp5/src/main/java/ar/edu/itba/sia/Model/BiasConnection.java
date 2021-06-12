package ar.edu.itba.sia.Model;

public class BiasConnection extends Connection {
    private double val;

    public BiasConnection(Neuron output, double val){
        super(output);
        this.val = val;
    }

    @Override
    public double getWeightedVal(){
        return this.val * weight;
    }

    @Override
    public void updateDeltaWeight(){
        deltaWeight += output.learningRate * output.getDelta() * val;
    }
}
