package ru.zhevnov.coffeeTime.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.zhevnov.coffeeTime.entity.Category;
import ru.zhevnov.coffeeTime.entity.Composition;
import ru.zhevnov.coffeeTime.entity.Item;
import ru.zhevnov.coffeeTime.entity.Product;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ProductDao implements IProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public List<Product> returnAllProducts(){
        return sessionFactory.getCurrentSession().createQuery("from Product").list();
    }

    @Transactional
    public Product returnProductById(int id){
       return sessionFactory.getCurrentSession().get(Product.class, id);
    }

    @Transactional
    public void updateProductWithNewData(int productId, String productName, double productPrice, int categoryId){
        Product product = sessionFactory.getCurrentSession().get(Product.class, productId);
        Category category = sessionFactory.getCurrentSession().get(Category.class, categoryId);
        product.setCategory(category);
        product.setPrice(productPrice);
        product.setName(productName);
    }

    @Transactional
    public void updateCompositionOfProduct(int compositionId, double quantityOfItem){
        Composition composition = sessionFactory.getCurrentSession().get(Composition.class, compositionId);
        composition.setQuantity(quantityOfItem);
        sessionFactory.getCurrentSession().update(composition);
    }

    @Transactional
    public void addItemToProduct(int idProduct, int idItem){
        Product product = sessionFactory.getCurrentSession().get(Product.class, idProduct);
        Composition composition = new Composition();
        composition.setItem(sessionFactory.getCurrentSession().get(Item.class, idItem));
        product.getComposition().add(composition);
        sessionFactory.getCurrentSession().save(composition);
        sessionFactory.getCurrentSession().update(product);
    }

    @Transactional
    public void removeItemFromProduct(int idProduct, int idComposition) {
        Product product = sessionFactory.getCurrentSession().get(Product.class, idProduct);
        Composition composition = sessionFactory.getCurrentSession().get(Composition.class, idComposition);
        product.getComposition().remove(composition);
        sessionFactory.getCurrentSession().update(product);
        sessionFactory.getCurrentSession().delete(composition);
    }

    @Transactional
    public Product returnNewProduct(){
        Product product = new Product();
        sessionFactory.getCurrentSession().save(product);
        return product;
    }
}
