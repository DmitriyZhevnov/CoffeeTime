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
    public List<BasketItem> returnListOfProductsInBasket(Employee employee) {
        return basketDao.returnListOfProductsInBasket(employee);
    }

    @Override
    public void updateCount(Employee employee1, int idProduct, int count) {
        basketDao.updateCount(employee1, idProduct, count);
    }

    @Override
    public void deleteItem(Employee employee, int idProduct) {
        basketDao.deleteItem(employee, idProduct);
    }
}
