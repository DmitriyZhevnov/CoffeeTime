package ru.zhevnov.coffeeTime.dao;

import ru.zhevnov.coffeeTime.entity.Employee;
import ru.zhevnov.coffeeTime.entity.Order;
import ru.zhevnov.coffeeTime.entity.OrderItem;
import ru.zhevnov.coffeeTime.entity.Product;

import java.sql.Date;
import java.util.List;

public interface IOrderDao {
    void saveNewOrder(int idEmployee, String phoneNumber, String paymentType, String card, String cash);
    List<Order> returnAllOrdersByEmployeeId(int employeeId);
    Order returnOrderById(int orderId);
    void cancelOrder(int idOrder, String reason, String type);
    void changePaymentType(int idOrder, String type, String cash, String card, String reason);
    List<Order> returnAllOrdersByCommercialObjectAndDate(int idCommercialObject, Date date);
}
