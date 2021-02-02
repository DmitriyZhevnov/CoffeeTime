package ru.zhevnov.coffeeTime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zhevnov.coffeeTime.dao.ICommercialObjectDao;
import ru.zhevnov.coffeeTime.entity.CommercialObject;

import java.util.List;

@Service
public class CommercialObjectService implements ICommercialObjectService {

    @Autowired
    private ICommercialObjectDao commercialObjectDao;

    @Override
    public List<CommercialObject> returnListOdCommercialObjects() {
        return commercialObjectDao.returnListOdCommercialObjects();
    }

    @Override
    public CommercialObject returnCommercialObjectById(int commercialObjectId) {
        return commercialObjectDao.returnCommercialObjectById(commercialObjectId);
    }
}
