package ru.zhevnov.coffeeTime.service;

public interface IOrderService {
    void saveNewOrder(int idEmployee, String phoneNumber, String paymentType);
}
