package ru.zhevnov.coffeeTime.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zhevnov.coffeeTime.entity.CommercialObject;
import ru.zhevnov.coffeeTime.entity.Employee;
import ru.zhevnov.coffeeTime.entity.Order;
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
        } else {
            Shift shift = sessionFactory.getCurrentSession().get(Shift.class, (int) list.get(0));
            if (shift.getCommercialObject().getId() != commercialObjectId) {
                shift.setDateClosed(new Date(System.currentTimeMillis()));
                shift.setTimeClosed(new Time(System.currentTimeMillis()));
                sessionFactory.getCurrentSession().update(shift);
                Shift shift2 = new Shift();
                shift2.setEmployee(sessionFactory.getCurrentSession().get(Employee.class, idEmployee));
                shift2.setDateOpened(date);
                shift2.setTimeOpened(time);
                shift2.setCommercialObject(commercialObjectService.returnCommercialObjectById(commercialObjectId));
                sessionFactory.getCurrentSession().save(shift2);
            }
        }
    }


    @Transactional
    public List makeReport(int idCommercialObject, Date fromDate, Date toDate) {
        DecimalFormat format = new DecimalFormat("##.00");
        DecimalFormatSymbols dfs = format.getDecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        format.setDecimalFormatSymbols(dfs);
        List list = new ArrayList();
        String sql = "select sum(card_amount + cash_amount) as total, sum(card_amount) as card, sum(cash_amount) as cash," +
                " (select count(shift_id) from orders where date_order between '" + fromDate.toString() + "' and '" + toDate.toString() + "' and shift_id in" +
                " (select id from shift where date_opened between '" + fromDate.toString() + "' and '" + toDate.toString() + "' and commercial_object_id = '" + idCommercialObject + "') and (cash_amount != '0' or card_amount !='0')) as countOrder," +
                " (select count(shift_id) from orders where date_order between '" + fromDate.toString() + "' and '" + toDate.toString() + "' and shift_id in (select id from shift where date_opened between '" + fromDate.toString() + "' and '" + toDate.toString() + "' and commercial_object_id = '" + idCommercialObject + "') and cash_amount = '0' and card_amount ='0') as countOrderCanceled" +
                " from orders where date_order between '" + fromDate.toString() + "' and '" + toDate.toString() + "' and shift_id in (select id from shift where date_opened between '" + fromDate.toString() + "' and '" + toDate.toString() + "' and commercial_object_id = '" + idCommercialObject + "');";
        List<Object[]> objList = sessionFactory.getCurrentSession().createSQLQuery(sql).list();
        for (Object[] objs : objList) {
            System.out.println(objs[0]);
            System.out.println(objs[1]);
            System.out.println(objs[2]);
            System.out.println(objs[3]);
            System.out.println(objs[4]);
            list.add(objs[0] == null ? 0.0 : format.format(Double.valueOf((Double) objs[0])));
            list.add(objs[1] == null ? 0.0 : format.format(Double.valueOf((Double) objs[1])));
            list.add(objs[2] == null ? 0.0 : format.format(Double.valueOf((Double) objs[2])));
            list.add(objs[3].toString());
            list.add(objs[4].toString());
        }
        System.out.println(list);
        return list;
    }

    @Transactional
    public List<Shift> returnAllShifts() {
        return sessionFactory.getCurrentSession().createQuery("from Shift order by (dateOpened) desc ").list();
    }

    @Transactional
    public List<Shift> returnShiftsByEmployeeIdAndDate(int idEmployee, Date fromDate, Date toDate) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("from Shift where employee.id = :idEmployee and dateOpened between :fromDate and :toDate");
        query.setParameter("idEmployee", idEmployee);
        query.setParameter("fromDate", fromDate);
        query.setParameter("toDate", toDate);
        return query.list();
    }

    @Transactional
    public String returnTotalTimeOfShiftsByEmployeeIdAndDate(int idEmployee, Date fromDate, Date toDate) {
        List<Shift> shiftList = returnShiftsByEmployeeIdAndDate(idEmployee, fromDate, toDate);
        long totalTime = 0;
        for (Shift shift : shiftList) {
            if (shift.getTimeClosed() == null) {
                continue;
            }
            totalTime += Math.abs(shift.getTimeClosed().getTime() - shift.getTimeOpened().getTime());
        }
        double hours = Double.parseDouble(String.valueOf(totalTime)) / 3600000;
        return String.format("%.1f", hours);
    }

    @Transactional
    public Shift returnShiftById(int idShift) {
        return sessionFactory.getCurrentSession().get(Shift.class, idShift);
    }

    @Transactional
    public void updateShiftWithNewData(int idShift, int idCommercialObject, int idEmployee, Date dateOpened, Time timeOpened, Date dateClosed,  Time timeClosed) {
       Shift shift = sessionFactory.getCurrentSession().get(Shift.class, idShift);
       shift.setEmployee(sessionFactory.getCurrentSession().get(Employee.class, idEmployee));
       shift.setCommercialObject(sessionFactory.getCurrentSession().get(CommercialObject.class, idCommercialObject));
       shift.setDateOpened(dateOpened);
       shift.setDateClosed(dateClosed);
       shift.setTimeOpened(timeOpened);
       shift.setTimeClosed(timeClosed);
    }
}
