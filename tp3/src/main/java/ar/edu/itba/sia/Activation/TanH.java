package ar.edu.itba.sia.Activation;

public class TanH extends ActivationFunction {

    double beta = 0.5;

    @Override
    public double evaluate(double value) {
        return Math.tanh(beta*value);
    }


    public double evaluateDer(double value) {
        return beta * (1 - Math.pow(evaluate(value),2));
    }

    @Override
    public FUNCTIONTYPE getType() {
        return FUNCTIONTYPE.NONLINEAER;
    }

}
