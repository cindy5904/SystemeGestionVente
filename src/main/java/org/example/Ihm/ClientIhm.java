package org.example.Ihm;

import org.example.Util.Categorie;
import org.example.entity.Article;
import org.example.entity.Client;
import org.example.service.ArticleService;
import org.example.service.ClientService;

import java.util.List;
import java.util.Scanner;

public class ClientIhm {
    ClientService clientService;
    Scanner scanner;

    public ClientIhm() {
        scanner = new Scanner(System.in);
        clientService = new ClientService();
    }

    public void start(){
        String choice;
        do {
            menu();
            choice = scanner.nextLine();
            switch (choice){
                case "1" -> ajouterClient();
                case "2" -> modifierClient();
                case "3" -> supprimerClient();
                case "4" ->afficherTousLesClients();
                case "5" ->afficherUnClient();
                case "0" -> System.out.println("Quitter");
                default -> System.out.println("choix invalide");
            }

        }while (!choice.equals("0"));
        clientService.close();
    }
    private void menu(){
        System.out.println("#######  Menu  ######");
        System.out.println("1. Créer un client");
        System.out.println("2. Modifier un client");
        System.out.println("3. Supprimer un client");
        System.out.println("4. Afficher tous les clients");
        System.out.println("5. Afficher un client");
        System.out.println("0. Quitter");
        System.out.println("Votre choix :");
    }

   public void ajouterClient() {
       System.out.println("*****Créer un client*****");
       System.out.print("Entrez le nom du client : ");
       String nom = scanner.nextLine();
       System.out.print("Entrez l'adresse mail du client : ");
       String mail = scanner.nextLine();
       Client client = new Client().builder()
               .nom(nom)
               .email(mail)
               .build();

       boolean succes = clientService.create(client);
       if(succes) {
           System.out.println("Client crée avec succès");
       } else {
           System.out.println("Erreur lors de la création du client");
       }

   }

   public void modifierClient() {
       System.out.println("*****Modifier un un client*****");

       System.out.print("Choisissez le numéro du client à modifier : ");
       int choixClient = scanner.nextInt();
       scanner.nextLine();

       Client clientAModifier = clientService.findById(choixClient);
       if (clientAModifier == null) {
           System.out.println("Aucun non trouvé.");
           return;
       }
       System.out.println("Entrer le nouveau nom : ");
       String nouveauNom = scanner.nextLine();
       clientAModifier.setNom(nouveauNom);
       scanner.nextLine();
       String nouvelEmail = scanner.nextLine();
       clientAModifier.setEmail(nouvelEmail);
       scanner.nextLine();

       boolean succes = clientService.update(clientAModifier);
       if (succes) {
           System.out.println("Client modifié avec succès.");
       } else {
           System.out.println("Erreur lors de la modification du client.");
       }
   }

   public void supprimerClient() {
       System.out.println("****Suppression d'un client****");
       System.out.print("Entrez l'identifiant du client à supprimer : ");
       int idClient = scanner.nextInt();
       scanner.nextLine();

       Client clientASuprrimer = clientService.findById(idClient);

       if (clientASuprrimer == null) {
           System.out.println("Aucun non trouvé.");
           return;
       }
       boolean succes = clientService.delete(clientASuprrimer);
       if (succes) {
           System.out.println("Client supprimé avec succès.");
       } else {
           System.out.println("Erreur lors de la suppression du client.");
       }


   }

   public void afficherTousLesClients() {
       System.out.println("****Afficher tous les clients****");
       List<Client> clients = clientService.findAll();
       for (Client client : clients) {
           System.out.println(client);
       }
   }

   public void afficherUnClient() {
       System.out.println("****Afficher un client par son Id****");
       int idClient = scanner.nextInt();
       scanner.nextLine();

       Client client = clientService.findById(idClient);

       if (client == null) {
           System.out.println("Aucun client trouvé avec cet identifiant.");
       } else {

           System.out.println("Détails du client : ");
           System.out.println(client);
       }
   }

}
