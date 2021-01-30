package ru.zhevnov.coffeeTime.service;


import ru.zhevnov.coffeeTime.entity.BasketItem;
import ru.zhevnov.coffeeTime.entity.Employee;

import java.util.List;

public interface IBasketService {
    public void addProductToBasket(int personId, int productId, int count);
    public List<BasketItem> returnListOfProductsInBasket(Employee employee);
    public void updateCount(Employee employee, int idProduct, int count);
    public void deleteItem(Employee employee, int idProduct);
}
