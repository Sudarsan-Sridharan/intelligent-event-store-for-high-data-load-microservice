package com.deviceinsight.services.ecommerce.controller;


import com.deviceinsight.services.ecommerce.dto.cart.CartDto;
import com.deviceinsight.services.ecommerce.impl.dao.CartDao;
import com.deviceinsight.services.ecommerce.impl.dao.LineItemDao;
import com.deviceinsight.services.ecommerce.impl.model.Cart;
import com.deviceinsight.services.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping(value = "/checkout")
public class CheckoutController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private LineItemDao lineItemDao;

    @Transactional
    @RequestMapping(value = "", method = RequestMethod.GET)
    public CheckoutDto checkout(HttpServletRequest httpServletRequest) {
        String sessionId = httpServletRequest.getSession().getId();
        Cart cart = (Cart) cartDao.findBySessionId(sessionId);

        String cartUuid = cart.getUuid().toString();


        CartDto cartDto = cartService.getCart(cartUuid);

        CheckoutDto checkoutDto = new CheckoutDto();
        checkoutDto.setCart(cartDto);

        return checkoutDto;


    }


}
