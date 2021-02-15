package ru.zhevnov.coffeeTime.dao;


import ru.zhevnov.coffeeTime.entity.Product;

import java.util.List;

public interface IProductDao {
    List<Product> returnAllProducts();
    Product returnProductById(int id);
    void updateProductWithNewData(int productId, String productName, double productPrice, int categoryId);
    void updateCompositionOfProduct(int compositionId, double quantityOfItem);
    void addItemToProduct(int idProduct, int idItem);
    void removeItemFromProduct(int idProduct, int idComposition);
    Product returnNewProduct();
}
