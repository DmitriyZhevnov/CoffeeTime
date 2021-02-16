package ru.zhevnov.coffeeTime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zhevnov.coffeeTime.dao.ICommercialObjectDao;
import ru.zhevnov.coffeeTime.entity.CommercialObject;
import ru.zhevnov.coffeeTime.entity.CommercialObjectQuantityOfItems;

import java.util.List;

@Service
public class CommercialObjectService implements ICommercialObjectService {

    @Autowired
    private ICommercialObjectDao commercialObjectDao;

    @Override
    public List<CommercialObject> returnAllCommercialObjects() {
        return commercialObjectDao.returnAllCommercialObjects();
    }

    @Override
    public CommercialObject returnCommercialObjectById(int commercialObjectId) {
        return commercialObjectDao.returnCommercialObjectById(commercialObjectId);
    }

    @Override
    public void submitItemsFromCommercialObjectsStorage(int idEmployee) {
        commercialObjectDao.submitItemsFromCommercialObjectsStorage(idEmployee);
    }

    @Override
    public void addItemsFromOrderInCommercialObjectsStorage(int idOrder) {
        commercialObjectDao.addItemsFromOrderInCommercialObjectsStorage(idOrder);
    }

    @Override
    public void addItemInCommercialObjectWarehouse(int idCommercialObject, int idItem) {
        commercialObjectDao.addItemInCommercialObjectWarehouse(idCommercialObject, idItem);
    }

}
