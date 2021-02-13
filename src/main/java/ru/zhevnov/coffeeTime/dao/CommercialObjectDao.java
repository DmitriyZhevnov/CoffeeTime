package ru.zhevnov.coffeeTime.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.zhevnov.coffeeTime.entity.*;
import ru.zhevnov.coffeeTime.service.IShiftService;

import javax.persistence.Query;
import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CommercialObjectDao implements ICommercialObjectDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private /*IShiftService shiftService;*/ IShiftDao shiftService;

    @Transactional
    public List<CommercialObject> returnAllCommercialObjects() {
        return sessionFactory.getCurrentSession().createQuery("from CommercialObject").list();
    }

    @Transactional
    public CommercialObject returnCommercialObjectById(int commercialObjectId) {
        return sessionFactory.getCurrentSession().get(CommercialObject.class, commercialObjectId);
    }

    @Transactional
    public void addItemsFromOrderInCommercialObjectsStorage(int idOrder) {
        Order order = sessionFactory.getCurrentSession().get(Order.class, idOrder);
        CommercialObject commercialObject =  shiftService.returnOpenedShiftByEmployeeId(order.getEmployee().getId()).getCommercialObject();
        for (OrderItem orderItem : order.getOrderItems()) {
            int countOfItemsInOrder = orderItem.getQuantity();
            Product product = orderItem.getProducts().get(0);
            List<Composition> compositions = product.getComposition();
            for (Composition composition : compositions) {
                Item item = composition.getItem();
                double quantityItemInComposition = composition.getQuantity();
                CommercialObjectQuantityOfItems commercialObjectQuantityOfItems = item.getCommercialObjectQuantityOfItems()
                        .stream().filter(s -> s.getCommercialObject().getId() == commercialObject.getId()).collect(Collectors.toList()).get(0);
                double quantityItemInCommercialObject = commercialObjectQuantityOfItems.getQuantity();
                commercialObjectQuantityOfItems.setQuantity(quantityItemInCommercialObject + (quantityItemInComposition * countOfItemsInOrder));
                sessionFactory.getCurrentSession().update(commercialObjectQuantityOfItems);
            }
        }
    }

    @Transactional
    public void submitItemsFromCommercialObjectsStorage(int idEmployee) {
        Employee employee = sessionFactory.getCurrentSession().get(Employee.class, idEmployee);
        Shift shift = shiftService.returnOpenedShiftByEmployeeId(employee.getId());
        CommercialObject cObject = shift.getCommercialObject();
        for (BasketItem basketItem : employee.getBasket().getBasketItems()) {
            int countOfItemsInBasket = basketItem.getQuantity();
            Product product = basketItem.getProducts().get(0);
            List<Composition> compositions = product.getComposition();
            for (Composition composition : compositions) {
                Item item = composition.getItem();
                double quantityItemInComposition = composition.getQuantity();
                CommercialObjectQuantityOfItems commercialObjectQuantityOfItems = item.getCommercialObjectQuantityOfItems().stream().filter(s -> s.getCommercialObject().getId() == cObject.getId()).collect(Collectors.toList()).get(0);
                double quantityItemInCommercialObject = commercialObjectQuantityOfItems.getQuantity();
                commercialObjectQuantityOfItems.setQuantity(quantityItemInCommercialObject - (quantityItemInComposition * countOfItemsInBasket));
                sessionFactory.getCurrentSession().update(commercialObjectQuantityOfItems);
            }
        }
    }
}
