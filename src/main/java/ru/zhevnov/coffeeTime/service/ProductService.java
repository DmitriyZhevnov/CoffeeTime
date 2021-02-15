package ru.zhevnov.coffeeTime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zhevnov.coffeeTime.dao.IProductDao;
import ru.zhevnov.coffeeTime.entity.Product;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> returnAllProducts() {
        return productDao.returnAllProducts();
    }

    @Override
    public Product returnProductById(int id) {
        return productDao.returnProductById(id);
    }

    @Override
    public void updateProductWithNewData(int productId, String productName, double productPrice, int categoryId) {
        productDao.updateProductWithNewData(productId, productName, productPrice, categoryId);
    }

    @Override
    public void updateCompositionOfProduct(int compositionId, double quantityOfItem) {
        productDao.updateCompositionOfProduct(compositionId, quantityOfItem);
    }

    @Override
    public void addItemToProduct(int idProduct, int idItem) {
        productDao.addItemToProduct(idProduct, idItem);
    }

    @Override
    public void removeItemFromProduct(int idProduct, int idComposition) {
        productDao.removeItemFromProduct(idProduct, idComposition);
    }

    @Override
    public Product returnNewProduct() {
        return productDao.returnNewProduct();
    }
}
