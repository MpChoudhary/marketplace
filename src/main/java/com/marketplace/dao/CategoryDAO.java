package com.marketplace.dao;

import java.util.List;

import com.marketplace.entity.Category;

public interface CategoryDAO {
    List<Category> getCategories();
    Category getCategory(int id);
    void deleteCategory(int id);
    void saveCategory(Category c);
}

