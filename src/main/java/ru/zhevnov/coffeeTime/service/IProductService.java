package ru.zhevnov.coffeeTime.service;

import ru.zhevnov.coffeeTime.entity.Product;

import java.util.List;

public interface IProductService {
    public List<Product> returnAllProducts();
    public Product returnProductById(int id);
}
