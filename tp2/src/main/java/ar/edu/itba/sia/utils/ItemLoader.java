package ar.edu.itba.sia.utils;

import ar.edu.itba.sia.Model.Item;
import ar.edu.itba.sia.Model.ItemType;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.*;

public class ItemLoader {
    public static HashMap<ItemType, ArrayList<Item>> loadItems(Properties props) throws IOException{
        return new HashMap<ItemType, ArrayList<Item>>(){
            {
                put(ItemType.WEAPON, loadFile(props.getProperty("weaponPath"), ItemType.WEAPON));
                put(ItemType.BOOTS, loadFile(props.getProperty("bootsPath"), ItemType.BOOTS));
                put(ItemType.HELMET, loadFile(props.getProperty("helmetPath"), ItemType.HELMET));
                put(ItemType.GLOVES, loadFile(props.getProperty("glovesPath"), ItemType.GLOVES));
                put(ItemType.VEST, loadFile(props.getProperty("vestPath"), ItemType.VEST));
            }

        };
    }

    private static ArrayList<Item> loadFile(String path, ItemType type) throws IOException {
        ArrayList<Item> list = new ArrayList<>();
        Reader input;
        input = new FileReader(path);

        CSVParser parser = CSVFormat.TDF.withFirstRecordAsHeader().parse(input);
        for(CSVRecord r : parser){
            Item i = new Item(
                Double.parseDouble(r.get("Fu")),
                Double.parseDouble(r.get("Ag")),
                Double.parseDouble(r.get("Ex")),
                Double.parseDouble(r.get("Re")),
                Double.parseDouble(r.get("Vi")),
                type,
                Long.parseLong(r.get("id"))
            );
            list.add(i);
        }
        return list;
    }

}
