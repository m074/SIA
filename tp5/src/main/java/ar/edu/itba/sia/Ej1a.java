package ar.edu.itba.sia;

import ar.edu.itba.sia.Activation.*;
import ar.edu.itba.sia.Model.AutoEncoder;
import ar.edu.itba.sia.Model.Font;

import java.util.List;

public class Ej1a {
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

        int[][] font2 = {{0x0e, 0x11, 0x17, 0x15, 0x17, 0x10, 0x0f},   // 0x40, @
                {0x04, 0x0a, 0x11, 0x11, 0x1f, 0x11, 0x11},   // 0x41, A
                {0x1e, 0x11, 0x11, 0x1e, 0x11, 0x11, 0x1e},   // 0x42, B
                {0x0e, 0x11, 0x10, 0x10, 0x10, 0x11, 0x0e},   // 0x43, C
                {0x1e, 0x09, 0x09, 0x09, 0x09, 0x09, 0x1e},   // 0x44, D
                {0x1f, 0x10, 0x10, 0x1c, 0x10, 0x10, 0x1f},   // 0x45, E
                {0x1f, 0x10, 0x10, 0x1f, 0x10, 0x10, 0x10},   // 0x46, F
                {0x0e, 0x11, 0x10, 0x10, 0x13, 0x11, 0x0f},   // 0x37, G
                {0x11, 0x11, 0x11, 0x1f, 0x11, 0x11, 0x11},   // 0x48, H
                {0x0e, 0x04, 0x04, 0x04, 0x04, 0x04, 0x0e},   // 0x49, I
                {0x1f, 0x02, 0x02, 0x02, 0x02, 0x12, 0x0c},   // 0x4a, J
                {0x11, 0x12, 0x14, 0x18, 0x14, 0x12, 0x11},   // 0x4b, K
                {0x10, 0x10, 0x10, 0x10, 0x10, 0x10, 0x1f},   // 0x4c, L
                {0x11, 0x1b, 0x15, 0x11, 0x11, 0x11, 0x11},   // 0x4d, M
                {0x11, 0x11, 0x19, 0x15, 0x13, 0x11, 0x11},   // 0x4e, N
                {0x0e, 0x11, 0x11, 0x11, 0x11, 0x11, 0x0e},   // 0x4f, O
                {0x1e, 0x11, 0x11, 0x1e, 0x10, 0x10, 0x10},   // 0x50, P
                {0x0e, 0x11, 0x11, 0x11, 0x15, 0x12, 0x0d},   // 0x51, Q
                {0x1e, 0x11, 0x11, 0x1e, 0x14, 0x12, 0x11},   // 0x52, R
                {0x0e, 0x11, 0x10, 0x0e, 0x01, 0x11, 0x0e},   // 0x53, S
                {0x1f, 0x04, 0x04, 0x04, 0x04, 0x04, 0x04},   // 0x54, T
                {0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x0e},   // 0x55, U
                {0x11, 0x11, 0x11, 0x11, 0x11, 0x0a, 0x04},   // 0x56, V
                {0x11, 0x11, 0x11, 0x15, 0x15, 0x1b, 0x11},   // 0x57, W
                {0x11, 0x11, 0x0a, 0x04, 0x0a, 0x11, 0x11},   // 0x58, X
                {0x11, 0x11, 0x0a, 0x04, 0x04, 0x04, 0x04},   // 0x59, Y
                {0x1f, 0x01, 0x02, 0x04, 0x08, 0x10, 0x1f},   // 0x5a, Z
                {0x0e, 0x08, 0x08, 0x08, 0x08, 0x08, 0x0e},   // 0x5b, [
                {0x10, 0x10, 0x08, 0x04, 0x02, 0x01, 0x01},   // 0x5c, \\
                {0x0e, 0x02, 0x02, 0x02, 0x02, 0x02, 0x0e},   // 0x5d, ]
                {0x04, 0x0a, 0x11, 0x00, 0x00, 0x00, 0x00},   // 0x5e, ^
                {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x1f}   // 0x5f, _
        };
        Font[] letters = new Font[font2.length];
        double[][] inputData = new double[32][35];

        for(int i = 0; i<letters.length; i++){
            letters[i] = new Font(font2[i], 7, 5);
        }
        for(int i=0; i<32; i++){
            for(int j=0; j<letters[i].getFontAsArray().length; j++){
                inputData[i][j] = letters[i].getFontAsArray()[j];
            }
        }

        int[][] layerConfigs = { {30, 20, 2, 20, 30}};
        AutoEncoder AC= new AutoEncoder(0.0015, 35, 35, layerConfigs[0], new TanH());;
        for(int[] layerConfig: layerConfigs){
            AC = new AutoEncoder(0.001, 35, 35, layerConfig, new TanH());
            AC.train(inputData, inputData, 10000, 0.8);
            System.out.println("Error tras entrenamiento: " + AC.getError(inputData, inputData));
        }
        /*
        String fontVals = " !\"#$%&'()*+,-./0123456789:;<=>?";
        System.out.println("char x-coord y-coord");
        for(int i =0; i<fontVals.length(); i++){
            List<Double> layer = AC.evaluateLatentSpace(inputData[i]);
            System.out.println(fontVals.charAt(i) + ", " + layer.get(0) + ", " + layer.get(1));
        }*/

    }
}
