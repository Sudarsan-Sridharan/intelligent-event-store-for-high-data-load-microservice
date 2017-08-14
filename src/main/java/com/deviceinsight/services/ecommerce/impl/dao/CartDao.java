package com.deviceinsight.services.ecommerce.impl.dao;

import com.deviceinsight.services.model.dao.BaseDao;

import java.util.List;
import java.util.UUID;

public interface CartDao<Cart> extends BaseDao<Cart> {

    UUID save(Cart cart);

    List<Cart> list(String sessionId);

    Cart findBySessionId(String sessionId);

}
