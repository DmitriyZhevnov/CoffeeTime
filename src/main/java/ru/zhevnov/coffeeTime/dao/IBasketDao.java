package ru.zhevnov.coffeeTime.dao;

import ru.zhevnov.coffeeTime.entity.BasketItem;
import ru.zhevnov.coffeeTime.entity.Employee;

import java.util.List;

public interface IBasketDao {
    void addProductToBasket(int employeeId, int productId, int count);
    List<BasketItem> returnListOfProductsInBasket(Employee employee);
    void updateCount(Employee employee, int idProduct, int count);
    void deleteItem(Employee employee, int idProduct);
    String returnTotalCostOfTheOrder(Employee employee, String phoneNumber);
}
