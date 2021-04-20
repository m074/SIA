package ar.edu.itba.sia.Activation;

public abstract class ActivationFunction {



    public abstract double evaluate(double value);
    public abstract double evaluateDer(double value);
    public abstract FUNCTIONTYPE getType();
}
