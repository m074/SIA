package ar.edu.itba.sia;

import ar.edu.itba.sia.Activation.TanH;
import ar.edu.itba.sia.Model.AutoEncoder;
import ar.edu.itba.sia.Model.Font;
import ar.edu.itba.sia.Model.Kohonen;
import ar.edu.itba.sia.Model.KohonenNeuron;


import java.util.List;

public class Ej2 {
    public static void main(String[] args){

        int[][] font1= {{0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00},   // 0x20, space
                {0x04, 0x04, 0x04, 0x04, 0x04, 0x00, 0x04},   // 0x21, !
                {0x09, 0x09, 0x12, 0x00, 0x00, 0x00, 0x00},   // 0x22, "
                {0x0a, 0x0a, 0x1f, 0x0a, 0x1f, 0x0a, 0x0a},   // 0x23, #
                {0x04, 0x0f, 0x14, 0x0e, 0x05, 0x1e, 0x04},   // 0x24, $
                {0x19, 0x19, 0x02, 0x04, 0x08, 0x13, 0x13},   // 0x25, %
                {0x04, 0x0a, 0x0a, 0x0a, 0x15, 0x12, 0x0d},   // 0x26, &
                {0x04, 0x04, 0x08, 0x00, 0x00, 0x00, 0x00},   // 0x27, '
                {0x02, 0x04, 0x08, 0x08, 0x08, 0x04, 0x02},   // 0x28, (
                {0x08, 0x04, 0x02, 0x02, 0x02, 0x04, 0x08},   // 0x29, )
                {0x04, 0x15, 0x0e, 0x1f, 0x0e, 0x15, 0x04},   // 0x2a, *
                {0x00, 0x04, 0x04, 0x1f, 0x04, 0x04, 0x00},   // 0x2b, +
                {0x00, 0x00, 0x00, 0x00, 0x04, 0x04, 0x08},   // 0x2c, ,
                {0x00, 0x00, 0x00, 0x1f, 0x00, 0x00, 0x00},   // 0x2d, -
                {0x00, 0x00, 0x00, 0x00, 0x00, 0x0c, 0x0c},   // 0x2e, .
                {0x01, 0x01, 0x02, 0x04, 0x08, 0x10, 0x10},   // 0x2f, /
                {0x0e, 0x11, 0x13, 0x15, 0x19, 0x11, 0x0e},   // 0x30, 0
                {0x04, 0x0c, 0x04, 0x04, 0x04, 0x04, 0x0e},   // 0x31, 1
                {0x0e, 0x11, 0x01, 0x02, 0x04, 0x08, 0x1f},   // 0x32, 2
                {0x0e, 0x11, 0x01, 0x06, 0x01, 0x11, 0x0e},   // 0x33, 3
                {0x02, 0x06, 0x0a, 0x12, 0x1f, 0x02, 0x02},   // 0x34, 4
                {0x1f, 0x10, 0x1e, 0x01, 0x01, 0x11, 0x0e},   // 0x35, 5
                {0x06, 0x08, 0x10, 0x1e, 0x11, 0x11, 0x0e},   // 0x36, 6
                {0x1f, 0x01, 0x02, 0x04, 0x08, 0x08, 0x08},   // 0x37, 7
                {0x0e, 0x11, 0x11, 0x0e, 0x11, 0x11, 0x0e},   // 0x38, 8
                {0x0e, 0x11, 0x11, 0x0f, 0x01, 0x02, 0x0c},   // 0x39, 9
                {0x00, 0x0c, 0x0c, 0x00, 0x0c, 0x0c, 0x00},   // 0x3a, :
                {0x00, 0x0c, 0x0c, 0x00, 0x0c, 0x04, 0x08},   // 0x3b, ;
                {0x02, 0x04, 0x08, 0x10, 0x08, 0x04, 0x02},   // 0x3c, <
                {0x00, 0x00, 0x1f, 0x00, 0x1f, 0x00, 0x00},   // 0x3d, =
                {0x08, 0x04, 0x02, 0x01, 0x02, 0x04, 0x08},   // 0x3e, >
                {0x0e, 0x11, 0x01, 0x02, 0x04, 0x00, 0x04}   // 0x3f, ?
        };
        Font[] letters = new Font[font1.length];
        double[][] inputData = new double[32][35];

        for(int i = 0; i<letters.length; i++){
            letters[i] = new Font(font1[i], 7, 5);
        }
        for(int i=0; i<32; i++){
            for(int j=0; j<letters[i].getFontAsArray().length; j++){
                inputData[i][j] = letters[i].getFontAsArray()[j];
            }
        }

        int[][] layerConfigs = { {30, 20, 2, 20, 30}};
        AutoEncoder AC= new AutoEncoder(0.001, 35, 35, layerConfigs[0], new TanH(), false);;
        for(int[] layerConfig: layerConfigs){
            AC = new AutoEncoder(0.001, 35, 35, layerConfig, new TanH(), false);
            AC.train(inputData, inputData, 20000, 0.7);
            System.out.println("Error tras entrenamiento: " + AC.getError(inputData, inputData));
        }

        double[][] latentSpace = new double[32][2];
        for(int i =0; i<latentSpace.length; i++){
            List<Double> layer = AC.evaluateLatentSpace(inputData[i]);
            latentSpace[i][0] = layer.get(0);
            latentSpace[i][1] = layer.get(1);
        }
        String[] fontVals = {" ", "!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+", ",","-", ".", "/", "0", "1",
        "2", "3", "4", "5", "6", "7", "8", "9", ":", ";", "<", "=", ">", "?"};
        Kohonen network = new Kohonen(latentSpace, 9.5, 7, fontVals);
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
        }
        //si me da ! y 1 en la misma neurona
        List<Double> one = AC.evaluateLatentSpace(inputData[17]);
        List<Double> exclampoint = AC.evaluateLatentSpace(inputData[1]);

        double x =  (one.get(0) + exclampoint.get(0))/2;
        double y = (one.get(1) + exclampoint.get(1))/2;
        printImage(AC.evaluate(new double[]{x,y}, 3, 6), 35, 5);
    }

    public static void printImage(List<Double> list, int imgSize, int imgWidth) {
        double [] image = new double[imgSize];
        for (int i = 0; i < 35 ; i++) {
            image[i] = list.get(i) > 0.5 ? 1 : 0;
        }
        for (int i = 0; i < image.length; i++) {
            if(i!=0 && i%imgWidth==0){
                System.out.print("\n");
            }
            char aux = image[i] == 1 ? '*':' ';
            System.out.print(aux + " ");
        }
        System.out.println();
        System.out.println();
    }
}
