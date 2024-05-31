package org.example.service;

import org.example.Util.Categorie;
import org.example.entity.Article;
import org.example.entity.Vente;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RapportService extends BaseService {
    ArticleService articleService;
    VenteService venteService;
    ClientService clientService;

    public RapportService() {
        articleService = new ArticleService();
        venteService = new VenteService();
        clientService = new ClientService();
    }

    public List<Article> stockDisponible() {
        session = sessionFactory.openSession();
        List<Article> articles = session.createQuery
                ("from Article where quantiteEnStock > 0", Article.class).list();
        session.close();
        return articles;
    }

    public Map<Categorie, Double> ventesParCategorie() {
        session = sessionFactory.openSession();
        Map<Categorie, Double> ventesParCategorie = new HashMap<>();
        Query query = session.createQuery(
                "SELECT article.categorie, SUM(vente.prix) " +
                        "FROM Vente vente " +
                        "JOIN vente.articles article " +
                        "GROUP BY article.categorie"
        );

        List<Object[]> results = query.getResultList();
        for (Object[] result : results) {
            Categorie categorie = (Categorie) result[0];
            Double montantVente = (Double) result[1];
            ventesParCategorie.put(categorie, montantVente);
        }

        return ventesParCategorie;
    }
}


