package ru.zhevnov.coffeeTime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zhevnov.coffeeTime.dao.IOrderDao;

import javax.transaction.Transactional;

@Service
public class OrderService implements IOrderService {

    @Autowired
    IOrderDao orderDao;

    @Transactional
    public void saveNewOrder(int idEmployee, String phoneNumber, String paymentType, String card, String cash) {
        orderDao.saveNewOrder(idEmployee, phoneNumber, paymentType, card, cash);
    }
}
