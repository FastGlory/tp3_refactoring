package src.service;

import src.model.CartItem;

import java.util.List;

public class OrderService {

    private int orderCounter = 1;

    public boolean validationStockItem(List<CartItem> cartItems) {
        try {
            for (CartItem cartItem : cartItems) {

                //  === Item individuel === //
                if (!cartItem.isTrio()) {
                    if (cartItem.getMainItem().getStock() < 1) {
                        throw new IllegalArgumentException(
                                "Stock insuffisant pour : " + cartItem.getMainItem().getName()
                        );
                    }
                    continue;
                }

                //  === Trios === //
                if (cartItem.getMainItem().getStock() < 1 ||
                        cartItem.getSnackItem().getStock() < 1 ||
                        cartItem.getDrinkItem().getStock() < 1) {

                    throw new IllegalArgumentException(
                            "Stock insuffisant pour le trio : " + cartItem.getDescription()
                    );
                }
            }
            return true;

        } catch (Exception e) {
            System.out.println("Erreur lors de la validation du stock : " + e.getMessage());
            throw e;
        }
    }


    public void reductionStock(List<CartItem> cartItems) {
        try {
            for (CartItem cartItem : cartItems) {

                // Item individuel
                if (!cartItem.isTrio()) {
                    cartItem.getMainItem().retirerStock(1);
                    continue;
                }

                // Trio
                cartItem.getMainItem().retirerStock(1);
                cartItem.getSnackItem().retirerStock(1);
                cartItem.getDrinkItem().retirerStock(1);
            }

        } catch (Exception e) {
            System.out.println("Erreur lors de la réduction de stock : " + e.getMessage());
            throw e;
        }
    }

    public void factureStock(List<CartItem> cartItems) {
        try {
            System.out.println("\n========= RECU =========");
            System.out.println("Commande #" + orderCounter);
            orderCounter++;

            double total = 0;

            // Affichage de chaque item
            for (CartItem ci : cartItems) {
                System.out.printf("%s - %.2f$\n", ci.getDescription(), ci.getPrice());
                total += ci.getPrice();
            }

            System.out.println("------------------------");
            System.out.printf("TOTAL: %.2f$\n", total);
            System.out.println("========================");

            // Vide le panier après paiement
            cartItems.clear();
            System.out.println("\n✓ Commande passée avec succès!");

        } catch (Exception e) {
            System.out.println("Erreur lors de la génération de la facture : " + e.getMessage());
        }
    }
}
