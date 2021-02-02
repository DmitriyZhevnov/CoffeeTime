package ru.zhevnov.coffeeTime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zhevnov.coffeeTime.dao.IShiftDao;
import ru.zhevnov.coffeeTime.entity.Employee;

@Service
public class ShiftService implements IShiftService {

    @Autowired
    private IShiftDao shiftDao;

    @Override
    public void checkOrOpenTheShift(Employee employee, int commercialObjectId) {
        shiftDao.checkOrOpenTheShift(employee, commercialObjectId);
    }

    @Override
    public void closeShift(Employee employee) {
        shiftDao.closeShift(employee);
    }
}
