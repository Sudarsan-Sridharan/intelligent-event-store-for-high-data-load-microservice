package com.deviceinsight.services.ecommerce.service;

import com.deviceinsight.services.ecommerce.controller.CaptureBodyDto;
import com.deviceinsight.services.ecommerce.controller.PersonalDataDto;
import com.deviceinsight.services.ecommerce.dto.cart.CartDto;
import com.deviceinsight.services.ecommerce.dto.cart.LineItemDto;
import com.deviceinsight.services.ecommerce.impl.dao.CartDao;
import com.deviceinsight.services.ecommerce.impl.dao.LineItemDao;
import com.deviceinsight.services.ecommerce.impl.dao.OrderDao;
import com.deviceinsight.services.ecommerce.impl.dao.SaleItemDao;
import com.deviceinsight.services.ecommerce.impl.model.Cart;
import com.deviceinsight.services.ecommerce.impl.model.LineItem;
import com.deviceinsight.services.ecommerce.impl.model.Order;
import com.deviceinsight.services.ecommerce.impl.model.SaleItem;
import com.deviceinsight.services.model.Product;
import com.deviceinsight.services.model.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CaptureServiceImpl implements CaptureService {

    @Autowired
    private LineItemDao lineItemDao;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private ProductDao<Product> productDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private SaleItemDao saleItemDao;


    @Override
    public CaptureResultDto complete(String sessionId, CaptureBodyDto captureBodyDto) {

        Cart cart = (Cart) cartDao.findBySessionId(sessionId);

        if(cart==null) {
            throw new RuntimeException("no cart exists");
        }

        List<LineItem> lineItems = lineItemDao.list(cart.getUuid().toString());

        if(lineItems.isEmpty()) {
            throw new RuntimeException("no line items exist");
        }

        Order order = new Order();

        PersonalDataDto personalDataDto = captureBodyDto.getPersonalData();

        order.setFirstName(personalDataDto.getFirstName());
        order.setLastName(personalDataDto.getLastName());
        order.setStreetName(personalDataDto.getStreetName());
        order.setStreetNumber(personalDataDto.getStreetNumber());
        order.setPostalCode(personalDataDto.getPostalCode());
        order.setCity(personalDataDto.getCity());
        order.setPayment(captureBodyDto.getPaymentSelection().getMethod());

        String orderUuid = orderDao.save(order).toString();

        for(LineItem lineItem : lineItems) {
            SaleItem saleItem = new SaleItem();
            saleItem.setTitle(lineItem.getTitle());
            saleItem.setAmount(lineItem.getAmount());
            saleItem.setPrice(lineItem.getPrice());
            saleItem.setProductUuid(lineItem.getProductUuid());
            saleItem.setCartUuid(lineItem.getCartUuid());
            saleItem.setTax(lineItem.getTax());
            saleItemDao.save(saleItem);
        }

        cartDao.delete(cart);
        CaptureResultDto captureResultDto = new CaptureResultDto();
        captureResultDto.setOrderNumber(orderUuid);
        return captureResultDto;
    }
}
