package ar.edu.itba.sia;

import ar.edu.itba.sia.Algorithm.GeneticAlgorithm;
import ar.edu.itba.sia.Model.Item;
import ar.edu.itba.sia.Model.Character;
import ar.edu.itba.sia.Model.ItemType;
import ar.edu.itba.sia.utils.Config;
import ar.edu.itba.sia.utils.ItemLoader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

public class App {
    public static void main(String args[]) throws IOException {

        //carga de propiedades del archivo de config

        Properties props = new Properties();
        Reader input;
        input = new InputStreamReader(Objects.requireNonNull(ItemLoader.class.getClassLoader().getResourceAsStream("config.properties")));
        props.load(input);
        Config config = new Config(props);

        //calculo del mejor personaje
        HashMap<ItemType, ArrayList<Item>> items = ItemLoader.loadItems(props);
        GeneticAlgorithm genAlgo = new GeneticAlgorithm();
        genAlgo.calculate(config, items);
    }
}
