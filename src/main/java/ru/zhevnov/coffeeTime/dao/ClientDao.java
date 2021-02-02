package ru.zhevnov.coffeeTime.dao;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class ClientDao implements IClientDao {

    @Transactional
    public void registerNewClient(String name, String phoneNumber) {

    }
}
