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
        int[] letterQ = {-1,1,1,1,-1,1,-1,-1,-1,1,1,-1,-1,-1,1,1,-1,-1,1,1,-1,1,1,-1,1};
        int[] letterD = {1,1,1,1,-1,1,-1,-1,-1,1,1,-1,-1,-1,1,1,-1,-1,-1,1,1,1,1,1,-1};
        int[] letterV = {1,-1,-1,-1,1,1,-1,-1,-1,1,1,-1,-1,-1,1,-1,1,-1,1,-1,-1,-1,1,-1,-1};
        int[] letterW = {1,-1,1,-1,1,1,-1,1,-1,1,1,-1,1,-1,1,1,1,-1,1,1,1,-1,-1,-1,1};
        int[] letterI = {1,1,1,1,1,-1,-1,1,-1,-1,-1,-1,1,-1,-1,-1,-1,1,-1,-1,1,1,1,1,1};

       // printLetter(letterI);


        List<int[]> patterns = new ArrayList<>();

        patterns.add(letterZ);
        patterns.add(letterM);
        patterns.add(letterR);
        patterns.add(letterO);


        //ZMFO 14
        //ZJRO 20
        //OTVF 18

        //JTOR 20
        //XCOF 48

        //ZCOF 46
        //ZCOX 54

        //CQOD 90
        
        int or= 0;
        for(int a=0; a<patterns.size()-1; a++){

            for(int b=a+1; b<patterns.size(); b++){
                int s= 0;
                for(int p=0; p<patterns.get(0).length; p++){
                  s += patterns.get(a)[p] * patterns.get(b)[p];
                }
                if(s<0)
                    s = s*-1;
                or += s;
            }

        }
        System.out.println(or);


        Hopfield hopfield = new Hopfield(patterns);
        hopfield.consult(addNoise(letterM,30));

    }



}
