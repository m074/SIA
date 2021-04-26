package ar.edu.itba.sia;

import ar.edu.itba.sia.Activation.Linear;
import ar.edu.itba.sia.Activation.Sigmoid;
import ar.edu.itba.sia.Activation.Sign;
import ar.edu.itba.sia.Activation.TanH;
import ar.edu.itba.sia.Model.MultiLayerPerceptron;
import ar.edu.itba.sia.Model.Multilayer;
import ar.edu.itba.sia.Model.SimplePerceptron2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class Ej3 {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(Objects.requireNonNull(Ej2.class.getClassLoader().getResourceAsStream("./ej3.tsv")));
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


        double[][] inputs = {{-1.0, 1.0}, {1.0, -1.0}, {-1.0, -1.0}, {1.0, 1.0}};
        double[] xorOutputs = {1.0, 1.0, -1.0, -1.0};


        //System.out.println(inputData[0][0]+" "+inputData[1][0]+" "+inputData[2][0]+" "+inputData[3][0]);

        //double error_eps, double learningRate, double[][] inputData, double[] outputData, ActivationFunction actFunc, int[] layers_qty
        MultiLayerPerceptron ml = new MultiLayerPerceptron(0.01,0.1,inputs,xorOutputs, new TanH(), new int[]{2,1});
        ml.train(20000 ,50);
        ml.prediction();





/*
        s = new Scanner(Objects.requireNonNull(Ej2.class.getClassLoader().getResourceAsStream("./output.tsv")));
        ArrayList<Double> l2 = new ArrayList<>();
        while(s.hasNextLine()){
            l2.add(Double.parseDouble(s.nextLine()));
        }
        double[] outputData = new double[l2.size()];
        for(int i=0; i<l2.size(); i++){
            outputData[i] = l2.get(i);
        }
        double[] outputDataNormalizedTan = outputData.clone();
        double[] outputDataNormalizedExp = outputData.clone();
        double max = Collections.max(l2);
        double min = Collections.min(l2);
        for(int i = 0; i < outputDataNormalizedTan.length; i++){
            outputDataNormalizedTan[i] = 2*((outputDataNormalizedTan[i]-min)/(max-min))-1;
            outputDataNormalizedExp[i] = (outputDataNormalizedExp[i]-min)/(max-min);
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



        SimplePerceptron2 sp2 = new SimplePerceptron2(0.001,0.01, inputData, outputDataNormalizedExp, new Sigmoid());
        sp2.train(1000,100);
        sp2.prediction();
  */

    }



}
