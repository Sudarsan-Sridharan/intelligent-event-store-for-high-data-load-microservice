package com.deviceinsight.services.ecommerce.controller;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;

//@Validated
@ConfigurationProperties(prefix = "cartAdd")
public class CartAddRequestDto {

    private String productUuid;

    //@Min(0) // TODO
    private int amount;

    public CartAddRequestDto() {
    }

    public CartAddRequestDto(String productUuid, int amount) {
        this.productUuid = productUuid;
        this.amount = amount;
    }

    public String getProductUuid() {
        return productUuid;
    }

    public void setProductUuid(String productUuid) {
        this.productUuid = productUuid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
