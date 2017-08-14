package com.deviceinsight.services.model.dao;

public interface ProductDao<Product> extends BaseDao<Product> {

    public void save(Product product);

}
