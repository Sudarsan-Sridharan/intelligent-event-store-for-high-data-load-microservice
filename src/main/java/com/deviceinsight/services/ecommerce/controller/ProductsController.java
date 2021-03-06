package com.deviceinsight.services.ecommerce.controller;

import com.deviceinsight.services.model.Product;
import com.deviceinsight.services.model.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductsController {

    @Autowired
    private ProductDao<Product> productDao;

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public void saveProduct() {
        Product product = new Product();
        product.setTitle("the title");
        productDao.save(product);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public List<Product> get() {
        //return productDao.getByUUID("b77d8fe6-c61b-49a9-9edd-6c627fcec6c7");
        return productDao.list();
    }
}