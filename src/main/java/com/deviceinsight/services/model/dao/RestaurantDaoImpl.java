package com.deviceinsight.services.model.dao;

import com.deviceinsight.services.model.Restaurant;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantDaoImpl extends BaseDaoImpl<Restaurant> implements RestaurantDao<Restaurant> {

    @Autowired
    private SessionFactory sessionFactory;

    public RestaurantDaoImpl() {
        super(Restaurant.class);
    }

    @Override
    public void save(Restaurant product) {
        sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public List<Restaurant> list() {
        return sessionFactory.getCurrentSession().createQuery("FROM Restaurant").list();
    }
}
