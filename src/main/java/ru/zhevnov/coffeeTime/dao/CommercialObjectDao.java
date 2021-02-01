package ru.zhevnov.coffeeTime.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.zhevnov.coffeeTime.entity.CommercialObject;
import ru.zhevnov.coffeeTime.entity.Shift;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CommercialObjectDao implements ICommercialObjectDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public List<CommercialObject> returnListOdCommercialObjects() {
        return sessionFactory.getCurrentSession().createQuery("from CommercialObject").list();
    }

    @Transactional
    public CommercialObject returnCommercialObjectById(int commercialObjectId) {
        return sessionFactory.getCurrentSession().get(CommercialObject.class, commercialObjectId);
    }

}
