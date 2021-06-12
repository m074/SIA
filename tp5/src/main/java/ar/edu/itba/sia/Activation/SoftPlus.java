package ar.edu.itba.sia.Activation;

public class SoftPlus extends ActivationFunction{

    @Override
    public double evaluate(double value) {
        return Math.log(1+Math.pow(Math.E, value));
    }

    public double evaluateDer(double value) {

        return 1/(1+Math.pow(Math.E,-value));
    }

    @Override
    public FUNCTIONTYPE getType() {
        return FUNCTIONTYPE.NONLINEAER;
    }
}
