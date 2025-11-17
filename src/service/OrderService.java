package src.service;

import src.model.CartItem;
import src.model.Item;

import java.util.List;

public class OrderService {
    private int orderCounter = 1;
    public boolean validationStockItem(List<CartItem> cartItems){
        for  (CartItem cartItem : cartItems){
            if(cartItem.isTrio()){
                if(cartItem.getMainItem().getStock() < 1 || cartItem.getSnackItem().getStock() < 1  || cartItem.getDrinkItem().getStock() < 1 ){
                    throw new IllegalArgumentException("Oups pas de stock pour votre item !");
                } else {
                    Item item = cartItem.getMainItem();
                    if (item.getStock() < 1  ) {
                        throw new IllegalArgumentException("Oups pas de stock pour votre "+ item.getName());
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public void reductionStock(List<CartItem> cartItems){
        for  (CartItem cartItem : cartItems){
            cartItem.getMainItem().retirerStock(1);
            if(cartItem.isTrio()){
                cartItem.getMainItem().retirerStock(1);
                cartItem.getSnackItem().retirerStock(1);
                cartItem.getDrinkItem().retirerStock(1);
            }
        }
    }

    public void factureStock(List<CartItem> cartItems){
        System.out.println("\n========= RECU =========");
        System.out.println("Commande #" + orderCounter);
        orderCounter++;

        double total = 0;
        for (CartItem ci : cartItems) {
            System.out.printf("%s - %.2f$\n", ci.getDescription(), ci.getPrice());
            total += ci.getPrice();
        }
        System.out.println("------------------------");
        System.out.printf("TOTAL: %.2f$\n", total);
        System.out.println("========================");

        cartItems.clear();
        System.out.println("\n✓ Commande passée avec succès!");
    }
}
