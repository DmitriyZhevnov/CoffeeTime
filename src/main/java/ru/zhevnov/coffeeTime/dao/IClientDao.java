package ru.zhevnov.coffeeTime.dao;

import ru.zhevnov.coffeeTime.entity.Client;
import ru.zhevnov.coffeeTime.entity.Employee;

public interface IClientDao {
    void registerNewClient(String name, String phoneNumber);
    Client returnClientByPhoneNumber(String phoneNumber);
    void addOnePercentToDiscount(int idClient);
}
