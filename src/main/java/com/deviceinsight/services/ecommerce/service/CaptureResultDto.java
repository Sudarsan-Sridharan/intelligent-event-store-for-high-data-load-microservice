package com.deviceinsight.services.ecommerce.service;

public class CaptureResultDto {

    private String orderNumber;

    public CaptureResultDto() {
    }

    public CaptureResultDto(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
