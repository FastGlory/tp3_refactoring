package src.service;

import src.model.CartItem;
import src.model.Item;

import java.util.List;

import java.util.ArrayList;

public class CartItemService {
    private final List<CartItem> cartItems = new ArrayList<>();

    public void addItemIndividuel(Item item) {
        cartItems.add(new CartItem(item));
    }

    public void addTrio(Item main, Item snack, Item drink) {
        cartItems.add(new CartItem(main, snack, drink));
    }
    public void removeItem(int index) {
        if (index < 0 || index >= cartItems.size()) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        cartItems.remove(index);
    }
    public void removeAll() {
        cartItems.clear();
    }
    public List<CartItem> getCartItems() {
        return cartItems;
    }
    public CartItem getCartItem(int index) {
        if (index < 0 || index >= cartItems.size()) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        return cartItems.get(index);
    }

    public double getTotal() {
        double total = 0;
        for (CartItem cartItem : cartItems) {
            total += cartItem.getPrice();
        }
        return total;
    }

}
