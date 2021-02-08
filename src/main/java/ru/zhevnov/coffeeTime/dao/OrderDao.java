package ru.zhevnov.coffeeTime.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.zhevnov.coffeeTime.entity.*;
import ru.zhevnov.coffeeTime.service.IBasketService;
import ru.zhevnov.coffeeTime.service.IClientService;
import ru.zhevnov.coffeeTime.service.ICommercialObjectService;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Time;

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

    @Transactional
    public void saveNewOrder(int idEmployee, String phoneNumber, String paymentType) {
//        sessionFactory.getCurrentSession().clear();
        Employee employee = sessionFactory.getCurrentSession().get(Employee.class, idEmployee);
        if (!employee.getBasket().getBasketItems().isEmpty()) {
            Date date = new Date(System.currentTimeMillis());
            Time time = new Time(System.currentTimeMillis());
            Client client = clientService.returnClientByPhoneNumber(phoneNumber);
            double totalCostOfOrder = Double.parseDouble(basketService.returnTotalCostOfTheOrder(employee.getId(), ""));
            Order newOrder;
            if (client == null) {
                newOrder = new Order(date, time, totalCostOfOrder, 0, employee, null, paymentType, null);
                sessionFactory.getCurrentSession().save(newOrder);
            } else {
                newOrder = new Order(date, time, totalCostOfOrder, client.getDiscount(), employee, client, paymentType, "");
                clientService.addOnePercentToDiscount(client.getId());
//                sessionFactory.getCurrentSession().update(client.getDiscount());
                sessionFactory.getCurrentSession().save(newOrder);
            }
            putProductsInOrderItemFromBasketItem(employee.getId(), newOrder.getId());
//            commercialObjectService.submitItemsFromCommercialObjectsStorage(employee.getId());
            basketService.cleanBasket(employee.getId());
        }
    }


    @Transactional
    public void putProductsInOrderItemFromBasketItem(int employeeId, int orderId){
        Employee employee = sessionFactory.getCurrentSession().get(Employee.class, employeeId);
        Order order = sessionFactory.getCurrentSession().get(Order.class, orderId);
        for(BasketItem basketItem : employee.getBasket().getBasketItems()){
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
}
