package ru.zhevnov.coffeeTime.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zhevnov.coffeeTime.entity.CommercialObjectQuantityOfItems;
import ru.zhevnov.coffeeTime.entity.Item;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ItemDao implements IItemDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public List<Item> returnAllItems() {
        return sessionFactory.getCurrentSession().createQuery("from Item").list();
    }

    @Transactional
    public void addNewItem(String itemName, String itemMeasure, double itemQuantityInWarehouse){
        Item item = new Item();
        item.setName(itemName);
        item.setMeasure(itemMeasure);
        item.setQuantityInWarehouse(itemQuantityInWarehouse);
        sessionFactory.getCurrentSession().save(item);
    }

    @Transactional
    public Item returnItemById(int idItem){
        return sessionFactory.getCurrentSession().get(Item.class, idItem);
    }

    @Transactional
    public void updateItem(int idItem, String nameItem, String measureItem, double quantityOfItem){
        Item item = sessionFactory.getCurrentSession().get(Item.class, idItem);
        item.setQuantityInWarehouse(quantityOfItem);
        item.setMeasure(measureItem);
        item.setName(nameItem);
        sessionFactory.getCurrentSession().update(item);
    }

    @Transactional
    public CommercialObjectQuantityOfItems returnCommercialObjectItemById(int idItem){
        return sessionFactory.getCurrentSession().get(CommercialObjectQuantityOfItems.class, idItem);
    }
    @Transactional
    public void updateCommercialObjectsItem(int idItem, double quantityOfItem){
        CommercialObjectQuantityOfItems item = sessionFactory.getCurrentSession().get(CommercialObjectQuantityOfItems.class, idItem);
        item.setQuantity(quantityOfItem);
        sessionFactory.getCurrentSession().update(item);
    }
}
