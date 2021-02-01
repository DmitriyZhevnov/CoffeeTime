package ru.zhevnov.coffeeTime.dao;

import ru.zhevnov.coffeeTime.entity.CommercialObject;
import ru.zhevnov.coffeeTime.entity.Shift;

import java.util.List;

public interface ICommercialObjectDao {
    List<CommercialObject> returnListOdCommercialObjects();
    CommercialObject returnCommercialObjectById(int commercialObjectId);
}
