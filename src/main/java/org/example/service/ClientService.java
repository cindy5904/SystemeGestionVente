package org.example.service;

import org.example.entity.Client;
import org.example.entity.Vente;
import org.example.interfaces.Repository;
import org.hibernate.query.Query;

import java.util.List;

public class ClientService extends BaseService implements Repository<Client> {
    public ClientService() {
    }

    @Override
    public boolean create(Client o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Client o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Client o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Client findById(int id) {
        session = sessionFactory.openSession();
        Client client = session.get(Client.class,id);
        session.close();
        return client;
    }

    @Override
    public List<Client> findAll() {
        session = sessionFactory.openSession();
        List<Client> clients = session.createQuery("from Client ", Client.class).list();
        session.close();
        return clients;
    }

    public List<Vente> getHistoriqueAchat(int clientId) {
        session = sessionFactory.openSession();
        String hql = "from Vente v where v.client.id = :clientId";
        Query<Vente> query = session.createQuery(hql, Vente.class);
        query.setParameter("clientId", clientId);
        List<Vente> historiqueAchat = query.list();
        session.close();
        return historiqueAchat;
    }

    public void close(){
        sessionFactory.close();
    }
}
