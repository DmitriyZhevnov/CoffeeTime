package ru.zhevnov.coffeeTime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zhevnov.coffeeTime.dao.ICategoryDao;
import ru.zhevnov.coffeeTime.entity.Category;
import ru.zhevnov.coffeeTime.entity.Product;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryDao categoryDao;

    @Override
    public List<Product> returnAllCoffees() {
        return categoryDao.returnAllCoffees();
    }

    @Override
    public List<Product> returnAllDrinks() {
        return categoryDao.returnAllDrinks();
    }

    @Override
    public List<Product> returnAllAdditions() {
        return categoryDao.returnAllAdditions();
    }

    @Override
    public List<Product> returnAllBars() {
        return categoryDao.returnAllBars();
    }

    @Override
    public List<Category> returnAllCategories() {
        return categoryDao.returnAllCategories();
    }
}
