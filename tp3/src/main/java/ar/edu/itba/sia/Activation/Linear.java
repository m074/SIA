package ar.edu.itba.sia.Activation;

public class Linear extends ActivationFunction{

    @Override
    public double evaluate(double value) {
        return value;
    }

    @Override
    public double evaluateDer(double value) {
        return 0;
    }

    @Override
    public FUNCTIONTYPE getType() {
        return FUNCTIONTYPE.LINEAR;
    }
}
