package ru.zhevnov.coffeeTime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zhevnov.coffeeTime.dao.IClientDao;
import ru.zhevnov.coffeeTime.entity.Client;

import java.io.UnsupportedEncodingException;

@Service
public class ClientService implements IClientService {

    @Autowired
    private IClientDao clientDao;

    @Override
    public void registerNewClient(String name, String phoneNumber) {
       ///////удалить try catch
        try {
            clientDao.registerNewClient(name, phoneNumber);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //////////////////
    }

    @Override
    public Client returnClientByPhoneNumber(String phoneNumber) {
        return clientDao.returnClientByPhoneNumber(phoneNumber);
    }

    @Override
    public void addOnePercentToDiscount(int idClient) {
        clientDao.addOnePercentToDiscount(idClient);
    }

}
