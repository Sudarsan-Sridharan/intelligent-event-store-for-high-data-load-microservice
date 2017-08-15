package com.deviceinsight.services.ecommerce.controller;


import com.deviceinsight.services.ecommerce.dto.cart.CartDto;
import com.deviceinsight.services.ecommerce.impl.dao.CartDao;
import com.deviceinsight.services.ecommerce.impl.dao.LineItemDao;
import com.deviceinsight.services.ecommerce.impl.dao.OrderDao;
import com.deviceinsight.services.ecommerce.impl.dao.SaleItemDao;
import com.deviceinsight.services.ecommerce.impl.model.Cart;
import com.deviceinsight.services.ecommerce.impl.model.LineItem;
import com.deviceinsight.services.ecommerce.impl.model.Order;
import com.deviceinsight.services.ecommerce.impl.model.SaleItem;
import com.deviceinsight.services.ecommerce.service.CaptureResultDto;
import com.deviceinsight.services.ecommerce.service.CaptureService;
import com.deviceinsight.services.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/capture")
public class CaptureController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private LineItemDao lineItemDao;

    @Autowired
    private SaleItemDao saleItemDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private CaptureService captureService;

    @Transactional
    @RequestMapping(value = "", method = RequestMethod.POST)
    public CaptureResultDto capture(HttpServletRequest httpServletRequest, @RequestBody CaptureBodyDto captureBodyDto) {
        String sessionId = httpServletRequest.getSession().getId();
            return captureService.complete(sessionId, captureBodyDto);
    }


}
