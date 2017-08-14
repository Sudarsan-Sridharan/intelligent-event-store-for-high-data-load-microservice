package com.deviceinsight.services.ecommerce.service;

import com.deviceinsight.services.ecommerce.dto.cart.CartDto;
import com.deviceinsight.services.ecommerce.impl.model.Cart;

import java.util.UUID;

public interface CartService {

    CartDto getCart(String cartUuid);

    void addToCart(String cartUuid, String productUuid, int amount);

    String createCart(String sessionId);

    void synchronizeProductPropertiesToCart(String cartUuid, String productUuid);
}
