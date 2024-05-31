package org.example.Ihm;

import lombok.Data;
import lombok.Getter;

import java.util.Scanner;

public class BaseIhm {
    private static BaseIhm instance;

    private Scanner scanner;
    private ArticleIhm articleIhm;
    private ClientIhm clientIhm;
    private VenteIhm venteIhm;
    private RapportIhm rapportIhm;

    private BaseIhm() {
        scanner = new Scanner(System.in);
        articleIhm = new ArticleIhm();
        clientIhm = new ClientIhm();
        venteIhm = new VenteIhm();
        rapportIhm = new RapportIhm();
    }

    public static BaseIhm getInstance() {
        if (instance == null) {
            instance = new BaseIhm();
        }
        return instance;
    }

    public void start() {
        String choix;
        do {
            System.out.println("##### Application de système de gestion inventaire #####");
            System.out.println("##### MENU PRINCIPAL #####");
            System.out.println("1. Gérer les articles");
            System.out.println("2. Gérer les clients");
            System.out.println("3. Gérer les ventes");
            System.out.println("4. Générer des rapports");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextLine();

            switch (choix) {
                case "1"-> articleIhm.start();
                case "2" -> clientIhm.start();
                case "3" -> venteIhm.start();
                case "4" -> rapportIhm.start();
                case "0" -> System.out.println("Au revoir !");
                default -> System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (!choix.equals("0"));
    }

}
