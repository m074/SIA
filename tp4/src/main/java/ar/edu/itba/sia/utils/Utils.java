package ar.edu.itba.sia.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    public static void shuffleArray(double[][] array){
        int index; double[] temp;
        for (int i = array.length - 1; i > 0; i--)
        {
            index = ThreadLocalRandom.current().nextInt(0, i+1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
}
