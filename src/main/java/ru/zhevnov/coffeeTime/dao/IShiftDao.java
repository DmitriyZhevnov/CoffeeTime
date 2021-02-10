package ru.zhevnov.coffeeTime.dao;

import ru.zhevnov.coffeeTime.entity.Employee;
import ru.zhevnov.coffeeTime.entity.Shift;

import java.util.List;

public interface IShiftDao {
    void checkOrOpenTheShift(int idEmployee, int commercialObjectId);
    void closeShift(int idEmployee);
    Shift returnOpenedShiftByEmployeeId(int idEmployee);
    List makeReport(int idEmployee);
}
