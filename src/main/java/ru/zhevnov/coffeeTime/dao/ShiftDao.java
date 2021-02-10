package ru.zhevnov.coffeeTime.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.zhevnov.coffeeTime.entity.Employee;
import ru.zhevnov.coffeeTime.entity.Shift;
import ru.zhevnov.coffeeTime.service.ICommercialObjectService;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ShiftDao implements IShiftDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private ICommercialObjectService commercialObjectService;

    @Transactional
    public Shift returnOpenedShiftByEmployeeId(int idEmployee) {
        Date date = new Date(System.currentTimeMillis());
        Query query = sessionFactory.getCurrentSession()
                .createQuery("select id from Shift where employee.id = :idEmployee and dateOpened = :dateOpened and dateClosed = null");
        query.setParameter("dateOpened", date);
        query.setParameter("idEmployee", idEmployee);
        List list = query.list();
        Shift shift = sessionFactory.getCurrentSession().get(Shift.class, Integer.parseInt(list.get(0).toString()));
        return shift;
    }

    @Transactional
    public void closeShift(int idEmployee) {
        Date date = new Date(System.currentTimeMillis());
        Time time = new Time(System.currentTimeMillis());
        Query query = sessionFactory.getCurrentSession().createQuery("select id from Shift where" +
                " employee.id = :idEmployee and dateOpened = :dateOpened and dateClosed = null");
        query.setParameter("dateOpened", date);
        query.setParameter("idEmployee", idEmployee);
        List list = query.list();
        if (!list.isEmpty()) {
            Shift shift = sessionFactory.getCurrentSession().get(Shift.class, Integer.parseInt(list.get(0).toString()));
            shift.setDateClosed(date);
            shift.setTimeClosed(time);
        }
    }

    @Transactional
    public void checkOrOpenTheShift(int idEmployee, int commercialObjectId) {
        Time time = new Time(System.currentTimeMillis());
        Date date = new Date(System.currentTimeMillis());
        Query query = sessionFactory.getCurrentSession().createQuery("select id from Shift where" +
                " employee.id = :idEmployee and dateOpened = :dateOpened and dateClosed = null");
        query.setParameter("dateOpened", date);
        query.setParameter("idEmployee", idEmployee);
        List list = query.list();
        if (list.isEmpty()) {
            Shift shift = new Shift();
            shift.setEmployee(sessionFactory.getCurrentSession().get(Employee.class, idEmployee));
            shift.setDateOpened(date);
            shift.setTimeOpened(time);
            shift.setCommercialObject(commercialObjectService.returnCommercialObjectById(commercialObjectId));
            sessionFactory.getCurrentSession().save(shift);
        }
    }


    @Transactional
    public List makeReport(int idEmployee) {
        DecimalFormat format = new DecimalFormat("##.00");
        DecimalFormatSymbols dfs = format.getDecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        format.setDecimalFormatSymbols(dfs);
        Date date = new Date(System.currentTimeMillis());
        List list = new ArrayList();
        String sql = "select sum(card_amount + cash_amount) as total, sum(card_amount) as card, sum(cash_amount) as cash,\n" +
                "       (select count(employee_id) from orders where date_order = '"+ date +"' and employee_id = '" + idEmployee + "' and (cash_amount != '0' or card_amount !='0')) as countOrder,\n" +
                "       (select count(employee_id) from orders where date_order = '"+ date +"' and employee_id = '" + idEmployee + "' and cash_amount = '0' and card_amount ='0') as countOrderCanceled\n" +
                "from orders where date_order = '"+ date +"' and employee_id = '" + idEmployee + "';";
        List<Object[]> objList = sessionFactory.getCurrentSession().createSQLQuery(sql).list();
        for(Object[] objs : objList){
            list.add(format.format(Double.valueOf((Double) objs[0])));
            list.add(format.format(Double.valueOf((Double) objs[1])));
            list.add(format.format(Double.valueOf((Double) objs[2])));
            list.add(objs[3].toString());
            list.add(objs[4].toString());
        }
        return list;
    }
}
