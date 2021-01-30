package ru.zhevnov.coffeeTime.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class OrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void saveNewOrder(){
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();

            session.getTransaction().commit();
        }
    }
}
