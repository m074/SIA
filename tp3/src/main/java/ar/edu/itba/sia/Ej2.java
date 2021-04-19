package ar.edu.itba.sia;

import ar.edu.itba.sia.Activation.Linear;
import ar.edu.itba.sia.Activation.Sigmoid;
import ar.edu.itba.sia.Activation.Sign;
import ar.edu.itba.sia.Activation.TanH;
import ar.edu.itba.sia.Model.Neuron;
import ar.edu.itba.sia.Model.SimplePerceptron;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Ej2 {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(Objects.requireNonNull(Ej2.class.getClassLoader().getResourceAsStream("./input.tsv")));
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
        double[][] inputData = new double[l.size()][l.get(0).length];
        int j = 0;
        for(double[] arr : l){
            for (int i = 0; i < arr.length; i++){
                inputData[j][i] = arr[i];
            }
            j++;
        }

        s = new Scanner(Objects.requireNonNull(Ej2.class.getClassLoader().getResourceAsStream("./output.tsv")));
        ArrayList<Double> l2 = new ArrayList<>();
        while(s.hasNextLine()){
            l2.add(Double.parseDouble(s.nextLine()));
        }
        double[] outputData = new double[l2.size()];
        for(int i=0; i<l2.size(); i++){
            outputData[i] = l2.get(i);
        }
        SimplePerceptron linearPerceptron = new SimplePerceptron(0.01 ,0.01, inputData, outputData, new Linear());
        boolean flag=true;
        while(flag){
            System.out.println("Teach or predict?");
            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();
            switch(choice){
                case "teach": linearPerceptron.train(10000, 1000); break;
                case "predict": Neuron n = linearPerceptron.getBest();
                    System.out.println("[-4.1793,-4.9218,1.7664]: " + n.predict(new double[]{-4.1793,-4.9218,1.7664}));
                    break;
                case "exit": flag=false; break;
            }
        }
    }
}
