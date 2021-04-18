package ar.edu.itba.sia.Activation;

public class Sigmoid extends ActivationFunction {

    @Override
    public double evaluate(double value) {
        double beta = 1.0;
        return 1/(1+Math.exp(-2*beta*value));
    }
}
