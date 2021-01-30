package ru.zhevnov.coffeeTime.dao;


import ru.zhevnov.coffeeTime.entity.Product;

import java.util.List;

public interface IProductDao {
    public List<Product> returnAllProducts();
    public Product returnProductById(int id);
}
