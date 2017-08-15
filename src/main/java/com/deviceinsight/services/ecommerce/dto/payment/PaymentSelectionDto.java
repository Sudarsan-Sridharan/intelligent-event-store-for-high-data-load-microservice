package com.deviceinsight.services.ecommerce.dto.payment;

public class PaymentSelectionDto {

    String method;

    public PaymentSelectionDto() {
    }

    public PaymentSelectionDto(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
