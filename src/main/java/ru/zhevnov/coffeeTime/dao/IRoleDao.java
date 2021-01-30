package ru.zhevnov.coffeeTime.dao;

import ru.zhevnov.coffeeTime.entity.Role;

public interface IRoleDao {
    public Role returnUserValue();

    public void addNewRole(String name);
}
