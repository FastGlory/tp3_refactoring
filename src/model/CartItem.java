package src.model;
public class CartItem {

    private final Item mainItem;
    private final Item snackItem;
    private final Item drinkItem;
    private final boolean isTrio;

    // ==== Pour article seul ==== //
    public CartItem(Item mainItem) {
        this.mainItem = mainItem;
        this.snackItem = null;
        this.drinkItem = null;
        this.isTrio = false;
    }

    // ==== Pour un trio ==== //
    public CartItem(Item main, Item snack, Item drink) {
        this.mainItem = main;
        this.snackItem = snack;
        this.drinkItem = drink;
        this.isTrio = true;
    }

    public boolean isTrio() {
        return isTrio;
    }

    public Item getMainItem() {
        return mainItem;
    }

    public Item getSnackItem() {
        return snackItem;
    }

    public Item getDrinkItem() {
        return drinkItem;
    }

    // === Application Rabais ===//
    public double getPrice() {
        if (!isTrio) {return mainItem.getPrice();}

        double total = mainItem.getPrice() + snackItem.getPrice() + drinkItem.getPrice();
        return Math.round(total * 0.85 * 100.0) / 100.0; // 15% rabais, arrondi à 2 décimales
    }

    // === Description ===//
    public String getDescription() {
        if (!isTrio) {
            return "Article individuel :  " + mainItem.getName();
        }

        return "TRIO: " + mainItem.getName()
                + " + " + snackItem.getName()
                + " + " + drinkItem.getName()
                + " (15% rabais)";
    }
}
