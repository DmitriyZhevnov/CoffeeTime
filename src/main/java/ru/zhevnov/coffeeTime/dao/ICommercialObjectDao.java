package ru.zhevnov.coffeeTime.dao;

import ru.zhevnov.coffeeTime.entity.CommercialObject;

import java.util.List;

public interface ICommercialObjectDao {
    List<CommercialObject> returnListOdCommercialObjects();
    CommercialObject returnCommercialObjectById(int commercialObjectId);
    void submitItemsFromCommercialObjectsStorage(int idEmployee);
}
