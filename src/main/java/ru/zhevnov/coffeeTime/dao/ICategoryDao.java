package ru.zhevnov.coffeeTime.dao;

import ru.zhevnov.coffeeTime.entity.Product;

import java.util.List;

public interface ICategoryDao {
    List<Product> returnAllCoffees();
}
