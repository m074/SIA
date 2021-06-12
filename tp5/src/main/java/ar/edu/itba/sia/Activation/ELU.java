package ar.edu.itba.sia.Activation;

public class ELU extends ActivationFunction{
    public final double beta = 0.1;

    @Override
    public double evaluate(double value) {
        return value > 0 ? value : beta*(Math.pow(Math.E, value) - 1);
    }

    public double evaluateDer(double value) {

        return value > 0 ? 1 : beta*Math.pow(Math.E, value);
    }

    @Override
    public FUNCTIONTYPE getType() {
        return FUNCTIONTYPE.LINEAR;
    }
}
