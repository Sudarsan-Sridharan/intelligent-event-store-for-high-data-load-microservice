package com.deviceinsight.services.model.dao;

import java.util.List;

public interface ProductDao<Product> extends BaseDao<Product> {

    public void save(Product product);

    List<com.deviceinsight.services.model.Product> list();


}
