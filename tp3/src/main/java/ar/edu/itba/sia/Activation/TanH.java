package ar.edu.itba.sia.Activation;

public class TanH extends ActivationFunction {

    @Override
    public double evaluate(double value) {
        double beta = 1.5;
        return Math.tanh(beta*value);
    }
}
