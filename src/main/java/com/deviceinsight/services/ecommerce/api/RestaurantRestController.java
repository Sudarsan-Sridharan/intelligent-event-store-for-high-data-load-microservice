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
@RequestMapping(value = "/api/restaurant")
public class RestaurantRestController {
    private static final Logger LOG = LogManager.getLogger(RestaurantRestController.class);

    @Autowired
    private RestaurantDao<Restaurant> restaurantDao;
//    private RestaurantDao<Restaurant> restaurantDao;//    private RestaurantDao<Restaurant> restaurantDao;//    private RestaurantDao<Restaurant> restaurantDao;//    private RestaurantDao<Restaurant> restaurantDao;//    private RestaurantDao<Restaurant> restaurantDao;//    private RestaurantDao<Restaurant> restaurantDao;//    private RestaurantDao<Restaurant> restaurantDao;//    private RestaurantDao<Restau

    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public List<Restaurant> get() {
        LOG.debug("This will be printed on debug");
        LOG.info("This will be printed on info");
        LOG.warn("This will be printed on warn");
        LOG.error("This will be printed on error");
        LOG.fatal("This will be printed on fatal");

       // LOG.info("Appending string: {}.", "Hello, World");
        return restaurantDao.list();
    }
}
