package ru.zhevnov.coffeeTime.dao;

import ru.zhevnov.coffeeTime.entity.Role;

public interface IRoleDao {
    Role returnUserValue();
    void addNewRole(String name);
}
