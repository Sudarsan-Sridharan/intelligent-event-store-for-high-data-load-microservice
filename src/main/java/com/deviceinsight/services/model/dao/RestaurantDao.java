package com.deviceinsight.services.model.dao;

import java.util.List;

public interface RestaurantDao<Restaurant> extends BaseDao<Restaurant> {

    public void save(Restaurant product);

    public List<Restaurant> list();
}
