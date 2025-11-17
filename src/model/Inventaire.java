package src.model;

import java.util.ArrayList;
import java.util.List;

public class Inventaire {
    private final List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }
    public List<Item> getAllItems() {
        return items;
    }

    public Item getItemByName(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public Item getItemByIndex(int index) {
        if  (index >= items.size() || index < 0) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        return items.get(index);
    }





}
