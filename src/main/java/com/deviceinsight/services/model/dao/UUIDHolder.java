package com.deviceinsight.services.model.dao;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;


public class UUIDHolder {
    private UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
