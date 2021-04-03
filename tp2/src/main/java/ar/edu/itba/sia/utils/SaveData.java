package ar.edu.itba.sia.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveData {
    

    public static void createFile(String fileName)
            throws IOException {
        String str = "generation,maxfitness,minfitness\n";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(str);

        writer.close();
    }

    public static void writeFile(String fileName,String generation, String maxFit, String minFit)
            throws IOException {
        String str = generation + "," + maxFit + "," + minFit +"\n" ;
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        writer.append(' ');
        writer.append(str);

        writer.close();
    }


}
