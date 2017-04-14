package com.deviceinsight.services.ecommerce.api;

import com.deviceinsight.services.model.Product;
import com.deviceinsight.services.model.dao.ProductDao;
import com.google.protobuf.ByteString;
import com.hazelcast.nio.IOUtil;
import com.sun.mail.iap.ByteArray;
import org.bouncycastle.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/products")
public class ProductRestController {

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
    public Product get() {
        return productDao.getByUUID("b77d8fe6-c61b-49a9-9edd-6c627fcec6c7");
    }

}
