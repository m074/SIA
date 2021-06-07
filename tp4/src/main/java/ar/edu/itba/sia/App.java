package ar.edu.itba.sia;


import ar.edu.itba.sia.Model.Hopfield;
import ar.edu.itba.sia.Model.Kohonen;
import ar.edu.itba.sia.Model.KohonenNeuron;
import ar.edu.itba.sia.Model.Oja;
import ar.edu.itba.sia.utils.Config;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

import static ar.edu.itba.sia.utils.Utils.addNoise;

public class App {
        public static void main(String args[]) throws IOException {
            Properties props = new Properties();
            Reader prop_file;
            prop_file = new InputStreamReader(new FileInputStream("config.properties"));
            props.load(prop_file);
            System.out.println("Loading set into database..\n");
            Config config = new Config(props);


            double[][] inputData = new double[28][7];
            Reader input;
            input = new InputStreamReader(new FileInputStream(config.europeFile));
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


            switch (config.algorithm) {
                case "kohonen":
                    Kohonen network = new Kohonen(inputData, config.startingRadius, config.k, countries);
                    network.processData();
                    KohonenNeuron[][] matrix = network.getResult();
                    for (int j = 0; j < matrix.length; j++) {
                        for (int k = 0; k < matrix[j].length; k++) {
                            System.out.print("Neuron " + j + k + " Countries: ");
                            for (String c : matrix[j][k].countryLastHits) {
                                System.out.print(c + " ");
                            }
                            System.out.println();
                        }
                    }
                    break;
                case "oja":
                    System.out.println("Area,GDP,Inflation,Life.expect,Military,Pop.growth,Unemployment");
                    double eta = config.eta;
                    Oja oja_network = new Oja(inputData, eta);
                    for (int x = 1; x <= 1000; x = x + 1) {
                        double[] oja_pca = oja_network.train(x);
                        for (int j = 0; j < oja_pca.length; j++) {
                            System.out.print(oja_pca[j]);
                            if (j + 1 != oja_pca.length) {
                                System.out.print(',');

                            }
                        }
                        System.out.println();
                    }
                    break;
                case "hopfield":
                    int[] letterJ = {1, 1, 1, 1, 1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, 1, -1, -1, 1, -1, -1, 1, 1, -1, -1};
                    int[] letterT = {1, 1, 1, 1, 1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1};
                    int[] letterO = {-1, 1, 1, 1, -1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, -1, 1, 1, 1, -1};
                    int[] letterC = {1, 1, 1, 1, 1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, 1, 1, 1, 1};
                    int[] letterR = {1, 1, 1, 1, -1, 1, -1, -1, -1, 1, 1, 1, 1, 1, -1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1};
                    int[] letterM = {1, -1, -1, -1, 1, 1, 1, -1, 1, 1, 1, -1, 1, -1, 1, 1, -1, 1, -1, 1, 1, -1, 1, -1, 1};
                    int[] letterX = {1, -1, -1, -1, 1, -1, 1, -1, 1, -1, -1, -1, 1, -1, -1, -1, 1, -1, 1, -1, 1, -1, -1, -1, 1};
                    int[] letterZ = {1, 1, 1, 1, 1, -1, -1, -1, 1, -1, -1, -1, 1, -1, -1, -1, 1, -1, -1, -1, 1, 1, 1, 1, 1};
                    int[] letterF = {1, 1, 1, 1, 1, 1, -1, -1, -1, -1, 1, 1, 1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1};
                    int[] letterQ = {-1, 1, 1, 1, -1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, 1, 1, -1, 1, 1, -1, 1};
                    int[] letterD = {1, 1, 1, 1, -1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, 1, 1, 1, -1};
                    int[] letterV = {1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, -1, 1, -1, 1, -1, -1, -1, 1, -1, -1};
                    int[] letterW = {1, -1, 1, -1, 1, 1, -1, 1, -1, 1, 1, -1, 1, -1, 1, 1, 1, -1, 1, 1, 1, -1, -1, -1, 1};
                    int[] letterI = {1, 1, 1, 1, 1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, 1, 1, 1, 1, 1};

                    List<int[]> patterns = new ArrayList<>();

                    patterns.add(letterZ);
                    patterns.add(letterM);
                    patterns.add(letterR);
                    patterns.add(letterO);
                    int or = 0;
                    for (int a = 0; a < patterns.size() - 1; a++) {

                        for (int b = a + 1; b < patterns.size(); b++) {
                            int s = 0;
                            for (int p = 0; p < patterns.get(0).length; p++) {
                                s += patterns.get(a)[p] * patterns.get(b)[p];
                            }
                            if (s < 0)
                                s = s * -1;
                            or += s;
                        }

                    }
                    System.out.println(or);

                    Hopfield hopfield = new Hopfield(patterns);
                    hopfield.consult(addNoise(letterM, config.noise));
                default:


            }
        }
}
