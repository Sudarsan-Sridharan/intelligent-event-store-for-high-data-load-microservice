package com.deviceinsight.services.model.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.UUID;

@Component
public class BaseDaoImpl<T> extends UUIDHolder implements BaseDao<T> {

    private Class<T> type;
    @Autowired
    private SessionFactory sessionFactory;

    public BaseDaoImpl() {
    }

    public BaseDaoImpl(Class<T> type) {
        this.type = type;
    }

    @Override
    public T getByUUID(String uuidAsString) {
        String withoutDashes = uuidAsString.replaceAll("-", "");
        BigInteger bi1 = new BigInteger(withoutDashes.substring(0, 16), 16);
        BigInteger bi2 = new BigInteger(withoutDashes.substring(16, 32), 16);
        UUID uuid = new UUID(bi1.longValue(), bi2.longValue());
        return sessionFactory.getCurrentSession().get(getMyType(), uuid);
    }

    public Class<T> getMyType() {
        return this.type;
    }
}
