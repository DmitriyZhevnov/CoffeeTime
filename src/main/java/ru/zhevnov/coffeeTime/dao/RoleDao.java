package ru.zhevnov.coffeeTime.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.zhevnov.coffeeTime.entity.Role;

import javax.transaction.Transactional;

@Repository
public class RoleDao implements IRoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public Role returnUserValue(){
            return sessionFactory.getCurrentSession().get(Role.class, 2);
    }

    public void addNewRole(String name){
//        try(Session session = sessionFactory.openSession()){
//            session.beginTransaction();
//            Role roleUser = new Role(name);
//            session.save(roleUser);
//            session.getTransaction().commit();
//            System.out.println(roleUser);
//        }
    }
}
