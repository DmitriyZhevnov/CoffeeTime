package ru.zhevnov.coffeeTime.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "shift")
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "commercial_object_id")
    private CommercialObject commercialObject;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @Column(name = "date_time_opened")
    private LocalDateTime dateTimeOpened;
    @Column(name = "date_time_closed")
    private LocalDateTime dateTimeClosed;

    public Shift() {
    }

    public Shift(CommercialObject commercialObject, Employee employee, LocalDateTime dateTimeOpened, LocalDateTime dateTimeClosed) {
        this.commercialObject = commercialObject;
        this.employee = employee;
        this.dateTimeOpened = dateTimeOpened;
        this.dateTimeClosed = dateTimeClosed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CommercialObject getCommercialObject() {
        return commercialObject;
    }

    public void setCommercialObject(CommercialObject commercialObject) {
        this.commercialObject = commercialObject;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDateTime getDateTimeOpened() {
        return dateTimeOpened;
    }

    public void setDateTimeOpened(LocalDateTime dateTimeOpened) {
        this.dateTimeOpened = dateTimeOpened;
    }

    public LocalDateTime getDateTimeClosed() {
        return dateTimeClosed;
    }

    public void setDateTimeClosed(LocalDateTime dateTimeClosed) {
        this.dateTimeClosed = dateTimeClosed;
    }

    @Override
    public String toString() {
        return "Shift{" +
                "id=" + id +
                ", commercialObject=" + commercialObject +
                ", employee=" + employee +
                ", dateTimeOpened=" + dateTimeOpened +
                ", dateTimeClosed=" + dateTimeClosed +
                '}';
    }
}
