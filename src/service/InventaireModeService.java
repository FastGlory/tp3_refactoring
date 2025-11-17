package src.service;

import src.enumModel.ItemSize;
import src.enumModel.ItemType;
import src.model.Inventaire;
import src.model.Item;

public class InventaireModeService {
    private final Inventaire inventaire;
    public InventaireModeService(Inventaire inventaire) {
        this.inventaire = inventaire;
    }
    public void getInventory(){
        System.out.println("\n--- STOCK ACTUEL ---");
        for (Item it : inventaire.getAllItems()) {
            System.out.println(it.getName() + ": " + it.getStock() + " unités (" + it.getPrice() + "$)");
        }

    }

    public void addStock(String name, int quantity){
        Item item =  inventaire.getItemByName(name);
        if(item == null){
            throw new IllegalArgumentException("Oups nom n'existe pas");
        }
        item.ajoutStock(quantity);
    }

    public void removeStock(String name,  int quantity){
        Item item =  inventaire.getItemByName(name);
        if(item == null){
            throw new IllegalArgumentException("Oups nom n'existe pas");
        }
        item.ajoutStock(quantity);
    }
    public void ajoutNouvelItem(String name, double price, int stock, ItemType type, ItemSize size) {
        Item item;
        if (type == ItemType.DRINK) {
            item = new Item(name, price, stock, type, size);
        } else {
            item = new Item(name, price, stock, type);
        }
        inventaire.addItem(item);
    }

}
