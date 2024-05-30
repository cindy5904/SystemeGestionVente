package org.example.service;

import org.example.entity.Article;
import org.example.interfaces.Repository;

import java.util.List;

public class ArticleService extends BaseService implements Repository<Article> {
    public ArticleService() {
        super();
    }

    @Override
    public boolean create(Article o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Article o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Article o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Article findById(int id) {
        session = sessionFactory.openSession();
        Article article = session.get(Article.class,id);
        session.close();
        return article;
    }
    @Override
    public List<Article> findAll() {
        session = sessionFactory.openSession();
        List<Article> articles = session.createQuery("from Article", Article.class).list();
        session.close();
        return articles;
    }
    public void close(){
        sessionFactory.close();
    }
}
