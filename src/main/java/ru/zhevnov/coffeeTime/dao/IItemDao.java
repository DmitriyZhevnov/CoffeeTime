package ru.zhevnov.coffeeTime.dao;

import ru.zhevnov.coffeeTime.entity.CommercialObjectQuantityOfItems;
import ru.zhevnov.coffeeTime.entity.Item;

import java.util.List;

public interface IItemDao {
    List<Item> returnAllItems();
    void addNewItem(String itemName, String itemMeasure, double itemQuantityInWarehouse);
    Item returnItemById(int idItem);
    void updateItem(int idItem, String nameItem, String measureItem, double quantityOfItem);
    CommercialObjectQuantityOfItems returnCommercialObjectItemById(int idItem);
    void updateCommercialObjectsItem(int idItem, double quantityOfItem);
}
