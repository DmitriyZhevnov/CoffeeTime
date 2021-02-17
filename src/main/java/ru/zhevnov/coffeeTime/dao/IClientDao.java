package ru.zhevnov.coffeeTime.dao;

import ru.zhevnov.coffeeTime.entity.Client;
import ru.zhevnov.coffeeTime.entity.Employee;

import java.io.UnsupportedEncodingException;

public interface IClientDao {
    void registerNewClient(String name, String phoneNumber) throws UnsupportedEncodingException;
    Client returnClientByPhoneNumber(String phoneNumber);
    void addOnePercentToDiscount(int idClient);
}
