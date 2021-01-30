package ru.zhevnov.coffeeTime.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.zhevnov.coffeeTime.entity.BasketItem;
import ru.zhevnov.coffeeTime.entity.Employee;
import ru.zhevnov.coffeeTime.entity.Product;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BasketDao implements IBasketDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void addProductToBasket(int employeeId, int productId, int count) {
        Employee employee = sessionFactory.getCurrentSession().get(Employee.class, employeeId);
        if (employee.getBasket().getBasketItems().stream().anyMatch(basketItem -> basketItem.getProducts().get(0).getId() == productId)) {
            BasketItem basketItem = employee.getBasket().getBasketItems().stream().filter(s -> s.getProducts().get(0).getId() == productId).collect(Collectors.toList()).get(0);
            int firstCountInBasketItem = basketItem.getQuantity();
            basketItem.setQuantity(firstCountInBasketItem + count);
            sessionFactory.getCurrentSession().update(basketItem);
        } else {
            Product product = sessionFactory.getCurrentSession().get(Product.class, productId);
            BasketItem basketItem = new BasketItem(employee.getBasket(), count);
            product.getBasketItems().add(basketItem);
            sessionFactory.getCurrentSession().update(product);
            sessionFactory.getCurrentSession().save(basketItem);
        }
    }

    public void updateCount(Employee employee1, int idProduct, int count) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Employee employee = session.get(Employee.class, employee1.getId());
            BasketItem basketItem = employee.getBasket().getBasketItems().stream().filter(s -> s.getProducts().get(0).getId() == idProduct).collect(Collectors.toList()).get(0);
            basketItem.setQuantity(count);
            session.update(basketItem);
            session.getTransaction().commit();
        }
    }

    @Transactional
    public void deleteItem(Employee employee1, int idProduct) {
        Employee employee = sessionFactory.getCurrentSession().get(Employee.class, employee1.getId());
        BasketItem basketItem = employee.getBasket().getBasketItems().stream().filter(s -> s.getProducts().get(0).getId() == idProduct).collect(Collectors.toList()).get(0);
        BasketItem newB = sessionFactory.getCurrentSession().get(BasketItem.class, basketItem.getId());
        Product product = sessionFactory.getCurrentSession().get(Product.class, idProduct);
//        employee.getBasket().getBasketItems().remove(newB);
        product.getBasketItems().remove(basketItem);
//        BasketItem productItem = product.getBasketItems().stream().filter(s -> s.getProducts().get(0).getId() == idProduct).collect(Collectors.toList()).get(0);
//        sessionFactory.getCurrentSession().update(product);
        System.out.println(newB);
//        sessionFactory.getCurrentSession().delete(newB);
//        sessionFactory.getCurrentSession().update(newB);
//        sessionFactory.getCurrentSession().delete(basketItem);
    }

    @Transactional
    public List<BasketItem> returnListOfProductsInBasket(Employee employee) {
        Employee e = sessionFactory.getCurrentSession().get(Employee.class, employee.getId());
        System.out.println(e.getBasket().getBasketItems());
        return e.getBasket().getBasketItems();
    }
}
