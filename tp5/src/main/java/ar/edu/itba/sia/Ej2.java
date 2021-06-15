package ar.edu.itba.sia;

import ar.edu.itba.sia.Activation.TanH;
import ar.edu.itba.sia.Model.AutoEncoder;
import ar.edu.itba.sia.Model.Font;
import ar.edu.itba.sia.Model.Kohonen;
import ar.edu.itba.sia.Model.KohonenNeuron;


import java.util.List;

import static ar.edu.itba.sia.utils.Utils.printImage;

public class Ej2 {
    public static void main(String[] args){

        int[][] font1= {{0x00, 0x0A, 0x0A, 0x00, 0x11, 0x0E, 0x00},   // :)
                {0x00, 0x0A, 0x0A, 0x00, 0x1F, 0x11, 0x0E},  // :D
                {0x00, 0x0A, 0x0A, 0x00, 0x0E, 0x11, 0x00}, // :(
                {0x00, 0x0A, 0x0A, 0x00, 0x0E, 0x11, 0x1F}, // D:
                {0x00, 0x0A, 0x0A, 0x00, 0x00, 0x1F, 0x00}, // :|
                {0x00, 0x0A, 0x0A, 0x00, 0x11, 0x1F, 0x11}, // :I
                {0x00, 0x0A, 0x0A, 0x00, 0x1F, 0x05, 0x02}, // :P
                {0x00, 0x0A, 0x0A, 0x00, 0x0E, 0x11, 0x0E}, // :O
                {0x00, 0x08, 0x0B, 0x00, 0x11, 0x0E, 0x00}, // ;)
                {0x00, 0x08, 0x0A, 0x00, 0x1F, 0x15, 0x0A},  // :B
                {0x00, 0x08, 0x0A, 0x00, 0x0A, 0x04, 0x00},   // c:
                {0x00, 0x08, 0x0A, 0x00, 0x04, 0x0A, 0x00},   // :c
                {0x00, 0x08, 0x0A, 0x00, 0x15, 0x0A, 0x00},   // :3
                {0x00, 0x08, 0x0A, 0x00, 0x0A, 0x15, 0x00},   // 3:
                {0x00, 0x08, 0x0A, 0x00, 0x11, 0x1F, 0x00},   // :]
                {0x00, 0x08, 0x0A, 0x00, 0x1F, 0x11, 0x00},   // :[
                {0x00, 0x08, 0x0B, 0x00, 0x1F, 0x11, 0x0E},// ;D
        };

        Font[] letters = new Font[font1.length];
        double[][] inputData = new double[17][35];

        for(int i = 0; i<letters.length; i++){
            letters[i] = new Font(font1[i], 7, 5);
        }
        for(int i=0; i<17; i++){
            for(int j=0; j<letters[i].getFontAsArray().length; j++){
                inputData[i][j] = letters[i].getFontAsArray()[j];
            }
        }

        int[][] layerConfigs = { {30, 20, 2, 20, 30}};
        AutoEncoder AC= new AutoEncoder(0.001, 35, 35, layerConfigs[0], new TanH(), false);;
        for(int[] layerConfig: layerConfigs){
            AC = new AutoEncoder(0.001, 35, 35, layerConfig, new TanH(), true);
            AC.train(inputData, inputData, 20000, 0.7);
            System.out.println("Error tras entrenamiento: " + AC.getError(inputData, inputData));
        }

        double[][] latentSpace = new double[7][2];
        for(int i =0; i<latentSpace.length; i++){
            List<Double> layer = AC.evaluateLatentSpace(inputData[i]);
            latentSpace[i][0] = layer.get(0);
            latentSpace[i][1] = layer.get(1);
        }


        String[] fontVals = {":)", ":D", ":(", "D:", ":|", ":I", ":P", ":O", ";)", ":B", "c:", ":c", ":3", "3:", ":]", ":[", ";D"};
        /*
        Kohonen network = new Kohonen(latentSpace, 4, 3, fontVals);
        network.processData();
        KohonenNeuron[][] matrix = network.getResult();
        for(int j=0; j<matrix.length; j++){
            for(int k=0; k<matrix[j].length;k++){
                System.out.print("Neuron " + (j+1) + (k+1) + " Characters: ");
                for(String c : matrix[j][k].countryLastHits){
                    System.out.print(c + " ");
                }
                System.out.println();
            }
        }*/
        //si me da ! y 1 en la misma neurona

        List<Double> six = AC.evaluateLatentSpace(inputData[6]);
        List<Double> ampersand = AC.evaluateLatentSpace(inputData[7]);

        double x =  (six.get(0) + ampersand.get(0))/2;
        double y = (six.get(1) + ampersand.get(1))/2;

        double xleft = (six.get(0) + x) / 2;
        double yleft= (six.get(1) + y)/2;

        double xright = (x + ampersand.get(0)) / 2;
        double yright = (y + ampersand.get(1)) / 2;

        printImage(AC.evaluate(new double[]{six.get(0), six.get(1)}, 3, 6), 35, 5);
        printImage(AC.evaluate(new double[]{xleft,yleft}, 3, 6), 35, 5);
        printImage(AC.evaluate(new double[]{x,y}, 3, 6), 35, 5);
        printImage(AC.evaluate(new double[]{xright,yright}, 3, 6), 35, 5);
        printImage(AC.evaluate(new double[]{ampersand.get(0), ampersand.get(1)}, 3, 6), 35, 5);

         
    }


}
