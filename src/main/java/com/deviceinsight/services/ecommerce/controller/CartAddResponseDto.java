package com.deviceinsight.services.ecommerce.controller;

public class CartAddResponseDto {

    private LineItemDifferenceDto difference;


    public LineItemDifferenceDto getDifference() {
        return difference;
    }

    public void setDifference(LineItemDifferenceDto difference) {
        this.difference = difference;
    }
}
