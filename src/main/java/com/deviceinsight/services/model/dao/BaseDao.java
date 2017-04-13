package com.deviceinsight.services.model.dao;

import com.deviceinsight.services.model.Product;

import java.util.UUID;

public interface BaseDao<T> {

    UUID getId();

    void setId(UUID id);

    T getByUUID(String id);
}
