package src.model;


import src.enumModel.ItemSize;
import src.enumModel.ItemType;

public class Item {
    private final String name;
    private final double price;
    private int stock;
    private final ItemType type;
    private final ItemSize size;


    public Item(String name, double price, int stock, ItemType type) {
        this(name, price, stock, type, null);
    }

    public Item(String name, double price, int stock, ItemType type, ItemSize size) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.type = type;
        this.size = size;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public ItemType getType() { return type; }
    public ItemSize getSize() { return size; }


    public void ajoutStock(int quantitee){
        if(quantitee > 0){
            stock += quantitee;
        } else {
            throw new IllegalArgumentException("Quantitee non valide");
        }
    }

    public void retirerStock(int quantitee) {
        if (quantitee <= 0) {
            throw new IllegalArgumentException("La quantité doit être STRICTEMENT positive.");
        }
        if (quantitee > stock) {
            throw new IllegalArgumentException("Impossible de retirer plus que le stock disponible !");
        }
        if (stock <= 0) {
            throw new IllegalArgumentException("Le stock est déjà épuisé.");
        }
        stock -= quantitee;
    }









}
