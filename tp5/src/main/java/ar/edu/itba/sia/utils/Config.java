package ar.edu.itba.sia.utils;

import java.util.Properties;

public class Config {

    public int epochs;
    public int noise;
    public String algorithm;
    public String layers_string;

    public int[] get_layers(){
        String[] parts = this.layers_string.split(",");
        int[] layers = new int[parts.length];
        for(int i=0; i<parts.length; i++){
            layers[i] = Integer.parseInt(parts[i]);
        }
        return layers;
    }

    public Config(Properties props) {
        this.layers_string = props.getProperty("layers");
        this.epochs = Integer.parseInt(props.getProperty("epochs"));
        this.noise = Integer.parseInt(props.getProperty("noise"));
        this.algorithm = props.getProperty("algorithm");
    }


}
