package src.service;

import src.enumModel.ItemSize;
import src.enumModel.ItemType;
import src.model.Inventaire;
import src.model.Item;

public class InventaireService {

    private final Inventaire inventaire;

    public InventaireService(Inventaire inventaire)  {
        this.inventaire = inventaire;
    }

    public void loadInventaire() {
        try {
            inventaire.addItem(new Item("Big Mac", 6.99, 50, ItemType.MAIN));
            inventaire.addItem(new Item("Quarter Pounder", 7.49, 40, ItemType.MAIN));
            inventaire.addItem(new Item("McChicken", 5.99, 45, ItemType.MAIN));

            inventaire.addItem(new Item("Frites", 3.49, 100, ItemType.SNACK));
            inventaire.addItem(new Item("Nuggets (6)", 4.99, 60, ItemType.SNACK));

            inventaire.addItem(new Item("Coca-Cola", 2.49, 80, ItemType.DRINK, ItemSize.MEDIUM));
            inventaire.addItem(new Item("Sprite", 2.49, 70, ItemType.DRINK, ItemSize.MEDIUM));
            inventaire.addItem(new Item("Jus d'orange", 2.99, 50, ItemType.DRINK, ItemSize.MEDIUM));

        } catch (Exception e) {
            System.out.println("Erreur lors du chargement de l'inventaire : " + e.getMessage());
        }
    }

    public void getInventory() {
        try {
            System.out.println("\n--- STOCK ACTUEL ---");
            for (Item it : inventaire.getAllItems()) {
                System.out.println(it.getName() + ": " + it.getStock()
                        + " unités (" + it.getPrice() + "$)");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'affichage de l'inventaire : " + e.getMessage());
        }
    }

    public void addStock(String name, int quantity) {
        try {
            Item item = inventaire.getItemByName(name);

            if (item == null) {
                throw new IllegalArgumentException("Oups, ce nom n'existe pas.");
            }

            item.ajoutStock(quantity);

        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout de stock : " + e.getMessage());
            throw e;
        }
    }


    public void removeStock(String name, int quantity) {
        try {
            Item item = inventaire.getItemByName(name);

            if (item == null) {
                throw new IllegalArgumentException("Oups, ce nom n'existe pas.");
            }

            if (quantity > item.getStock()) {
                throw new IllegalArgumentException("Stock insuffisant, retrait impossible !");
            }

            item.retirerStock(quantity);

        } catch (Exception e) {
            System.out.println("Erreur lors du retrait de stock : " + e.getMessage());
            throw e;
        }
    }

    public void ajoutNouvelItem(String name, double price, int stock, ItemType type, ItemSize size) {
        try {
            Item item;

            // Si l'item est une boisson il faut rajoute rla taille vu que dans le model il est différent
            if (type == ItemType.DRINK) {
                item = new Item(name, price, stock, type, size);
            } else {
                item = new Item(name, price, stock, type);
            }

            inventaire.addItem(item);

        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout du nouvel item : " + e.getMessage());
        }
    }
}
