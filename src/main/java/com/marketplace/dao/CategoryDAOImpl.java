package com.marketplace.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.marketplace.entity.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Category> getCategories() {
        Session session = sessionFactory.getCurrentSession();
        List<Category> categories = session.createQuery("from Category", Category.class)
                .getResultList();
        return categories;
    }

    @Override
    public void deleteCategory(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("delete from Category where id=:categoryId");
        q.setParameter("categoryId", id);
        q.executeUpdate();
    }

    @Override
    public Category getCategory(int id) {
        Session session = sessionFactory.getCurrentSession();
        Category c = session.get(Category.class, id);
        return c;
    }

    @Override
    public void saveCategory(Category c) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(c);
    }

}
