package ru.zhevnov.coffeeTime.service;

import ru.zhevnov.coffeeTime.entity.Employee;
import ru.zhevnov.coffeeTime.entity.Shift;

public interface IShiftService {
    void checkOrOpenTheShift(int idEmployee, int commercialObjectId);
    void closeShift(int idEmployee);
    Shift returnOpenedShiftByEmployeeId(int idEmployee);
}
