package ar.edu.itba.sia.Activation;

public class TanH extends ActivationFunction {

    @Override
    public double evaluate(double value) {
        return Math.tanh(value);
    }
}
