package com.deviceinsight.services.model.dao;

import com.deviceinsight.services.model.Product;

public interface ProductDao<Product> extends BaseDao<Product> {

public void save(Product product);

}
