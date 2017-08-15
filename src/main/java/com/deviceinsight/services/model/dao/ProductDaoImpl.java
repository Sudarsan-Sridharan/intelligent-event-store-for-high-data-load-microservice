package com.deviceinsight.services.model.dao;

import com.deviceinsight.services.model.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao<Product> {

    @Autowired
    private SessionFactory sessionFactory;

    public ProductDaoImpl() {
        super(Product.class);
    }

    @Override
    public void save(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public List<Product> list() {

        return sessionFactory.getCurrentSession().createCriteria(Product.class).list();

    }
}
