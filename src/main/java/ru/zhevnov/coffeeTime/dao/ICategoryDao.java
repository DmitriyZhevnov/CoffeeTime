package ru.zhevnov.coffeeTime.dao;

import ru.zhevnov.coffeeTime.entity.Product;

import javax.transaction.Transactional;
import java.util.List;

public interface ICategoryDao {
    List<Product> returnAllCoffees();
    List<Product> returnAllDrinks();
    List<Product> returnAllAdditions();
    List<Product> returnAllBars();
}
