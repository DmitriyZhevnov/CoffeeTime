package ru.zhevnov.coffeeTime.dao;

import ru.zhevnov.coffeeTime.entity.BasketItem;
import ru.zhevnov.coffeeTime.entity.Employee;

import java.util.List;

public interface IBasketDao {
    public void addProductToBasket(int employeeId, int productId, int count);
    public List<BasketItem> returnListOfProductsInBasket(Employee employee);
    public void updateCount(Employee employee, int idProduct, int count);
    public void deleteItem(Employee employee, int idProduct);
    public String returnTotalCostOfTheOrder(Employee employee);
}
