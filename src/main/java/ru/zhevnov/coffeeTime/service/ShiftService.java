package ru.zhevnov.coffeeTime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zhevnov.coffeeTime.dao.IShiftDao;
import ru.zhevnov.coffeeTime.entity.Employee;
import ru.zhevnov.coffeeTime.entity.Shift;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

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

    @Override
    public List<Shift> returnAllShifts() {
        return shiftDao.returnAllShifts();
    }

    @Override
    public List<Shift> returnShiftsByEmployeeIdAndDate(int idEmployee, Date fromDate, Date toDate) {
        return shiftDao.returnShiftsByEmployeeIdAndDate(idEmployee, fromDate, toDate);
    }

    @Override
    public String returnTotalTimeOfShiftsByEmployeeIdAndDate(int idEmployee, Date fromDate, Date toDate) {
        return shiftDao.returnTotalTimeOfShiftsByEmployeeIdAndDate(idEmployee, fromDate, toDate);
    }

    @Override
    public Shift returnShiftById(int idShift) {
        return shiftDao.returnShiftById(idShift);
    }

    @Override
    public void updateShiftWithNewData(int idShift, int idCommercialObject, int idEmployee, Date dateOpened, Time timeOpened, Date dateClosed, Time timeClosed) {
        shiftDao.updateShiftWithNewData(idShift, idCommercialObject, idEmployee, dateOpened, timeOpened, dateClosed, timeClosed);
    }
}
