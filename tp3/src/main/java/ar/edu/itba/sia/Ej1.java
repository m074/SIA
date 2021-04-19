package ar.edu.itba.sia;

import ar.edu.itba.sia.Activation.Sign;
import ar.edu.itba.sia.Model.Neuron;
import ar.edu.itba.sia.Model.SimplePerceptron;

import java.util.Scanner;

public class Ej1 {
    public static void main(String[] args){
        double[][] inputs = {{-1.0, 1.0}, {1.0, -1.0}, {-1.0, -1.0}, {1.0, 1.0}};
        double[] andOutputs = {-1.0, -1.0, -1.0, 1.0};
        double[] xorOutputs = {1.0, 1.0, -1.0, -1.0};
        SimplePerceptron AndPerceptron = new SimplePerceptron(0 ,0.01, inputs, andOutputs, new Sign());
        SimplePerceptron XorPerceptron = new SimplePerceptron(0,0.1, inputs, xorOutputs, new Sign());
        boolean flag=true;
        while(flag){
            System.out.println("Teach or predict?");
            Scanner s = new Scanner(System.in);
            String choice = s.nextLine();
            switch(choice){
                case "teach": AndPerceptron.train(1000000, 1000); break;
                case "predict": Neuron n = AndPerceptron.getBest();
                    System.out.println("[-1, 1]: " + n.predict(new double[]{-1.0, 1.0}));
                    System.out.println("[1, -1]: " + n.predict(new double[]{1.0, -1.0}));
                    System.out.println("[-1, -1]: " + n.predict(new double[]{-1.0, -1.0}));
                    System.out.println("[1, 1]: " + n.predict(new double[]{1.0, 1.0}));
                    break;
                case "exit": flag=false; break;
            }
        }

    }
}
