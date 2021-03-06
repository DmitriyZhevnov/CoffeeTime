package ru.zhevnov.coffeeTime.dao;

import ru.zhevnov.coffeeTime.entity.Employee;
import ru.zhevnov.coffeeTime.entity.Shift;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface IShiftDao {
    void checkOrOpenTheShift(int idEmployee, int commercialObjectId);
    void closeShift(int idEmployee);
    Shift returnOpenedShiftByEmployeeId(int idEmployee);
    List makeReport(int idCommercialObject, Date fromDate, Date toDate);
    List<Shift> returnAllShifts();
    List<Shift> returnShiftsByEmployeeIdAndDate(int idEmployee, Date fromDate, Date toDate);
    String returnTotalTimeOfShiftsByEmployeeIdAndDate(int idEmployee, Date fromDate, Date toDate);
    Shift returnShiftById(int idShift);
    void updateShiftWithNewData(int idShift, int idCommercialObject, int idEmployee, Date dateOpened, Time timeOpened, Date dateClosed,  Time timeClosed);
}
