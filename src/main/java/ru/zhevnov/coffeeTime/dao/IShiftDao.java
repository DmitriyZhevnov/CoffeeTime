package ru.zhevnov.coffeeTime.dao;

import ru.zhevnov.coffeeTime.entity.Employee;
import ru.zhevnov.coffeeTime.entity.Shift;

public interface IShiftDao {
    void checkOrOpenTheShift(Employee employee, int commercialObjectId);
    void closeShift(Employee employee);
}
