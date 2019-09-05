package com.marketplace.dao;
import java.util.List;

import com.marketplace.entity.Product;

public interface ProductDAO {
    List<Product> getProducts();
    List<Object> getProducts(String columns);
    Product getProduct(int id);
    Object getProduct(int id, String columns);
    void deleteProduct(int id);
    void saveProduct(Product r);
}
