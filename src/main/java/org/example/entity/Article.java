package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.Util.Categorie;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String description;
    private Categorie categorie;
    private String taille;
    private double prix;
    private int quantiteEnStock;
    @ManyToMany(mappedBy = "articles", fetch = FetchType.LAZY)
    private List<Vente> ventes = new ArrayList<>();

    public Article() {

    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", categorie=" + categorie +
                ", taille='" + taille + '\'' +
                ", prix=" + prix +
                ", quantiteEnStock=" + quantiteEnStock +
                '}';
    }
}
