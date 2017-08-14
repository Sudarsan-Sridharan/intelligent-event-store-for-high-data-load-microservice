package com.deviceinsight.services.ecommerce.impl.dao;

import com.deviceinsight.services.ecommerce.impl.model.Cart;
import com.deviceinsight.services.ecommerce.impl.model.LineItem;
import com.deviceinsight.services.model.dao.BaseDaoImpl;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
public class CartDaoImpl extends BaseDaoImpl<Cart> implements CartDao<Cart> {

    @Autowired
    private SessionFactory sessionFactory;

    public CartDaoImpl() {
        super(Cart.class);
    }

    @Override
    public UUID save(Cart cart) {
        return (UUID) sessionFactory.getCurrentSession().save(cart);
    }

    @Override
    @Transactional
    public List<Cart> list(String sessionId) {
        return sessionFactory.getCurrentSession().createCriteria(Cart.class).add(Restrictions.eq("sessionId", sessionId)).list();
    }

    @Override
    public Cart findBySessionId(String sessionId) {
        return (Cart) sessionFactory.getCurrentSession().createCriteria(Cart.class).add(Restrictions.eq("sessionId", sessionId)).uniqueResult();
    }
}
