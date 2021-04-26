package ar.edu.itba.sia.utils;
import ar.edu.itba.sia.Activation.*;

import java.util.Properties;

public class Config {
    public String inputFile;
    public String outputFile;
    public Double learningRate;
    public Double errorEps;
    public String activationFunction;
    public int numberIterations;
    public int sameBiasIterations;


    public Config(Properties props){
        this.inputFile =  props.getProperty("inputFile");
        this.outputFile = props.getProperty("outputFile");
        this.learningRate =  Double.parseDouble(props.getProperty("learningRate"));
        this.errorEps = Double.parseDouble(props.getProperty("errorEps"));
        this.activationFunction = props.getProperty("activationFunction");
        this.numberIterations = Integer.parseInt(props.getProperty("numberIterations"));
        this.sameBiasIterations = Integer.parseInt(props.getProperty("sameBiasIterations"));
    }


    public ActivationFunction getActivationFunction(){
        switch(this.activationFunction){
            case "linear": return new Linear();
            case "sigmoid": return new Sigmoid();
            case "sign": return new Sign();
            case "tanh": return new TanH();
            default:return null;
        }
    }

}
