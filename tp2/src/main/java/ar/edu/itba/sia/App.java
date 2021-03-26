package ar.edu.itba.sia;

import ar.edu.itba.sia.Model.Item;
import ar.edu.itba.sia.Model.ItemType;
import ar.edu.itba.sia.utils.ItemLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String args[]) throws IOException {
        HashMap<ItemType, ArrayList<Item>> items = ItemLoader.loadItems();
    }
}
