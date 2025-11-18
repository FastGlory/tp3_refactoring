package src.main;

import src.model.Inventaire;
import src.service.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Inventaire inventaire = new Inventaire();
        InventaireService inventaireService = new InventaireService(inventaire);

        inventaireService.loadInventaire(); // on va charger ici les inventaire a la place de la manière du badCode

        CartItemService cartService = new CartItemService();
        OrderService orderService = new OrderService();
        ClientModeService clientService = new ClientModeService(inventaire, cartService, orderService);
        InventaireModeService inventaireModeService = new InventaireModeService(inventaireService);

        boolean applicationRun = true;

        System.out.println("=== MCDONALD'S ===");

        while (applicationRun) {
            try {
                System.out.println("\n1. Mode Client");
                System.out.println("2. Mode Inventaire");
                System.out.println("3. Quitter");
                System.out.print("Choix: ");

                int choix = sc.nextInt();

                switch (choix) {
                    case 1 -> clientService.startClientMode();
                    case 2 -> inventaireModeService.startInventaireMode();
                    case 3 -> applicationRun = false;
                    default -> System.out.println("Choix invalide.");
                }

            } catch (Exception e) {
                System.out.println("Erreur : entrée invalide.");
                sc.nextLine();
            }
        }

        // Petit ajout personnelle
        System.out.println("Au revoir !");
    }
}

// Pourquoi final ? final empêche la modification de certaines variables ou objets, rendant le code plus sûr
// Les try catch on été ajouté avec l'autocomplétation et AI, j'avais compléetement oublier de les intégrer. Vu que cela est très simple a faire
// je me suis permis d'utilisé ces différents OUTIL.
// Ici certains try-catch appel déjà des erreur donc c'est normal ca fasse des doublons dans l'affichage erreur
