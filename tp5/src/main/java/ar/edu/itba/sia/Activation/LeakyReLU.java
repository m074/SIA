package ar.edu.itba.sia.Activation;

public class LeakyReLU extends ActivationFunction {
    public final double beta = 0.01;

    @Override
    public double evaluate(double value) {
        return value > 0 ? value : beta*value;
    }

    public double evaluateDer(double value) {

        return value > 0 ? 1 : beta;
    }

    @Override
    public FUNCTIONTYPE getType() {
        return FUNCTIONTYPE.LINEAR;
    }
}
