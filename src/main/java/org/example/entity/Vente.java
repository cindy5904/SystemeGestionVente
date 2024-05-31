package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.Util.EtatVente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@Builder
@AllArgsConstructor
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private EtatVente etatVente;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vente_article", joinColumns = @JoinColumn(name = "vente_id"), inverseJoinColumns = @JoinColumn(name = "article_id"))
    private List<Article> articles = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Vente() {

    }

    @Override
    public String toString() {
        return "Vente{" +
                "id=" + id +
                ", etatVente=" + etatVente +
                ", articles=" + articles +
                ", client=" + client +
                '}';
    }
}
