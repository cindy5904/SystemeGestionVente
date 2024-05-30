package org.example.Ihm;

import org.example.Util.Categorie;
import org.example.Util.EtatVente;
import org.example.entity.Article;
import org.example.entity.Client;
import org.example.entity.Vente;
import org.example.service.ArticleService;
import org.example.service.ClientService;
import org.example.service.VenteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VenteIhm {
    VenteService venteService;
    ClientService clientService;
    ArticleService articleService;
    Scanner scanner;

    public VenteIhm() {
        scanner = new Scanner(System.in);
        venteService = new VenteService();
        clientService = new ClientService();
        articleService = new ArticleService();

    }

    public void start(){
        String choice;
        do {
            menu();
            choice = scanner.nextLine();
            switch (choice){
                case "1" -> ajouterVente();
                case "2" -> modifierVente();
                case "3" -> suivreLEtatDUneVente();
                case "4" -> afficherToutesLesVentes();
                case "5" -> genererUnRecuPourUnClient();
                case "0" -> System.out.println("Quitter");
                default -> System.out.println("choix invalide");
            }

        }while (!choice.equals("0"));
        venteService.close();
    }
    private void menu(){
        System.out.println("#######  Menu  ######");
        System.out.println("1. Ajouter une vente");
        System.out.println("2. Modifier une vente");
        System.out.println("3. Suivre l'état d'une vente");
        System.out.println("4. Consulter les ventes");
        System.out.println("5. Générer le reçu");
        System.out.println("0. Quitter");
        System.out.println("Votre choix :");
    }

    public void ajouterVente() {
        System.out.println("*****Créer un article*****");
        System.out.print("Numero Client fidélité : ");
        int choixClient = scanner.nextInt();
        scanner.nextLine();

        Client clientChoisi = clientService.findById(choixClient);
        if (clientChoisi == null) {
            System.out.println("Client non trouvée.");
            return;
        }

        System.out.print("Combien d'articles allez-vous scanner ? ");
        int nombreArticles = scanner.nextInt();
        scanner.nextLine();

        List<Article> articles = new ArrayList<>();
        for (int i = 0; i < nombreArticles; i++) {
            System.out.print("Scannage de l'article ref " + (i + 1) + " : ");
            int choixArticle = scanner.nextInt();
            scanner.nextLine();

            Article articleScanne = articleService.findById(choixArticle);
            if (articleScanne == null) {
                System.out.println("L'article avec la référence " + choixArticle + " n'existe pas.");
                return;
            } else {
                articles.add(articleScanne);
            }
        }
        VenteService venteService  = new VenteService();

        System.out.println("Choisissez l'état de la vente : ");
        for (EtatVente etatVente : EtatVente.values()) {
            System.out.println(etatVente.ordinal() + 1 + ". " + etatVente);
        }
        System.out.print("Votre choix : ");
        int etatVenteChoix = scanner.nextInt();
        scanner.nextLine();

        EtatVente etatVente = EtatVente.values()[etatVenteChoix - 1];
        Vente vente = new Vente().builder()
                .client(clientChoisi)
                .articles(articles)
                .etatVente(etatVente)
                .build();


        boolean succes = venteService.create(vente);
        if(succes) {
            System.out.println("Vente crée avec succès");
        } else {
            System.out.println("Erreur lors de la création de l'article");
        }
    }

    public void modifierVente() {

    }

    public void suivreLEtatDUneVente() {
        System.out.println("*****Suivre l'état d'une vente*****");
        System.out.print("Choisissez le numéro de la vente pour suivre son état : ");
        int choixVente = scanner.nextInt();
        scanner.nextLine();
        Vente vente = venteService.findById(choixVente);
        if (vente == null) {
            System.out.println("Vente non trouvée.");
            return;
        }

        System.out.println("Détails de la vente : ");
        System.out.println("ID de la vente : " + vente.getId());
        System.out.println("État de la vente : " + vente.getEtatVente());
        System.out.println("Client : " + vente.getClient().getNom());


    }

    public void afficherToutesLesVentes() {
        System.out.println("****Afficher toutes les ventes****");
        List<Vente> ventes = venteService.findAll();
        for (Vente vente : ventes) {
            System.out.println(vente);
        }

    }

    public void genererUnRecuPourUnClient() {
        System.out.println("***** Générer un reçu de la vente pour le client *****");
        System.out.println("Numéro de la vente(id) : ");
        int choixVente = scanner.nextInt();
        scanner.nextLine();
        Vente vente = venteService.findById(choixVente);
        if (vente == null) {
            System.out.println("Vente non trouvée.");
            return;
        }
        double somme = venteService.TotalPrixRecu(vente);


        System.out.println("Détails de la vente : ");
        System.out.println("Reference de la vente(id) : " + vente.getId());
        System.out.println("Client : " + vente.getClient().getNom());
        System.out.println("État de la vente : " + vente.getEtatVente());
        System.out.println("Article : ");
        List<Article> articles = venteService.detailsArticlesVente(vente);
        for (Article article : articles) {
            System.out.println("Description : " + article.getDescription());
            System.out.println("Quantité : " + article.getQuantiteEnStock());
            System.out.println("Prix unitaire : " + article.getPrix());
            System.out.println("-----------------------------------------");
        }
        System.out.println("Somme totale : " + somme);
        }



    }

