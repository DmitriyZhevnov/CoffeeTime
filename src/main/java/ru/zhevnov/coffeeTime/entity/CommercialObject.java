package ru.zhevnov.coffeeTime.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "commercial_object")
public class CommercialObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String address;

    @OneToMany(mappedBy = "commercialObject", fetch = FetchType.LAZY)
    private Set<Shift> shifts = new HashSet<>();
    @OneToMany(mappedBy = "commercialObject", fetch = FetchType.LAZY)
    private Set<CommercialObjectQuantityOfItems> commercialObjectQuantityOfItems = new HashSet<>();

    public CommercialObject() {
    }

    public CommercialObject(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(Set<Shift> shifts) {
        this.shifts = shifts;
    }

    public Set<CommercialObjectQuantityOfItems> getCommercialObjectQuantityOfItems() {
        return commercialObjectQuantityOfItems;
    }

    public void setCommercialObjectQuantityOfItems(Set<CommercialObjectQuantityOfItems> commercialObjectQuantityOfItems) {
        this.commercialObjectQuantityOfItems = commercialObjectQuantityOfItems;
    }

    @Override
    public String toString() {
        return "CommercialObject{" +
                "id=" + id +
                ", address='" + address + '\'' +
                '}';
    }
}
