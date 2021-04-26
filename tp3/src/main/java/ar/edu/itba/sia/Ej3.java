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


        double[][] inputData = new double[l.size()/7][l.get(0).length*7];

        int j = 0;
        int py= 0;
        int px= 0;
        for(double[] arr : l){
;

            for (int i = 0; i < arr.length; i++){
                inputData[px][i+py] = arr[i];
            }
            j++;

            if(j%7 == 0){
                py =0;
                px +=1;
            }
            else{
                py += 5;
            }
        }

        System.out.println(" " + inputData.length + " " + inputData[0].length);



        double[][] inputs = {{-1.0, 1.0}, {1.0, -1.0}, {-1.0, -1.0}, {1.0, 1.0}};
        //double[] xorOutputs = {1.0, 1.0, -1.0, -1.0};
        double[] xorOutputs = {1.0, 1.0, 0, 0};

        /*
        MultiLayerPerceptron ml = new MultiLayerPerceptron(0.0001,0.01,inputs,xorOutputs, new Sigmoid(), new int[]{2,1});
        ml.train(200000 ,50);
        ml.prediction();

         */





        //double[] pixelsoutputs = {1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0};

        //double[] pixelsoutputs = {0.5, -0.5, 0.5, -0.5, 0.5, -0.5, 0.5, -0.5, 0.5, -0.5};
        double[] pixelsoutputs = {1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0};


        MultiLayerPerceptron ml = new MultiLayerPerceptron(0.01,0.001,inputData,pixelsoutputs, new Sigmoid(), new int[]{3,1});
        ml.train(200000 ,50);
        ml.prediction();

        

    }



}
