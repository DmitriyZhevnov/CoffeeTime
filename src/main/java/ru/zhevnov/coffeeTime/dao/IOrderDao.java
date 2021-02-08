package ru.zhevnov.coffeeTime.dao;

import ru.zhevnov.coffeeTime.entity.Employee;

public interface IOrderDao {
    void saveNewOrder(int idEmployee, String phoneNumber, String paymentType);
}
