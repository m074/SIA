package ar.edu.itba.sia.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationParser {
    private final static String
            MAP_PATH = "map_path",
            OVITO_DIR = "ovito_dir",
            ALGORITHM = "algorithm",
            HEURISTIC = "heuristic",
            DEPTH = "depth";
    private final static String VALUE_SEPARATOR = "=";
    private final static int HEADER = 0, VALUE = 1;

    public String map_path;
    public String ovito_dir;
    public String algorithm;
    public String heuristic;
    public int depth;


    private String dynamicFileName;

    public ConfigurationParser(){
    }

    public void parse(String fileName) throws IOException {

        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        while((line = bufferedReader.readLine()) != null) {

            String [] args = line.split(VALUE_SEPARATOR);

            switch(args[HEADER]) {
                case MAP_PATH:
                    this.map_path = args[VALUE];
                    break;
                case OVITO_DIR:
                    this.ovito_dir = args[VALUE];
                    break;
                case ALGORITHM:
                    this.algorithm = args[VALUE];
                    break;
                case HEURISTIC:
                    this.heuristic = args[VALUE];
                    break;
                case DEPTH:
                    this.depth = Integer.parseInt(args[VALUE]);
                    break;
                default:

            }
        }
        bufferedReader.close();

    }
}
