package ru.zhevnov.coffeeTime.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.zhevnov.coffeeTime.entity.Employee;
import ru.zhevnov.coffeeTime.entity.Shift;
import ru.zhevnov.coffeeTime.service.IRoleService;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ShiftDao implements IShiftDao {

    @Autowired
    private SessionFactory sessionFactory;

    ////Изменить на сервис!!!!
    @Autowired
    private ICommercialObjectDao commercialObjectDao;
    /////////////


    @Transactional
    public void closeShift(Employee employee){
        Date date = new Date(System.currentTimeMillis());
        Time time = new Time(System.currentTimeMillis());
        Query query = sessionFactory.getCurrentSession().createQuery("select id from Shift where" +
                " employee.id = :idEmployee and dateOpened = :dateOpened and dateClosed = null");
        query.setParameter("dateOpened", date);
        query.setParameter("idEmployee", employee.getId());
        List list = query.list();
        if (!list.isEmpty()) {
            Shift shift = sessionFactory.getCurrentSession().get(Shift.class, Integer.parseInt(list.get(0).toString()));
            shift.setDateClosed(date);
            shift.setTimeClosed(time);
        }
    }

    @Transactional
    public void checkOrOpenTheShift(Employee employee, int commercialObjectId) {
        Time time = new Time(System.currentTimeMillis());
        Date date = new Date(System.currentTimeMillis());
        Query query = sessionFactory.getCurrentSession().createQuery("select id from Shift where" +
                " employee.id = :idEmployee and dateOpened = :dateOpened and dateClosed = null");
        query.setParameter("dateOpened", date);
        query.setParameter("idEmployee", employee.getId());
        List list = query.list();
        if (list.isEmpty()) {
            Shift shift = new Shift();
            shift.setEmployee(employee);
            shift.setDateOpened(date);
            shift.setTimeOpened(time);
            shift.setCommercialObject(commercialObjectDao.returnCommercialObjectById(commercialObjectId));
            sessionFactory.getCurrentSession().save(shift);
            //добавить объект
        }
    }

}
