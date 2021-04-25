package ar.edu.itba.sia;


import ar.edu.itba.sia.Activation.Linear;
import ar.edu.itba.sia.Model.SimplePerceptron2;
import ar.edu.itba.sia.utils.Config;
import ar.edu.itba.sia.utils.TsvLoader;

import java.io.*;
import java.util.*;

public class App {
        public static void main(String args[]) throws IOException {
            Properties props = new Properties();
            Reader input;
            input = new InputStreamReader(new FileInputStream("D:\\config2.properties"));
            props.load(input);
            System.out.println("Loading set into database..\n");
            Config config = new Config(props);

            ArrayList<double[]> inputData_list = TsvLoader.loadInput(config);
            double[][] inputData = new double[inputData_list.size()][inputData_list.get(0).length];
            int j = 0;
            for(double[] arr : inputData_list){
                for (int i = 0; i < arr.length; i++){
                    inputData[j][i] = arr[i];
                }
                j++;
            }

            ArrayList<Double> outputData_list = TsvLoader.loadOutput(config);
            double[] outputData = new double[outputData_list.size()];
            for(int i=0; i<outputData_list.size(); i++){
                outputData[i] = outputData_list.get(i);
            }

            double[] outputDataNormalizedTan = outputData.clone();
            double[] outputDataNormalizedExp = outputData.clone();
            double max = Collections.max(outputData_list);
            double min = Collections.min(outputData_list);

            for(int i = 0; i < outputDataNormalizedTan.length; i++){
                outputDataNormalizedTan[i] = 2*((outputDataNormalizedTan[i]-min)/(max-min))-1;
                outputDataNormalizedExp[i] = (outputDataNormalizedExp[i]-min)/(max-min);
            }

            SimplePerceptron2 sp2 = new SimplePerceptron2(config.errorEps, config.learningRate, inputData, outputDataNormalizedExp, config.getActivationFunction());
            sp2.train(config.numberIterations,config.sameBiasIterations);
            sp2.prediction();




        }
}
