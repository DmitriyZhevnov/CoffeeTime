package ru.zhevnov.coffeeTime.service;

import ru.zhevnov.coffeeTime.entity.Employee;

import java.util.List;

public interface IEmployeeService {
    public void setPhoneNumberAndAddress(Employee employee, String phoneNumber, String address);
    public boolean checkLoginForExist(String login);
    public void updateEmployee(Employee employee);
    public void registerNewEmployee(String name, String login, String password);
    public List<Employee> returnAllEmployees();
    public Employee checkAndReturnEmployeeByLoginAndPassword(String login, String password);
}