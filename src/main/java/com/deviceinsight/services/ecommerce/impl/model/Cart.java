package com.deviceinsight.services.ecommerce.impl.model;

import com.deviceinsight.services.model.IProduct;
import com.deviceinsight.services.model.dao.BaseUUIDEntity;

import javax.persistence.Entity;

@Entity
@javax.persistence.Table(name = "cart")
public class Cart extends BaseUUIDEntity implements IProduct {

    public Cart() {
    }

    private String sessionId;

    public Cart(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
