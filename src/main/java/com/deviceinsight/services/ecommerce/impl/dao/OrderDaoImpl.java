package com.deviceinsight.services.ecommerce.impl.dao;

import com.deviceinsight.services.ecommerce.impl.model.Cart;
import com.deviceinsight.services.ecommerce.impl.model.Order;
import com.deviceinsight.services.model.dao.BaseDaoImpl;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao<Order> {

    @Autowired
    private SessionFactory sessionFactory;

    public OrderDaoImpl() {
        super(Order.class);
    }

    @Override
    public UUID save(Order order) {
        return (UUID) sessionFactory.getCurrentSession().save(order);
    }

    @Override
    @Transactional
    public List<Order> list(String sessionId) {
        return sessionFactory.getCurrentSession().createCriteria(Order.class).add(Restrictions.eq("sessionId", sessionId)).list();
    }

    @Override
    public Order findBySessionId(String sessionId) {
        return (Order) sessionFactory.getCurrentSession().createCriteria(Order.class).add(Restrictions.eq("sessionId", sessionId)).uniqueResult();
    }
}
