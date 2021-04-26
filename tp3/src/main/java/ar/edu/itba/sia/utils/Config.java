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
    public double beta;
    public double momentum;
    public String layers_string;
    public boolean adaptLR;
    public int updateLRits;
    public double LRincrement;
    public double LRdecrement;


    public Config(Properties props){
        this.inputFile =  props.getProperty("inputFile");
        this.outputFile = props.getProperty("outputFile");
        this.learningRate =  Double.parseDouble(props.getProperty("learningRate"));
        this.errorEps = Double.parseDouble(props.getProperty("errorEps"));
        this.activationFunction = props.getProperty("activationFunction");
        this.numberIterations = Integer.parseInt(props.getProperty("numberIterations"));
        this.sameBiasIterations = Integer.parseInt(props.getProperty("sameBiasIterations"));
        this.beta =  Double.parseDouble(props.getProperty("beta"));
        this.momentum =  Double.parseDouble(props.getProperty("momentum"));
        this.layers_string = props.getProperty("layers");
        String adaptLR_string = props.getProperty("adaptLR");
        if(adaptLR_string.equals("true")){
            this.adaptLR = true;
            this.updateLRits = Integer.parseInt(props.getProperty("updateLRits"));
            this.LRincrement = Double.parseDouble(props.getProperty("LRincrement"));
            this.LRdecrement = Double.parseDouble(props.getProperty("LRdecrement"));
        }else{
            this.adaptLR = false;
        }
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

    public int[] get_layers(){
        String[] parts = this.layers_string.split(",");
        int[] layers = new int[parts.length];
        for(int i=0; i<parts.length; i++){
            layers[i] = Integer.parseInt(parts[i]);
        }
        return layers;
    }

}
