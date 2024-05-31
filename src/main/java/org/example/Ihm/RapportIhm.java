package org.example.Ihm;

import org.example.Util.Categorie;
import org.example.entity.Article;
import org.example.entity.Vente;
import org.example.service.ArticleService;
import org.example.service.ClientService;
import org.example.service.RapportService;
import org.example.service.VenteService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RapportIhm {
    Scanner scanner;
    ClientService clientService;
    VenteService venteService;
    ArticleService articleService;
    RapportService rapportService;

    public RapportIhm() {
        scanner = new Scanner(System.in);
        clientService = new ClientService();
        venteService = new VenteService();
        articleService = new ArticleService();
        rapportService = new RapportService();

    }
    public void start(){
        String choice;
        do {
            menu();
            choice = scanner.nextLine();
            switch (choice){
                case "1" -> rapportStockDisponible();
                case "2" -> rapportVenteRealisee();
                case "3" -> rapportPerformanceArticle();
                case "4" -> statistiqueVenteParCategorie();
                case "5" -> statistiqueVenteParClient();
                case "0" -> System.out.println("Quitter");
                default -> System.out.println("choix invalide");
            }

        }while (!choice.equals("0"));
        articleService.close();
    }
    private void menu(){
        System.out.println("#######  Menu Rapport ######");
        System.out.println("1. Afficher le rapport des stocks disponibles");
        System.out.println("2. Afficher le rapport des ventes réalisees");
        System.out.println("3. Afficher le rapport des performances des articles");
        System.out.println("4. Fournir les statistiques sur les ventes par catégorie");
        System.out.println("5. Fournir les statistiques sur les ventes par client");
        System.out.println("0. Quitter");
        System.out.println("Votre choix :");
    }

    public void rapportStockDisponible() {
        System.out.println("***Stock disponible***");
        List<Article> articles = rapportService.stockDisponible();
        for (Article article : articles) {
            System.out.println("Article : " + article.getDescription());
            System.out.println("Quantité en stock : " + article.getQuantiteEnStock());
            System.out.println("-----------------------------------------");
        }
    }

    public void rapportVenteRealisee() {
        System.out.println("****Rapport des ventes Réalisées****");
        List<Vente> ventes = venteService.findAll();
        int nombreVentes = ventes.size();
        System.out.println("Nombre de ventes réalisées : " + nombreVentes);
    }

    public void rapportPerformanceArticle() {
        System.out.println("En cours...");
    }

    public void statistiqueVenteParCategorie() {
        System.out.println("En cours...");
    }

    public void statistiqueVenteParClient() {
        System.out.println("En cours....");
    }
}
