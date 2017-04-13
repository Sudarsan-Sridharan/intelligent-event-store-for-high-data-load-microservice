package com.deviceinsight.services.model.dao;

import com.deviceinsight.services.model.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao<Product> {

    @Autowired
    private SessionFactory sessionFactory;
    //private Class<Product> type;

    public ProductDaoImpl() {
        super(Product.class);
    }

    @Override
    public void save(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }

   /* @Override
    public void save(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }*/


  /*  public Class<Product> setMyType() {
        this.type = Product.class;
    }
*/
}
