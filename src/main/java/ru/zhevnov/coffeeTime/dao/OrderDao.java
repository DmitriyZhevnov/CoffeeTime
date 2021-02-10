package ru.zhevnov.coffeeTime.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.zhevnov.coffeeTime.entity.*;
import ru.zhevnov.coffeeTime.service.IBasketService;
import ru.zhevnov.coffeeTime.service.IClientService;
import ru.zhevnov.coffeeTime.service.ICommercialObjectService;
import ru.zhevnov.coffeeTime.service.IShiftService;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class OrderDao implements IOrderDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private IBasketService basketService;
    @Autowired
    private IClientService clientService;
    @Autowired
    private ICommercialObjectService commercialObjectService;
    @Autowired
    private IShiftService shiftService;

    @Transactional
    public void saveNewOrder(int idEmployee, String phoneNumber, String paymentType, String card, String cash) {
        Employee employee = sessionFactory.getCurrentSession().get(Employee.class, idEmployee);
        if (!employee.getBasket().getBasketItems().isEmpty()) {
            Date date = new Date(System.currentTimeMillis());
            Time time = new Time(System.currentTimeMillis());
            Client client = clientService.returnClientByPhoneNumber(phoneNumber);
            double totalCostOfOrder;
            Order newOrder;
            if (client == null) {
                totalCostOfOrder = Double.parseDouble(basketService.returnTotalCostOfTheOrder(employee.getId(), ""));
                if (paymentType.equals("cash")) {
                    newOrder = new Order(date, time, totalCostOfOrder, 0.0, 0, employee, null, paymentType, null);
                } else if (paymentType.equals("card")) {
                    newOrder = new Order(date, time, 0.0, totalCostOfOrder, 0, employee, null, paymentType, null);
                } else {
                    double cashAmount = Double.parseDouble(cash);
                    double cardAmount = Double.parseDouble(card);
                    newOrder = new Order(date, time, cashAmount, cardAmount, 0, employee, null, paymentType, null);
                }
            } else {
                totalCostOfOrder = Double.parseDouble(basketService.returnTotalCostOfTheOrder(employee.getId(), client.getPhoneNumber()));
                if (paymentType.equals("cash")) {
                    newOrder = new Order(date, time, totalCostOfOrder, 0.0, client.getDiscount(), employee, client, paymentType, null);
                } else if (paymentType.equals("card")) {
                    newOrder = new Order(date, time, 0.0, totalCostOfOrder, client.getDiscount(), employee, client, paymentType, null);
                } else {
                    double cashAmount = Double.parseDouble(cash);
                    double cardAmount = Double.parseDouble(card);
                    newOrder = new Order(date, time, cashAmount, cardAmount, client.getDiscount(), employee, client, paymentType, null);
                }
                clientService.addOnePercentToDiscount(client.getId());
            }
            System.out.println(newOrder);
            sessionFactory.getCurrentSession().save(newOrder);
            putProductsInOrderItemFromBasketItem(employee.getId(), newOrder.getId());
            basketService.cleanBasket(employee.getId());
        }
    }


    @Transactional
    public void putProductsInOrderItemFromBasketItem(int employeeId, int orderId) {
        Employee employee = sessionFactory.getCurrentSession().get(Employee.class, employeeId);
        Order order = sessionFactory.getCurrentSession().get(Order.class, orderId);
        for (BasketItem basketItem : employee.getBasket().getBasketItems()) {
            Product product = sessionFactory.getCurrentSession().get(Product.class, basketItem.getProducts().get(0).getId());
            OrderItem orderItem = new OrderItem();
            orderItem.setProducts(basketItem.getProducts());
            orderItem.setQuantity(basketItem.getQuantity());
            orderItem.setOrder(order);
            product.getOrderItems().add(orderItem);
            sessionFactory.getCurrentSession().update(product);
            sessionFactory.getCurrentSession().save(orderItem);
        }
    }

    @Transactional
    public List<Order> returnAllOrdersByEmployeeId(int employeeId) {
        Date date = new Date(System.currentTimeMillis());
        Query query = sessionFactory.getCurrentSession()
                .createQuery("from Order where employee.id = :idEmployee and date = :date");
        query.setParameter("date", date);
        query.setParameter("idEmployee", employeeId);
        return query.list();
    }

    @Transactional
    public Order returnOrderById(int orderId){
        return sessionFactory.getCurrentSession().get(Order.class, orderId);
    }
}
