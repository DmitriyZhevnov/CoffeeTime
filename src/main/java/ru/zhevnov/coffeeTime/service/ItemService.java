package ru.zhevnov.coffeeTime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zhevnov.coffeeTime.dao.IItemDao;
import ru.zhevnov.coffeeTime.entity.CommercialObjectQuantityOfItems;
import ru.zhevnov.coffeeTime.entity.Item;

import java.util.List;

@Service
public class ItemService implements IItemService {

    @Autowired
    private IItemDao itemDao;

    @Override
    public List<Item> returnAllItems() {
        return itemDao.returnAllItems();
    }

    @Override
    public void addNewItem(String itemName, String itemMeasure, double itemQuantityInWarehouse) {
        itemDao.addNewItem(itemName, itemMeasure, itemQuantityInWarehouse);
    }

    @Override
    public Item returnItemById(int idItem) {
        return itemDao.returnItemById(idItem);
    }

    @Override
    public void updateItem(int idItem, String nameItem, String measureItem, double quantityOfItem) {
        itemDao.updateItem(idItem, nameItem, measureItem, quantityOfItem);
    }

    @Override
    public CommercialObjectQuantityOfItems returnCommercialObjectItemById(int idItem) {
        return itemDao.returnCommercialObjectItemById(idItem);
    }

    @Override
    public void updateCommercialObjectsItem(int idItem, double quantityOfItem) {
        itemDao.updateCommercialObjectsItem(idItem, quantityOfItem);
    }
}
