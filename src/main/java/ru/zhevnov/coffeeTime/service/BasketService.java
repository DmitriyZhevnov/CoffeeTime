package ru.zhevnov.coffeeTime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zhevnov.coffeeTime.dao.IBasketDao;
import ru.zhevnov.coffeeTime.entity.BasketItem;
import ru.zhevnov.coffeeTime.entity.Employee;

import java.util.List;

@Service
public class BasketService implements IBasketService{

    @Autowired
    private IBasketDao basketDao;

    @Override
    public void addProductToBasket(int personId, int productId, int count) {
        basketDao.addProductToBasket(personId, productId, count);
    }

    @Override
    public List<BasketItem> returnListOfProductsInBasket(int idEmployee) {
        return basketDao.returnListOfProductsInBasket(idEmployee);
    }

    @Override
    public void updateCount(int idEmployee, int idProduct, int count) {
        basketDao.updateCount(idEmployee, idProduct, count);
    }

    @Override
    public void deleteItem(int idEmployee, int idProduct) {
        basketDao.deleteItem(idEmployee, idProduct);
    }

    @Override
    public String returnTotalCostOfTheOrder(int idEmployee, String phoneNumber) {
        return basketDao.returnTotalCostOfTheOrder(idEmployee, phoneNumber);
    }

    @Override
    public void cleanBasket(int idEmployee) {
        basketDao.cleanBasket(idEmployee);
    }
}
