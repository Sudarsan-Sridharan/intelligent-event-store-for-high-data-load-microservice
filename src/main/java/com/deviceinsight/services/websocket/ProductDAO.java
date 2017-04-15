package com.deviceinsight.services.websocket;

import com.deviceinsight.services.model.Product;

import java.util.List;

/**
 * Created by ebners on 14.04.17.
 */
public interface ProductDAO {
    public List<Product> list();

    public Product get(int id);

    public void saveOrUpdate(Product user);


    public Product findByTicker(String string);

    public Product findByEmployeeId(int string);

    public Product findByKellnerName(String string);

    public List<Product> getAll();

    void delete(Product id);

    Product findById(String username);

    public String getCurrentItemCount();
}
