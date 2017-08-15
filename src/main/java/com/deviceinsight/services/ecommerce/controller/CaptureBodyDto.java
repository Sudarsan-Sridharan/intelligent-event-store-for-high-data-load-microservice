package com.deviceinsight.services.ecommerce.controller;

import com.deviceinsight.services.ecommerce.dto.payment.PaymentSelectionDto;

public class CaptureBodyDto {

    private PersonalDataDto personalData;
    private PaymentSelectionDto paymentSelection;

    public CaptureBodyDto() {
    }

    public CaptureBodyDto(PersonalDataDto personalData, PaymentSelectionDto paymentSelection) {
        this.personalData = personalData;
        this.paymentSelection = paymentSelection;
    }

    public PersonalDataDto getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalDataDto personalData) {
        this.personalData = personalData;
    }

    public PaymentSelectionDto getPaymentSelection() {
        return paymentSelection;
    }

    public void setPaymentSelection(PaymentSelectionDto paymentSelection) {
        this.paymentSelection = paymentSelection;
    }
}
