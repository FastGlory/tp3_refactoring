package src.service;

import src.model.CartItem;
import src.model.Item;

import java.util.List;
import java.util.ArrayList;

public class CartItemService {

    // Une liste du panier du client
    private final List<CartItem> cartItems = new ArrayList<>();

    public void addItemIndividuel(Item item) {
        try {
            cartItems.add(new CartItem(item));
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout de l'item individuel : " + e.getMessage());
        }
    }

    public void addTrio(Item main, Item snack, Item drink) {
        try {
            cartItems.add(new CartItem(main, snack, drink));
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout du trio : " + e.getMessage());
        }
    }

    public void removeItem(int index) {
        try {
            if (index < 0 || index >= cartItems.size()) {
                throw new IllegalArgumentException("Index hors limites");
            }
            cartItems.remove(index);
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erreur inattendue lors du retrait de l'item : " + e.getMessage());
        }
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }


    public double getTotal() {
        double total = 0;
        try {
            for (CartItem cartItem : cartItems) {
                total += cartItem.getPrice();
            }
        } catch (Exception e) {
            System.out.println("Erreur lors du calcul du total : " + e.getMessage());
        }
        return total;
    }
}
