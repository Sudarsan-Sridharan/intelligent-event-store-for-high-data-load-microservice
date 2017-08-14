package com.deviceinsight.services.ecommerce.impl.dao;

import com.deviceinsight.services.model.dao.BaseDao;

import java.util.List;

public interface SaleItemDao<SaleItem> extends BaseDao<SaleItem> {

    void save(SaleItem lineItem);

    List<SaleItem> list(String customerUuid);

}
