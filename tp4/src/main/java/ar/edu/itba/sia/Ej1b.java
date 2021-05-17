package ar.edu.itba.sia;

import ar.edu.itba.sia.Model.Kohonen;
import ar.edu.itba.sia.Model.KohonenNeuron;
import ar.edu.itba.sia.Model.Oja;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;

public class Ej1b {
    public static void main(String[] args) throws IOException {
        double[][] inputData = new double[28][7];
        Reader input;
        input = new InputStreamReader(new FileInputStream("normalizedCountries.csv"));
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
        double eta=0.0005;
        Oja network = new Oja(inputData, eta);
        double[] oja_pca = network.train(10000);
        for(int j=0; j<oja_pca.length; j++){
            System.out.println(oja_pca[j]);
        }
    }
}
