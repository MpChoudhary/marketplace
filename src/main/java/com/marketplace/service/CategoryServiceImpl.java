package com.marketplace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marketplace.dao.CategoryDAO;
import com.marketplace.dao.ProductDAO;
import com.marketplace.entity.Category;
import com.marketplace.entity.Product;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private ProductDAO productDAO;


    /**
     * Category
     */
    @Override
    @Transactional
    public List<Category> getCategories() {
        return categoryDAO.getCategories();
    }

    @Override
    @Transactional
    public Category getCategory(int id) {
        return categoryDAO.getCategory(id);
    }

    @Override
    @Transactional
    public void deleteCategory(int id) {
        categoryDAO.deleteCategory(id);
    }

    @Override
    @Transactional
    public void saveCategory(Category c) {
        categoryDAO.saveCategory(c);
    }

    /**
     * Product
     */

    @Override
    @Transactional
    public List<Product> getProducts() {
        return productDAO.getProducts();
    }

    @Override
    @Transactional
    public List<Object> getProducts(String columns) { return productDAO.getProducts(columns); }

    @Override
    @Transactional
    public Product getProduct(int id) {
        return productDAO.getProduct(id);
    }

    @Override
    @Transactional
    public Object getProduct(int id, String columns) { return productDAO.getProduct(id, columns); }

    @Override
    @Transactional
    public void deleteProduct(int id) {
        productDAO.deleteProduct(id);
    }

    @Override
    @Transactional
    public void saveProduct(Product p) {
        productDAO.saveProduct(p);
    }
}
