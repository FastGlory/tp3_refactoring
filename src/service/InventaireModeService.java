package src.service;

import src.enumModel.ItemSize;
import src.enumModel.ItemType;

import java.util.Scanner;

public class InventaireModeService {

    private final InventaireService inventaireService;
    private final Scanner sc = new Scanner(System.in);

    public InventaireModeService(InventaireService inventaireService) {
        this.inventaireService = inventaireService;
    }

    public void startInventaireMode() {

        boolean running = true;

        while (running) {
            try {
                System.out.println("\n=== INVENTAIRE ===");
                System.out.println("1. Afficher inventaire");
                System.out.println("2. Ajouter stock");
                System.out.println("3. Retirer stock");
                System.out.println("4. Ajouter nouvel item");
                System.out.println("5. Retour");
                System.out.print("Choix: ");

                int choix = sc.nextInt();

                switch (choix) {
                    case 1 -> afficherInventaire();
                    case 2 -> ajouterStock();
                    case 3 -> retirerStock();
                    case 4 -> ajouterNouvelItem();
                    case 5 -> running = false;
                    default -> System.out.println("Choix invalide.");
                }

            } catch (Exception e) {
                System.out.println("Erreur : entrée invalide.");
                sc.nextLine();
            }
        }
    }

    private void afficherInventaire() {
        try {
            inventaireService.getInventory();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'affichage : " + e.getMessage());
        }
    }

    private void ajouterStock() {
        try {
            System.out.print("Nom de l'item: ");
            sc.nextLine();
            String name = sc.nextLine();

            System.out.print("Quantité à ajouter: ");
            int qty = sc.nextInt();

            inventaireService.addStock(name, qty);
            System.out.println("Stock ajouté !");

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
            sc.nextLine();
        }
    }

    // Retirer du stock
    private void retirerStock() {
        try {
            System.out.print("Nom de l'item: ");
            sc.nextLine();
            String name = sc.nextLine();

            System.out.print("Quantité à retirer: ");
            int qty = sc.nextInt();

            inventaireService.removeStock(name, qty);
            System.out.println("Stock retiré !");

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
            sc.nextLine();
        }
    }

    // Ajouter un nouvel item COMPLET
    private void ajouterNouvelItem() {
        try {
            sc.nextLine();

            System.out.print("Nom: ");
            String name = sc.nextLine();

            System.out.print("Prix: ");
            double price = sc.nextDouble();

            System.out.print("Stock initial: ");
            int stock = sc.nextInt();

            System.out.print("Type (main/snack/drink): ");
            String typeInput = sc.next();

            // Vérification du type d'item
            ItemType type;
            if (typeInput.equalsIgnoreCase("main")) {
                type = ItemType.MAIN;
            } else if (typeInput.equalsIgnoreCase("snack")) {
                type = ItemType.SNACK;
            } else if (typeInput.equalsIgnoreCase("drink")) {
                type = ItemType.DRINK;
            } else {
                System.out.println("Type invalide.");
                return;
            }

            // Gestion de la taille (SEULEMENT si boisson)
            ItemSize size = null;
            if (type == ItemType.DRINK) {
                try {
                    System.out.print("Taille (SMALL/MEDIUM/LARGE): ");
                    size = ItemSize.valueOf(sc.next().toUpperCase());
                } catch (Exception e) {
                    System.out.println("Taille invalide.");
                    return;
                }
            }

            // Ajout final
            inventaireService.ajoutNouvelItem(name, price, stock, type, size);
            System.out.println("Item ajouté !");

        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout de l'item : " + e.getMessage());
            sc.nextLine();
        }
    }
}
