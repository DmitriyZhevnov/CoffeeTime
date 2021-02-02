package ru.zhevnov.coffeeTime.service;


import ru.zhevnov.coffeeTime.entity.BasketItem;
import ru.zhevnov.coffeeTime.entity.Employee;

import java.util.List;

public interface IBasketService {
    void addProductToBasket(int personId, int productId, int count);
    List<BasketItem> returnListOfProductsInBasket(Employee employee);
    void updateCount(Employee employee, int idProduct, int count);
    void deleteItem(Employee employee, int idProduct);
    String returnTotalCostOfTheOrder(Employee employee, String phoneNumber);
}
