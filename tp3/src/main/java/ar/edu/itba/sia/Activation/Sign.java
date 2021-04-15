package ar.edu.itba.sia.Activation;

public class Sign extends ActivationFunction {
    @Override
    public double evaluate(double excitement) {
        if(excitement > 0)
            return 1.0;
        else
            return -1.0;
    }
}
