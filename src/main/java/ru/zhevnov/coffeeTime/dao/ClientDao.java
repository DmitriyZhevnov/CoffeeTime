package ru.zhevnov.coffeeTime.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.zhevnov.coffeeTime.entity.Client;
import ru.zhevnov.coffeeTime.entity.Employee;


import javax.transaction.Transactional;

@Repository
public class ClientDao implements IClientDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void registerNewClient(String name, String phoneNumber) {
        sessionFactory.getCurrentSession().save(new Client(name, phoneNumber, 1));
    }

    @Transactional
    public Client returnClientByPhoneNumber(String phoneNumber) {
        if (phoneNumber.isEmpty()) {
            return null;
        } else {
            Query query = sessionFactory.getCurrentSession().createQuery("select id from Client where phoneNumber = :phoneNumber");
            query.setParameter("phoneNumber", phoneNumber);
            return sessionFactory.getCurrentSession().get(Client.class, Integer.parseInt(query.list().get(0).toString()));
        }
    }

    @Transactional
    public void addOnePercentToDiscount(int idClient) {
        sessionFactory.getCurrentSession().clear();
        Client client = sessionFactory.getCurrentSession().get(Client.class, idClient);
        int discount = client.getDiscount();
        if (discount < 20){
            client.setDiscount(++discount);
        }
        sessionFactory.getCurrentSession().update(client);
    }
}
