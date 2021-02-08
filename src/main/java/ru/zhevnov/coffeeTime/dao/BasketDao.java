package ru.zhevnov.coffeeTime.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.zhevnov.coffeeTime.entity.BasketItem;
import ru.zhevnov.coffeeTime.entity.Employee;
import ru.zhevnov.coffeeTime.entity.Product;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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

    public void updateCount(int idEmployee, int idProduct, int count) {
//        try (Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            Employee employee = session.get(Employee.class, employee1.getId());
//            BasketItem basketItem = employee.getBasket().getBasketItems().stream().filter(s -> s.getProducts().get(0).getId() == idProduct).collect(Collectors.toList()).get(0);
//            basketItem.setQuantity(count);
//            session.update(basketItem);
//            session.getTransaction().commit();
//        }
    }

    @Transactional
    public void deleteItem(int idEmployee, int idProduct) {
        Employee employee = sessionFactory.getCurrentSession().get(Employee.class, idEmployee);
        BasketItem basketItem = employee.getBasket().getBasketItems().stream().filter(s -> s.getProducts().get(0).getId() == idProduct).collect(Collectors.toList()).get(0);
        Product product = sessionFactory.getCurrentSession().get(Product.class, idProduct);
        employee.getBasket().getBasketItems().remove(basketItem);
        product.getBasketItems().remove(basketItem);
        sessionFactory.getCurrentSession().delete(basketItem);
        sessionFactory.getCurrentSession().update(employee);
        sessionFactory.getCurrentSession().update(product);
    }

    @Transactional
    public void cleanBasket(int idEmployee){
        Employee employee = sessionFactory.getCurrentSession().get(Employee.class, idEmployee);
        while (!employee.getBasket().getBasketItems().isEmpty()){
            deleteItem(employee.getId(), employee.getBasket().getBasketItems().get(0).getProducts().get(0).getId());
        }
    }

    @Transactional
    public List<BasketItem> returnListOfProductsInBasket(int idEmployee) {
        Query query = sessionFactory.getCurrentSession().createQuery("from BasketItem where basket.employee.id =:employeeId");
        query.setParameter("employeeId", idEmployee);
        return query.list();
    }

    @Transactional
    public String returnTotalCostOfTheOrder(int idEmployee, String phoneNumber) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("select discount from Client where phoneNumber = :phoneNumber");
        query.setParameter("phoneNumber", phoneNumber);
        Query query2 = sessionFactory.getCurrentSession()
                .createQuery("select sum(p.price * bi.quantity) from Product p join p.basketItems bi join bi.basket ba join ba.employee e where e.id = :employeeId");
        query2.setParameter("employeeId", idEmployee);
        DecimalFormat format = new DecimalFormat("##.00");
        DecimalFormatSymbols dfs = format.getDecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        format.setDecimalFormatSymbols(dfs);
        try {
            double totalCost = Double.parseDouble(query2.list().get(0).toString());
            if (query.list().isEmpty()) {
                return format.format(totalCost);
            } else {
                return format.format(totalCost - (totalCost / 100 * Double.parseDouble(query.list().get(0).toString())));
            }
        } catch (NullPointerException e){
            return "0";
        }

    }
}
