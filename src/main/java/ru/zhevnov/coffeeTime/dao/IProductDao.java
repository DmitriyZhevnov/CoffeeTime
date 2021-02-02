package ru.zhevnov.coffeeTime.dao;


import ru.zhevnov.coffeeTime.entity.Product;

import java.util.List;

public interface IProductDao {
    List<Product> returnAllProducts();
    Product returnProductById(int id);
}
