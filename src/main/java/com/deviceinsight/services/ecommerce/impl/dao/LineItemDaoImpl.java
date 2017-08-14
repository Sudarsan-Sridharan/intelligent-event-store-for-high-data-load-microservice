package com.deviceinsight.services.ecommerce.impl.dao;

import com.deviceinsight.services.ecommerce.impl.model.LineItem;
import com.deviceinsight.services.model.Product;
import com.deviceinsight.services.model.Restaurant;
import com.deviceinsight.services.model.dao.BaseDaoImpl;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Component
public class LineItemDaoImpl extends BaseDaoImpl<LineItem> implements LineItemDao<LineItem> {

    @Autowired
    private SessionFactory sessionFactory;

    public LineItemDaoImpl() {
        super(LineItem.class);
    }

    @Override
    public void save(LineItem lineItem) {
        sessionFactory.getCurrentSession().save(lineItem);
    }

    @Override
    @Transactional
    public List<LineItem> list(String cartUuid) {
        return sessionFactory.getCurrentSession().createCriteria(LineItem.class).add(Restrictions.eq("cartUuid", cartUuid)).list();
    }

    @Override
    public LineItem getByCartUuidAndProductUuid(String cartUuid, String productUuid) {
        return (LineItem) sessionFactory.getCurrentSession()
                .createCriteria(LineItem.class)
                .add(Restrictions.and(
                        Restrictions.eq("cartUuid", cartUuid),
                        Restrictions.eq("productUuid", productUuid))
                )
                .uniqueResult();
    }
}
