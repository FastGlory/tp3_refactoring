package src.service;

import src.enumModel.ItemType;
import src.model.CartItem;
import src.model.Inventaire;
import src.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientModeService {

    private final Scanner sc = new Scanner(System.in);
    private final Inventaire inventaire;
    private final CartItemService cartService;
    private final OrderService orderService;

    public ClientModeService(Inventaire inventaire, CartItemService cartService, OrderService orderService) {
        this.inventaire = inventaire;
        this.cartService = cartService;
        this.orderService = orderService;
    }

    // Mode menu pour le client
    public void startClientMode() {

        try {
            // Accueil personnalisé pour le client
            System.out.print("Nom: ");
            String name = sc.nextLine();
            System.out.println("Bienvenue " + name);

            boolean loop = true;

            while (loop) {
                System.out.println("\n=== MODE CLIENT ===");
                System.out.println("1. Voir menu");
                System.out.println("2. Ajouter TRIO");
                System.out.println("3. Ajouter item individuel");
                System.out.println("4. Voir panier");
                System.out.println("5. Retirer item du panier");
                System.out.println("6. Passer commande");
                System.out.println("7. Retour");
                System.out.print("Choix: ");

                try {
                    int choix = sc.nextInt();

                    // Navigation du client  en question
                    switch (choix) {
                        case 1 -> afficherMenu();
                        case 2 -> ajouterTrio();
                        case 3 -> ajouterIndividuel();
                        case 4 -> afficherPanier();
                        case 5 -> retirerItemPanier();
                        case 6 -> passerCommande();
                        case 7 -> loop = false;
                        default -> System.out.println("Choix invalide.");
                    }

                } catch (Exception e) {
                    System.out.println("Erreur : entrée invalide.");
                    sc.nextLine();
                }
            }

        } catch (Exception e) {
            System.out.println("Erreur critique : " + e.getMessage());
        }
    }

    private void afficherMenu() {
        try {
            System.out.println("\n=== MENU ===");
            List<Item> items = inventaire.getAllItems();

            for (int i = 0; i < items.size(); i++) {
                Item it = items.get(i);
                System.out.printf("%d. %s - %.2f$ (stock: %d)\n",
                        i + 1, it.getName(), it.getPrice(), it.getStock());
            }

        } catch (Exception e) {
            System.out.println("Erreur lors de l'affichage du menu : " + e.getMessage());
        }
    }

    private void ajouterIndividuel() {
        try {
            afficherMenu();
            System.out.print("Choix: ");
            int choix = sc.nextInt() - 1;

            Item item = inventaire.getItemByIndex(choix);

            if (item.getStock() < 1) {
                System.out.println("ERREUR : stock insuffisant.");
                return;
            }

            cartService.addItemIndividuel(item);
            System.out.println("✓ Ajouté au panier !");

        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout individuel : " + e.getMessage());
            sc.nextLine();
        }
    }

    private void ajouterTrio() {
        try {
            // === Sélection du plat principal === //
            System.out.println("\nPlats principaux:");
            List<Item> mains = new ArrayList<>();

            for (Item item : inventaire.getAllItems()) {
                if (item.getType() == ItemType.MAIN) mains.add(item);
            }

            for (int i = 0; i < mains.size(); i++) {
                System.out.printf("%d. %s - %.2f$\n",
                        i + 1, mains.get(i).getName(), mains.get(i).getPrice());
            }

            System.out.print("Choix: ");
            int choixMain = sc.nextInt() - 1;

            if (choixMain < 0 || choixMain >= mains.size()) {
                System.out.println("ERREUR : Choix invalide");
                return;
            }

            Item mainItem = mains.get(choixMain);

            // === Accompagnement === //
            System.out.println("\nAccompagnements:");
            List<Item> snacks = new ArrayList<>();

            for (Item it : inventaire.getAllItems()) {
                if (it.getType() == ItemType.SNACK) snacks.add(it);
            }

            for (int i = 0; i < snacks.size(); i++) {
                System.out.printf("%d. %s - %.2f$\n",
                        i + 1, snacks.get(i).getName(), snacks.get(i).getPrice());
            }

            System.out.print("Choix: ");
            int choixSnack = sc.nextInt() - 1;

            if (choixSnack < 0 || choixSnack >= snacks.size()) {
                System.out.println("ERREUR : Choix invalide");
                return;
            }

            Item snackItem = snacks.get(choixSnack);

            // === Boisson === //
            System.out.println("\nBoissons:");
            List<Item> drinks = new ArrayList<>();

            for (Item it : inventaire.getAllItems()) {
                if (it.getType() == ItemType.DRINK) drinks.add(it);
            }

            for (int i = 0; i < drinks.size(); i++) {
                System.out.printf("%d. %s - %.2f$\n",
                        i + 1, drinks.get(i).getName(), drinks.get(i).getPrice());
            }

            System.out.print("Choix: ");
            int choixDrink = sc.nextInt() - 1;

            if (choixDrink < 0 || choixDrink >= drinks.size()) {
                System.out.println("ERREUR : Choix invalide");
                return;
            }

            Item drinkItem = drinks.get(choixDrink);

            // Vérification du stock
            if (mainItem.getStock() < 1 || snackItem.getStock() < 1 || drinkItem.getStock() < 1) {
                System.out.println("ERREUR : Stock insuffisant pour créer ce trio.");
                return;
            }

            cartService.addTrio(mainItem, snackItem, drinkItem);
            System.out.println("✓ Trio ajouté !");

        } catch (Exception e) {
            System.out.println("Erreur lors de la création du trio : " + e.getMessage());
            sc.nextLine();
        }
    }

    private void afficherPanier() {
        try {
            System.out.println("\n=== PANIER ===");
            List<CartItem> items = cartService.getCartItems();

            if (items.isEmpty()) {
                System.out.println("Panier vide.");
                return;
            }

            for (int i = 0; i < items.size(); i++) {
                CartItem ci = items.get(i);
                System.out.printf("%d. %s - %.2f$\n",
                        i + 1, ci.getDescription(), ci.getPrice());
            }

            System.out.printf("TOTAL : %.2f$\n", cartService.getTotal());

        } catch (Exception e) {
            System.out.println("Erreur lors de l'affichage du panier : " + e.getMessage());
        }
    }

    private void retirerItemPanier() {
        try {
            afficherPanier();
            System.out.print("Numéro à retirer : ");
            int index = sc.nextInt() - 1;

            cartService.removeItem(index);
            System.out.println("Item retiré !");

        } catch (Exception e) {
            System.out.println("Erreur lors du retrait : " + e.getMessage());
            sc.nextLine();
        }
    }

    private void passerCommande() {
        try {
            List<CartItem> items = cartService.getCartItems();

            if (items.isEmpty()) {
                System.out.println("\nPanier vide! Ajoutez des items d'abord.");
                return;
            }

            orderService.validationStockItem(items);
            orderService.reductionStock(items);
            orderService.factureStock(items);

        } catch (Exception e) {
            System.out.println("Erreur lors de la commande : " + e.getMessage());
        }
    }
}
