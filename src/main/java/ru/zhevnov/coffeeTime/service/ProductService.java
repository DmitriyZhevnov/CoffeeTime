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
}
