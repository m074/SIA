package ar.edu.itba.sia.utils;

import ar.edu.itba.sia.Model.Item;
import ar.edu.itba.sia.Model.ItemType;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

public class ItemLoader {
    public static Map<ItemType, ArrayList<Item>> loadItems() throws IOException{
        return new HashMap<ItemType, ArrayList<Item>>(){
            {
                put(ItemType.WEAPON, loadFile(".", ItemType.WEAPON));
                put(ItemType.BOOTS, loadFile(".", ItemType.BOOTS));
                put(ItemType.HELMET, loadFile(".", ItemType.HELMET));
                put(ItemType.GLOVES, loadFile(".", ItemType.GLOVES));
                put(ItemType.VEST, loadFile(".", ItemType.VEST));
            }

        };
    }

    private static ArrayList<Item> loadFile(String path, ItemType type) throws IOException {
        ArrayList<Item> list = new ArrayList<>();
        Reader input;

        input = new InputStreamReader(Objects.requireNonNull(ItemLoader.class.getClassLoader().getResourceAsStream(path)));


        CSVParser parser = CSVFormat.TDF.withFirstRecordAsHeader().parse(input);
        for(CSVRecord r : parser){
            Item i = new Item(
                Double.parseDouble(r.get("Fu")),
                Double.parseDouble(r.get("Ag")),
                Double.parseDouble(r.get("Ex")),
                Double.parseDouble(r.get("Re")),
                Double.parseDouble(r.get("Vi")),
                    type
            );
            list.add(i);
        }
        return list;
    }

}
