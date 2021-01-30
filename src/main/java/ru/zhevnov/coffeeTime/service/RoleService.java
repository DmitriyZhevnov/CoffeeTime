package ru.zhevnov.coffeeTime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zhevnov.coffeeTime.dao.IRoleDao;
import ru.zhevnov.coffeeTime.entity.Role;

@Service
public class RoleService implements IRoleService{

    @Autowired
    private IRoleDao roleDao;

    @Override
    public Role returnUserValue() {
        return roleDao.returnUserValue();
    }

    @Override
    public void addNewRole(String name) {
        roleDao.addNewRole(name);
    }
}
