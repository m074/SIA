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

    public static void printLetter(int[] letter){
        String str = "";
        for (int i=0;i<letter.length;i++){
            str += letter[i]==1?"*":" ";
            if((i+1)%5 == 0)
                str += "\n";
        }
        System.out.println(str);
    }

}
