package ru.zhevnov.coffeeTime.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.zhevnov.coffeeTime.entity.Client;

import javax.transaction.Transactional;

@Repository
public class ClientDao implements IClientDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void registerNewClient(String name, String phoneNumber) {
        sessionFactory.getCurrentSession().save(new Client(name, phoneNumber, 1));
    }
}
