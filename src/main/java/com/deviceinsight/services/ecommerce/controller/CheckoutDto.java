package com.deviceinsight.services.ecommerce.controller;

import com.deviceinsight.services.ecommerce.dto.cart.CartDto;

public class CheckoutDto {

    private CartDto cart;

    public CheckoutDto() {
    }


    public CheckoutDto(CartDto cart) {
        this.cart = cart;
    }

    public CartDto getCart() {
        return cart;
    }

    public void setCart(CartDto cart) {
        this.cart = cart;
    }
}
