package ru.zhevnov.coffeeTime.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.zhevnov.coffeeTime.entity.Basket;
import ru.zhevnov.coffeeTime.entity.Employee;
import ru.zhevnov.coffeeTime.service.IEmployeeService;
import ru.zhevnov.coffeeTime.service.IRoleService;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.util.List;

@Repository
public class EmployeeDao implements IEmployeeDao {

    private SessionFactory sessionFactory;
    private IRoleService roleService;

    @Autowired
    public EmployeeDao(SessionFactory sessionFactory, IRoleService roleService) {
        this.sessionFactory = sessionFactory;
        this.roleService = roleService;
    }
    public void setPhoneNumberAndAddress(Employee employee, String phoneNumber, String address) {
//        try(Session session = sessionFactory.openSession()){
//            session.beginTransaction();
//            Employee e =  session.get(Employee.class, employee.getId());
//            e.setPhoneNumber(phoneNumber);
//            e.setAddress(address);
//            session.update(p);
//            session.getTransaction().commit();
//        }
    }

    public boolean checkLoginForExist(String login) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("select id from Employee where login = :login");
            query.setParameter("login", login);
            List list = query.list();
            session.getTransaction().commit();
            if (list.isEmpty()) {
                return false;
            } else {
                return true;
            }
        }
    }

    public void updateEmployee(Employee employee) {
//        try (Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            session.update(employee);
//            session.getTransaction().commit();
//        }
    }

    @Transactional
    public void registerNewEmployee(String name, String login, String password) {
        Basket basket = new Basket();
        Employee employee = new Employee(name, login, password, roleService.returnUserValue());
        sessionFactory.getCurrentSession().save(employee);
        basket.setEmployee(employee);
        sessionFactory.getCurrentSession().save(basket);
    }

    public List<Employee> returnAllEmployees() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Employee ");
            List<Employee> listEmployees = query.list();
            session.getTransaction().commit();
            return listEmployees;
        }
    }

    @Transactional
    public Employee checkAndReturnEmployeeByLoginAndPassword(String login, String password) {
        Query query = sessionFactory.getCurrentSession().createQuery("select id from Employee where login = :login and password = :password");
        query.setParameter("login", login);
        query.setParameter("password", password);
        List list = query.list();
        if (list.isEmpty()) {
            return null;
        } else {
            Employee employee = sessionFactory.getCurrentSession().get(Employee.class, Integer.parseInt(list.get(0).toString()));
            return employee;
        }
    }
}
