package ru.zhevnov.coffeeTime.service;

import ru.zhevnov.coffeeTime.entity.Role;

public interface IRoleService {
    public Role returnUserValue();
    public void addNewRole(String name);
}
