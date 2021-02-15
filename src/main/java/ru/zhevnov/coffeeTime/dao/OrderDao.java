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
import java.util.ArrayList;
import java.util.List;

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
            Shift currentShift = shiftService.returnOpenedShiftByEmployeeId(idEmployee);
            if (client == null) {
                totalCostOfOrder = Double.parseDouble(basketService.returnTotalCostOfTheOrder(employee.getId(), ""));
                if (paymentType.equals("cash")) {
                    newOrder = new Order(date, time, totalCostOfOrder, 0.0, 0, currentShift, null, paymentType, null);
                } else if (paymentType.equals("card")) {
                    newOrder = new Order(date, time, 0.0, totalCostOfOrder, 0, currentShift, null, paymentType, null);
                } else {
                    double cashAmount = Double.parseDouble(cash);
                    double cardAmount = Double.parseDouble(card);
                    newOrder = new Order(date, time, cashAmount, cardAmount, 0, currentShift, null, paymentType, null);
                }
            } else {
                totalCostOfOrder = Double.parseDouble(basketService.returnTotalCostOfTheOrder(employee.getId(), client.getPhoneNumber()));
                if (paymentType.equals("cash")) {
                    newOrder = new Order(date, time, totalCostOfOrder, 0.0, client.getDiscount(), currentShift, client, paymentType, null);
                } else if (paymentType.equals("card")) {
                    newOrder = new Order(date, time, 0.0, totalCostOfOrder, client.getDiscount(), currentShift, client, paymentType, null);
                } else {
                    double cashAmount = Double.parseDouble(cash);
                    double cardAmount = Double.parseDouble(card);
                    newOrder = new Order(date, time, cashAmount, cardAmount, client.getDiscount(), currentShift, client, paymentType, null);
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
    public void cancelOrder(int idOrder, String reason, String type) {
        Date date = new Date(System.currentTimeMillis());
        Time time = new Time(System.currentTimeMillis());
        Order order = sessionFactory.getCurrentSession().get(Order.class, idOrder);
        order.setCardAmount(0.0);
        order.setCashAmount(0.0);
        order.setPaymentType("cancelled");
        StringBuilder info;
        if (order.getInfo() == null) {
            info = new StringBuilder();
        } else {
            info = new StringBuilder(order.getInfo());
        }
        if (type.equals("withWriteOffProducts")) {
            info.append("| Отмена заказа " + date + " в " + time + " С списанием продуктов.\n Причина:" + reason + "\n");
        } else if (type.equals("withoutWriteOffProducts")) {
            info.append("| Отмена заказа " + date + " в " + time + " БЕЗ списания продуктов.\n Причина:" + reason + "\n");
            commercialObjectService.addItemsFromOrderInCommercialObjectsStorage(order.getId());
        }
        order.setInfo(info.toString());
        sessionFactory.getCurrentSession().update(order);
    }

    @Transactional
    public void changePaymentType(int idOrder, String type, String cash, String card, String reason) {
        Date date = new Date(System.currentTimeMillis());
        Time time = new Time(System.currentTimeMillis());
        Order order = sessionFactory.getCurrentSession().get(Order.class, idOrder);
        StringBuilder info;
        if (order.getInfo() == null) {
            info = new StringBuilder();
        } else {
            info = new StringBuilder(order.getInfo());
        }
        info.append("| Изменение типа оплаты " + date + " в " + time + ", с " + order.getPaymentType() +
                " на " + type + ".\n Причина:" + reason + "\n");
        order.setPaymentType(type);
        double totalCost = order.getCashAmount() + order.getCardAmount();
        if (type.equals("cash")) {
            order.setCashAmount(totalCost);
            order.setCardAmount(0.0);
        } else if (type.equals("card")) {
            order.setCashAmount(0.0);
            order.setCardAmount(totalCost);
        } else if (type.equals("different")) {
            order.setCashAmount(Double.parseDouble(cash));
            order.setCardAmount(Double.parseDouble(card));
        }
        order.setInfo(info.toString());
        sessionFactory.getCurrentSession().update(order);
    }

    @Transactional
    public List<Order> returnAllOrdersByEmployeeId(int employeeId) {
        Date date = new Date(System.currentTimeMillis());
        Shift shift = shiftService.returnOpenedShiftByEmployeeId(employeeId);
        Query query = sessionFactory.getCurrentSession().createQuery("select id from Shift where dateOpened = :date and commercialObject.id = :idCommercialObject and employee.id = :idEmployee");
        query.setParameter("date", date);
        query.setParameter("idCommercialObject", shift.getCommercialObject().getId());
        query.setParameter("idEmployee", employeeId);
        Query queryForFindOrders = sessionFactory.getCurrentSession().createQuery("from Order o where o.shift.id in (:idShifts)");
        queryForFindOrders.setParameter("idShifts", query.list());
        return queryForFindOrders.list();
    }

    @Transactional
    public List<Order> returnAllOrdersByCommercialObjectAndDate(int idCommercialObject, Date date) {
        Query query = sessionFactory.getCurrentSession().createQuery("select id from Shift where dateOpened = :date and commercialObject.id = :idCommercialObject");
        query.setParameter("date", date);
        query.setParameter("idCommercialObject", idCommercialObject);
        Query queryForFindOrders = sessionFactory.getCurrentSession().createQuery("from Order o where o.shift.id in (:idShifts)");
        queryForFindOrders.setParameter("idShifts", query.list());
        return queryForFindOrders.list();
    }

    @Transactional
    public Order returnOrderById(int orderId) {
        return sessionFactory.getCurrentSession().get(Order.class, orderId);
    }
}
