package ar.edu.itba.sia.utils;

import java.util.Properties;

public class Config {
    public String europeFile;
    public double startingRadius;
    public int k;
    public double eta;
    public int epochs;
    public int noise;
    public String algorithm;


    public Config(Properties props) {
        this.europeFile = props.getProperty("europeFile");
        this.startingRadius = Double.parseDouble(props.getProperty("startingRadius"));
        this.k = Integer.parseInt(props.getProperty("matrixSize"));
        this.eta = Double.parseDouble(props.getProperty("eta"));
        this.epochs = Integer.parseInt(props.getProperty("epochs"));
        this.noise = Integer.parseInt(props.getProperty("noise"));
        this.algorithm = props.getProperty("algorithm");
    }


}
