package com.deviceinsight.services.config;

import org.xbill.DNS.Serial;

import java.io.Serializable;

/**
 * Created by ebners on 15.05.17.
 */
public class Notification implements Serializable {

public Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
