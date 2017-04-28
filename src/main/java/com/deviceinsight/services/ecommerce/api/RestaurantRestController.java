package com.deviceinsight.services.ecommerce.api;

import com.deviceinsight.services.model.Restaurant;
import com.deviceinsight.services.model.dao.RestaurantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/restaurant")
public class RestaurantRestController {

    @Autowired
    private RestaurantDao<Restaurant> restaurantDao;
//    private RestaurantDao<Restaurant> restaurantDao;//    private RestaurantDao<Restaurant> restaurantDao;//    private RestaurantDao<Restaurant> restaurantDao;//    private RestaurantDao<Restaurant> restaurantDao;//    private RestaurantDao<Restaurant> restaurantDao;//    private RestaurantDao<Restaurant> restaurantDao;//    private RestaurantDao<Restaurant> restaurantDao;//    private RestaurantDao<Restau

    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public List<Restaurant> get() {
        return restaurantDao.list();
    }
}
