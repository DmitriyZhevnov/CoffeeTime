package ru.zhevnov.coffeeTime.dao;

import ru.zhevnov.coffeeTime.entity.BasketItem;
import ru.zhevnov.coffeeTime.entity.Employee;

import java.util.List;

public interface IBasketDao {
    void addProductToBasket(int employeeId, int productId);
    List<BasketItem> returnListOfProductsInBasket(int idEmployee);
    void updateCount(int idEmployee, int idProduct, int count);
    void deleteItem(int idEmployee, int idProduct);
    String returnTotalCostOfTheOrder(int idEmployee, String phoneNumber);
    void cleanBasket(int idEmployee);
    void submitProductInBasket(int employeeId, int productId);
}
