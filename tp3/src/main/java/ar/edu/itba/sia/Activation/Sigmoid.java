package ar.edu.itba.sia.Activation;

public class Sigmoid extends ActivationFunction {

    private double beta = 4;

    public Sigmoid(){}

    public Sigmoid(double beta){
        this.beta = beta;
    }

    @Override
    public double evaluate(double value) {
        return 1/(1+Math.exp(-2*beta*value));
    }

    public double evaluateDer(double value) {

        return 2 * beta * evaluate(value) * (1 - evaluate(value));
    }

    @Override
    public FUNCTIONTYPE getType() {
        return FUNCTIONTYPE.NONLINEAER;
    }

}
