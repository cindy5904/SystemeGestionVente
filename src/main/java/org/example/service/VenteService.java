package org.example.service;

import org.example.entity.Article;
import org.example.entity.Client;
import org.example.entity.Vente;
import org.example.interfaces.Repository;
import org.hibernate.query.Query;

import java.util.List;

public class VenteService extends BaseService implements Repository<Vente> {


    @Override
    public boolean create(Vente o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Vente o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Vente o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Vente findById(int id) {
        session = sessionFactory.openSession();
        Vente vente = session.get(Vente.class,id);
        session.close();
        return vente;
    }

    @Override
    public List<Vente> findAll() {
        session = sessionFactory.openSession();
        List<Vente> ventes = session.createQuery("SELECT DISTINCT v FROM Vente v LEFT JOIN FETCH v.client LEFT JOIN FETCH v.articles", Vente.class).list();
        session.close();
        return ventes;
    }

    public Vente genereRecu() {
        session = sessionFactory.openSession();
        Vente vente = session.createQuery("SELECT DISTINCT v FROM Vente v LEFT JOIN FETCH v.client LEFT JOIN FETCH v.articles", Vente.class).getSingleResult();
        session.close();
        return vente;
    }

    public double TotalPrixRecu(Vente vente) {

    }

    public List<Article> detailsArticlesVente(Vente vente) {
        return vente.getArticles();
    }




    public void close(){
        sessionFactory.close();
    }
}
