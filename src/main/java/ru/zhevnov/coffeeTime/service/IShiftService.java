package ru.zhevnov.coffeeTime.service;

import ru.zhevnov.coffeeTime.entity.Employee;

public interface IShiftService {
    void checkOrOpenTheShift(Employee employee, int commercialObjectId);
    void closeShift(Employee employee);
}
