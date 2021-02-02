package ru.zhevnov.coffeeTime.dao;


import ru.zhevnov.coffeeTime.entity.Employee;

import java.util.List;

public interface IEmployeeDao {
    void setPhoneNumberAndAddress(Employee employee, String phoneNumber, String address);
    boolean checkLoginForExist(String login);
    void updateEmployee(Employee employee);
    void registerNewEmployee(String name, String login, String password);
    List<Employee> returnAllEmployees();
    Employee checkAndReturnEmployeeByLoginAndPassword(String login, String password);
}
