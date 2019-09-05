package com.marketplace.service;

import java.util.List;

import com.marketplace.entity.Category;
import com.marketplace.entity.Product;

public interface CategoryService {
    List<Category> getCategories();
    Category getCategory(int id);
    void deleteCategory(int id);
    void saveCategory(Category p);

    List<Product> getProducts();
    List<Object> getProducts(String columns);
    Product getProduct(int id);
    Object getProduct(int id, String columns);
    void deleteProduct(int id);
    void saveProduct(Product r);
}
