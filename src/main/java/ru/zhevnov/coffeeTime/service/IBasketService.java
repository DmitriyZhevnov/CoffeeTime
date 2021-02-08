package ru.zhevnov.coffeeTime.service;


import ru.zhevnov.coffeeTime.entity.BasketItem;
import ru.zhevnov.coffeeTime.entity.Employee;

import java.util.List;

public interface IBasketService {
    void addProductToBasket(int personId, int productId, int count);
    List<BasketItem> returnListOfProductsInBasket(int idEmployee);
    void updateCount(int idEmployee, int idProduct, int count);
    void deleteItem(int idEmployee, int idProduct);
    String returnTotalCostOfTheOrder(int idEmployee, String phoneNumber);
    void cleanBasket(int idEmployee);
}
