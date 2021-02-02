package ru.zhevnov.coffeeTime.service;

import ru.zhevnov.coffeeTime.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> returnAllProducts();
    Product returnProductById(int id);
}
