package com.deviceinsight.services.model.dao;

import java.util.UUID;

public interface BaseDao<T> {

    UUID getUuid();

    void setUuid(UUID uuid);

    T getByUUID(String s);
}
