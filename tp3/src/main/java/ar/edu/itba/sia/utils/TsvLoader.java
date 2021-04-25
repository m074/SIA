package ar.edu.itba.sia.utils;


import java.io.File;
import java.io.IOException;
import java.util.*;

public class TsvLoader {
    public static ArrayList<double[]> loadInput(Config config) throws IOException{
        Scanner s = new Scanner(new File(config.inputFile));

        ArrayList<double[]> l = new ArrayList<>();
        while(s.hasNextLine()){
            String[] data = s.nextLine().trim().split("\\s+");
            double[] aux = new double[data.length];
            int i = 0;
            for(String str: data){
                aux[i++] = Double.parseDouble(str);
            }
            l.add(aux);
        }
        return l;
    }

    public static ArrayList<Double> loadOutput(Config config) throws IOException{
        Scanner s = new Scanner(new File(config.outputFile));

        ArrayList<Double> l2 = new ArrayList<>();
        while(s.hasNextLine()){
            l2.add(Double.parseDouble(s.nextLine()));
        }
        return l2;
    }

}
