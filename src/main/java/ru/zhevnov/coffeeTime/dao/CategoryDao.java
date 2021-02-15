package ru.zhevnov.coffeeTime.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.zhevnov.coffeeTime.entity.Category;
import ru.zhevnov.coffeeTime.entity.Product;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CategoryDao implements ICategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public List<Product> returnAllCoffees() {
        return sessionFactory.getCurrentSession().createQuery("from Product where category = '1'").list();
    }
    @Transactional
    public List<Product> returnAllDrinks() {
        return sessionFactory.getCurrentSession().createQuery("from Product where category = '2'").list();
    }
    @Transactional
    public List<Product> returnAllAdditions() {
        return sessionFactory.getCurrentSession().createQuery("from Product where category = '3'").list();
    }
    @Transactional
    public List<Product> returnAllBars() {
        return sessionFactory.getCurrentSession().createQuery("from Product where category = '4'").list();
    }
    @Transactional
    public List<Category> returnAllCategories(){
        return sessionFactory.getCurrentSession().createQuery("from Category").list();
    }
}
