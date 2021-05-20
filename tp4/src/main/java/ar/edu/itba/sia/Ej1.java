package ar.edu.itba.sia;

import ar.edu.itba.sia.Model.Kohonen;
import ar.edu.itba.sia.Model.KohonenNeuron;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.apache.commons.math3.stat.StatUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;

public class Ej1 {
    public static void main(String[] args) throws IOException {
        double[][] inputData = new double[28][7];
        Reader input;
        input = new InputStreamReader(Objects.requireNonNull(Ej1.class.getClassLoader().getResourceAsStream("normalizedCountries.csv")));
        CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(input);
        String[] countries = new String[28];
        int i = 0;
        for (CSVRecord r : parser) {
            countries[i] = r.get("Country");
            inputData[i][0] = Double.parseDouble(r.get("Area"));
            inputData[i][1] = Double.parseDouble(r.get("GDP"));
            inputData[i][2] = Double.parseDouble(r.get("Inflation"));
            inputData[i][3] = Double.parseDouble(r.get("Life.expect"));
            inputData[i][4] = Double.parseDouble(r.get("Military"));
            inputData[i][5] = Double.parseDouble(r.get("Pop.growth"));
            inputData[i][6] = Double.parseDouble(r.get("Unemployment"));
            i++;
        }

        Kohonen network = new Kohonen(inputData, 10, 7, countries);
        network.processData();
        double[][] uMatrix = network.getUMatrix();
        KohonenNeuron[][] matrix = network.getResult();
        for(int j=0; j<matrix.length; j++){
            for(int k=0; k<matrix[j].length;k++){
                System.out.print("Neuron " + (j+1) + (k+1) + " Countries: ");
                for(String c : matrix[j][k].countryLastHits){
                    System.out.print(c + " ");
                }
                System.out.println();
            }
        }
        System.out.println("--------------------------\n\n");

        for(int j=0; j<uMatrix.length; j++){
            for(int k=0; k<uMatrix.length; k++){
                System.out.print(uMatrix[j][k] + (k==uMatrix.length-1 ? ";" : ", "));
            }
            System.out.print("\n");
        }
    }
}
