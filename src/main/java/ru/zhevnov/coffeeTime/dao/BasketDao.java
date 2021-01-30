package ru.zhevnov.coffeeTime.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.zhevnov.coffeeTime.entity.BasketItem;
import ru.zhevnov.coffeeTime.entity.Employee;
import ru.zhevnov.coffeeTime.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BasketDao implements IBasketDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addProductToBasket(int personId, int productId, int count) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Employee employee = session.get(Employee.class, personId);
            if (employee.getBasket().getBasketItems().stream().anyMatch(basketItem -> basketItem.getProducts().get(0).getId() == productId)) {
                BasketItem basketItem = employee.getBasket().getBasketItems().stream().filter(s -> s.getProducts().get(0).getId() == productId).collect(Collectors.toList()).get(0);
                int firstCountInBasketItem = basketItem.getQuantity();
                basketItem.setQuantity(firstCountInBasketItem + count);
                session.update(basketItem);
                session.getTransaction().commit();
            } else {
                Product product = session.get(Product.class, productId);
                List<Product> tt = new ArrayList<>();
                tt.add(product);
                BasketItem basketItem = new BasketItem();//(employee.getBasket(), count, tt);
                session.save(basketItem);
                product.getBasketItems().add(basketItem);
                session.update(product);
                employee.getBasket().getBasketItems().add(basketItem);
                session.update(employee);
                session.getTransaction().commit();
            }
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

    public void deleteItem(Employee employee1, int idProduct) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Employee employee = session.get(Employee.class, employee1.getId());
            BasketItem basketItem = employee.getBasket().getBasketItems().stream().filter(s -> s.getProducts().get(0).getId() == idProduct).collect(Collectors.toList()).get(0);
            Product product = session.get(Product.class, idProduct);
            BasketItem productItem = product.getBasketItems().stream().filter(s -> s.getProducts().get(0).getId() == idProduct).collect(Collectors.toList()).get(0);
            session.delete(basketItem);
            session.delete(productItem);
            session.getTransaction().commit();
        }
    }

    public List<BasketItem> returnListOfProductsInBasket(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Employee e = session.get(Employee.class, employee.getId());
            List<Product> list = new ArrayList<>();
            e.getBasket().getBasketItems().stream().forEach(basketItem -> list.add(basketItem.getProducts().get(0)));
            session.getTransaction().commit();
            return e.getBasket().getBasketItems();
        }
    }
}
