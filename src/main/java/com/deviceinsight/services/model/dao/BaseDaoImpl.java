package com.deviceinsight.services.model.dao;

import com.deviceinsight.services.model.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.UUID;

@Component
public class BaseDaoImpl<T> implements BaseDao<T> {

    private Class<T> type;

    public BaseDaoImpl() {
    }

    @Autowired
    private SessionFactory sessionFactory;

    private UUID id;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public T getByUUID(String id) {

        String withoutDashes = "24cff791-cf3b-4f6e-bab1-29c364c25b49".replaceAll("-", "");
        BigInteger bi1 = new BigInteger(withoutDashes.substring(0, 16), 16);
        BigInteger bi2 = new BigInteger(withoutDashes.substring(16, 32), 16);
        UUID uuid = new UUID(bi1.longValue(), bi2.longValue());


        return sessionFactory.getCurrentSession().get(getMyType(), uuid);
    }

    public BaseDaoImpl(Class<T> type) {
        this.type = type;
    }

    public Class<T> getMyType() {
        return this.type;
    }
}
