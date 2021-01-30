package ru.zhevnov.coffeeTime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zhevnov.coffeeTime.dao.IEmployeeDao;
import ru.zhevnov.coffeeTime.entity.Employee;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private IEmployeeDao employeeDao;

    @Override
    public void setPhoneNumberAndAddress(Employee employee, String phoneNumber, String address) {
        employeeDao.setPhoneNumberAndAddress(employee, phoneNumber, address);
    }

    @Override
    public boolean checkLoginForExist(String login) {
        return employeeDao.checkLoginForExist(login);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeDao.updateEmployee(employee);
    }

    @Override
    public void registerNewEmployee(String name, String login, String password) {
        employeeDao.registerNewEmployee(name, login, password);
    }

    @Override
    public List<Employee> returnAllEmployees() {
        return employeeDao.returnAllEmployees();
    }

    @Override
    public Employee checkAndReturnEmployeeByLoginAndPassword(String login, String password) {
        return employeeDao.checkAndReturnEmployeeByLoginAndPassword(login, password);
    }
}
