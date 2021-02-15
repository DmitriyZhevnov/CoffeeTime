package ru.zhevnov.coffeeTime.service;

import ru.zhevnov.coffeeTime.entity.CommercialObject;
import ru.zhevnov.coffeeTime.entity.CommercialObjectQuantityOfItems;

import java.util.List;

public interface ICommercialObjectService {
    List<CommercialObject> returnAllCommercialObjects();
    CommercialObject returnCommercialObjectById(int commercialObjectId);
    void submitItemsFromCommercialObjectsStorage(int idEmployee);
    void addItemsFromOrderInCommercialObjectsStorage(int idOrder);
    List<CommercialObjectQuantityOfItems> returnItemsInCommercialObject(int idCommercialObject);
}
