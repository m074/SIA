package ar.edu.itba.sia;

import ar.edu.itba.sia.Model.Hopfield;

import java.io.IOException;
import java.util.*;
import static ar.edu.itba.sia.utils.Utils.addNoise;
import static ar.edu.itba.sia.utils.Utils.printLetter;

public class Ej2 {
    public static void main(String[] args) throws IOException {

        int[] letterJ = {1,1,1,1,1,-1,-1,-1,1,-1,-1,-1,-1,1,-1,1,-1,-1,1,-1,-1,1,1,-1,-1};
        int[] letterT = {1,1,1,1,1,-1,-1,1,-1,-1,-1,-1,1,-1,-1,-1,-1,1,-1,-1,-1,-1,1,-1,-1};
        int[] letterO = {-1,1,1,1,-1,1,-1,-1,-1,1,1,-1,-1,-1,1,1,-1,-1,-1,1,-1,1,1,1,-1};
        int[] letterC = {1,1,1,1,1,1,-1,-1,-1,-1,1,-1,-1,-1,-1,1,-1,-1,-1,-1,1,1,1,1,1};
        int[] letterR = {1,1,1,1,-1,1,-1,-1,-1,1,1,1,1,1,-1,1,-1,-1,-1,1,1,-1,-1,-1,1};
        int[] letterM = {1,-1,-1,-1,1,1,1,-1,1,1,1,-1,1,-1,1,1,-1,1,-1,1,1,-1,1,-1,1};
        int[] letterX = {1,-1,-1,-1,1,-1,1,-1,1,-1,-1,-1,1,-1,-1,-1,1,-1,1,-1,1,-1,-1,-1,1};
        int[] letterZ = {1,1,1,1,1,-1,-1,-1,1,-1,-1,-1,1,-1,-1,-1,1,-1,-1,-1,1,1,1,1,1};
        int[] letterF = {1,1,1,1,1,1,-1,-1,-1,-1,1,1,1,-1,-1,1,-1,-1,-1,-1,1,-1,-1,-1,-1};


        List<int[]> patterns = new ArrayList<>();
        patterns.add(letterJ);
        patterns.add(letterT);
        patterns.add(letterO);
        patterns.add(letterR);

        //printLetter(letterF);


        Hopfield hopfield = new Hopfield(patterns);
        hopfield.consult(addNoise(letterT,20));

    }



}
