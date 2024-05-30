package org.example.Ihm;

import org.example.Util.Categorie;
import org.example.entity.Article;
import org.example.service.ArticleService;

import java.util.List;
import java.util.Scanner;

public class ArticleIhm {
    private ArticleService articleService;
    private Scanner scanner;

    public ArticleIhm() {
        scanner = new Scanner(System.in);
        articleService = new ArticleService();
    }

    public void start(){
        String choice;
        do {
            menu();
            choice = scanner.nextLine();
            switch (choice){
                case "1" -> ajouterArticle();
                case "2" -> modifierArticle();
                case "3" -> suppressionArticle();
                case "4" -> afficherTousLesArticles();
                case "0" -> System.out.println("Quitter");
                default -> System.out.println("choix invalide");
            }

        }while (!choice.equals("0"));
        articleService.close();
    }
    private void menu(){
        System.out.println("#######  Menu  ######");
        System.out.println("1. Ajouter un article");
        System.out.println("2. Modifier un article");
        System.out.println("3. Supprimer un article");
        System.out.println("4. Consulter les articles");
        System.out.println("0. Quitter");
        System.out.println("Votre choix :");
    }

    private void ajouterArticle() {
        System.out.println("*****Créer un article*****");
        ArticleService articleService = new ArticleService();
        System.out.print("Entrez la description de l'article: ");
        String description = scanner.nextLine();
        System.out.print("Entrez la taille de l'article: ");
        String taille = scanner.nextLine();
        System.out.print("Entrez le prix de l'article: ");
        double prix = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Entrez la quantité de l'article: ");
        int quantite = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Choisissez une catégorie : ");
        for (Categorie categorie : Categorie.values()) {
            System.out.println(categorie.ordinal() + 1 + ". " + categorie);
        }
        System.out.print("Votre choix : ");
        int categorieChoix = scanner.nextInt();
        scanner.nextLine();

        Categorie categorie = Categorie.values()[categorieChoix - 1];
        Article article = new Article().builder()
                .description(description)
                .categorie(categorie)
                .taille(taille)
                .prix(prix)
                .quantiteEnStock(quantite)
                .build();

        boolean succes = articleService.create(article);
        if(succes) {
            System.out.println("Article crée avec succès");
        } else {
            System.out.println("Erreur lors de la création de l'article");
        }

    }

    public void modifierArticle() {
        System.out.println("*****Modifier un article*****");

        System.out.print("Choisissez le numéro de l'article à modifier : ");
        int choixArticle = scanner.nextInt();
        scanner.nextLine();

        Article articleAModifier = articleService.findById(choixArticle);
        if (articleAModifier == null) {
            System.out.println("Aucun non trouvé.");
            return;
        }

        System.out.print("Nouvelle description : ");
        String nouvelleDescription = scanner.nextLine();
        articleAModifier.setDescription(nouvelleDescription);

        System.out.print("Nouvelle taille : ");
        String nouvelleTaille = scanner.nextLine();
        articleAModifier.setTaille(nouvelleTaille);

        System.out.print("Nouveau prix : ");
        double nouveauPrix = scanner.nextDouble();
        articleAModifier.setPrix(nouveauPrix);
        scanner.nextLine();

        System.out.print("Nouvelle quantité : ");
        int nouvelleQuantite = scanner.nextInt();
        articleAModifier.setQuantiteEnStock(nouvelleQuantite);
        scanner.nextLine();

        System.out.println("Choisissez une nouvelle catégorie : ");
        for (Categorie categorie : Categorie.values()) {
            System.out.println(categorie.ordinal() + 1 + ". " + categorie);
        }
        System.out.print("Votre choix : ");
        int categorieChoix = scanner.nextInt();
        scanner.nextLine();

        Categorie nouvelleCategorie = Categorie.values()[categorieChoix - 1];
        articleAModifier.setCategorie(nouvelleCategorie);

        boolean succes = articleService.update(articleAModifier);
        if (succes) {
            System.out.println("Article modifié avec succès.");
        } else {
            System.out.println("Erreur lors de la modification de l'article.");
        }
    }

    public void suppressionArticle() {
        System.out.println("****Suppression d'un article****");
        System.out.print("Entrez l'identifiant de l'article à supprimer : ");
        int idArticle = scanner.nextInt();
        scanner.nextLine();

        Article articleASupprimer = articleService.findById(idArticle);

        if (articleASupprimer == null) {
            System.out.println("Aucun non trouvé.");
            return;
        }
        boolean succes = articleService.delete(articleASupprimer);
        if (succes) {
            System.out.println("Article supprimé avec succès.");
        } else {
            System.out.println("Erreur lors de la suppression de l'article.");
        }

    }

    public void afficherTousLesArticles() {
        System.out.println("****Afficher tous les articles****");
        List<Article> articles = articleService.findAll();
        for (Article article : articles) {
            System.out.println(article);
        }

    }


}



