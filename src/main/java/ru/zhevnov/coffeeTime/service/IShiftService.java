package ru.zhevnov.coffeeTime.service;

import ru.zhevnov.coffeeTime.entity.Employee;

public interface IShiftService {
    void checkOrOpenTheShift(int idEmployee, int commercialObjectId);
    void closeShift(int idEmployee);
}
