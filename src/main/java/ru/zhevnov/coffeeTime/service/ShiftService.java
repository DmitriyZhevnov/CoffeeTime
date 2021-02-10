package ru.zhevnov.coffeeTime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zhevnov.coffeeTime.dao.IShiftDao;
import ru.zhevnov.coffeeTime.entity.Employee;
import ru.zhevnov.coffeeTime.entity.Shift;

import javax.transaction.Transactional;

@Service
public class ShiftService implements IShiftService {

    @Autowired
    private IShiftDao shiftDao;

    @Transactional
    public void checkOrOpenTheShift(int idEmployee, int commercialObjectId) {
        shiftDao.checkOrOpenTheShift(idEmployee, commercialObjectId);
    }

    @Transactional
    public void closeShift(int idEmployee) {
        shiftDao.closeShift(idEmployee);
    }

    @Override
    public Shift returnOpenedShiftByEmployeeId(int idEmployee) {
        return shiftDao.returnOpenedShiftByEmployeeId(idEmployee);
    }
}
