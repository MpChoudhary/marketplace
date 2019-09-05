package com.marketplace.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.marketplace.entity.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> getProducts() {
        Session session = sessionFactory.getCurrentSession();
        List<Product> products = session.createQuery("from Product", Product.class)
                .getResultList();
        return products;
    }

    @Override
    public List<Object> getProducts(String columns) {
        Session session = sessionFactory.getCurrentSession();
        List<Object> products = session.createQuery("Select " + columns +" from Product").getResultList();
        return products;
    }

    @Override
    public Product getProduct(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Product.class, id);
    }

    @Override
    public Object getProduct(int id, String columns) {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("Select " + columns + " from Product where id=:productId");
        q.setParameter("productId", id);
        Object obj = q.getSingleResult();
        return q.getSingleResult();
    }

    @Override
    public void deleteProduct(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("delete from Product where id=:productId");
        q.setParameter("productId", id);
        q.executeUpdate();
    }

    @Override
    public void saveProduct(Product p) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(p);
    }
}
