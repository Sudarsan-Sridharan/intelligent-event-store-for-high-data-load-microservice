package com.deviceinsight.services.ecommerce.impl.dao;

import com.deviceinsight.services.model.dao.BaseDao;

import java.util.List;
import java.util.UUID;

public interface OrderDao<Order> extends BaseDao<Order> {

    UUID save(Order order);

    List<Order> list(String sessionId);

    Order findBySessionId(String sessionId);

}
