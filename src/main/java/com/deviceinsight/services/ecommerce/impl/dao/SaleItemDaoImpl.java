package com.deviceinsight.services.ecommerce.impl.dao;

import com.deviceinsight.services.ecommerce.impl.model.SaleItem;
import com.deviceinsight.services.model.dao.BaseDaoImpl;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class SaleItemDaoImpl extends BaseDaoImpl<SaleItem> implements SaleItemDao<SaleItem> {

    @Autowired
    private SessionFactory sessionFactory;

    public SaleItemDaoImpl() {
        super(SaleItem.class);
    }

    @Override
    public void save(SaleItem saleItem) {
        sessionFactory.getCurrentSession().save(saleItem);
    }

    @Override
    @Transactional
    public List<SaleItem> list(String cartUuid) {
        return sessionFactory.getCurrentSession().createCriteria(SaleItem.class).add(Restrictions.eq("cartUuid", cartUuid)).list();
    }
}
