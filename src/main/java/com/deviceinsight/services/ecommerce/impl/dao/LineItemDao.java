package com.deviceinsight.services.ecommerce.impl.dao;

import com.deviceinsight.services.model.dao.BaseDao;

import java.util.List;

public interface LineItemDao<LineItem> extends BaseDao<LineItem> {

    void save(LineItem lineItem);

    List<LineItem> list(String customerUuid);

    LineItem getByCartUuidAndProductUuid(String cartUuid, String productUuid);
}
