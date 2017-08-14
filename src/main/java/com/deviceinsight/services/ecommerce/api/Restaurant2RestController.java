package com.deviceinsight.services.ecommerce.api;

import com.deviceinsight.services.model.Restaurant;
import com.deviceinsight.services.model.dao.RestaurantDao;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Restaurant2RestController {
    private static final Logger LOG = LogManager.getLogger(Restaurant2RestController.class);

    @Autowired
    private RestaurantDao restaurantDao;


    @RequestMapping(value = "/api/restaurant2", method = RequestMethod.GET)
    public List<Restaurant> get() {



        // LOG.info("Appending string: {}.", "Hello, World");
        return restaurantDao.list();

    }
@Transactional
    @RequestMapping(value = "/api/restaurant/{uuid}", method = RequestMethod.GET)
    public List<Restaurant> gest() {



        // LOG.info("Appending string: {}.", "Hello, World");
        return restaurantDao.list();

    }


}

