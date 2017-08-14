package com.deviceinsight.services.ecommerce.service;

import com.deviceinsight.services.ecommerce.dto.cart.CartDto;
import com.deviceinsight.services.ecommerce.dto.cart.LineItemDto;
import com.deviceinsight.services.ecommerce.impl.dao.CartDao;
import com.deviceinsight.services.ecommerce.impl.dao.LineItemDao;
import com.deviceinsight.services.ecommerce.impl.model.Cart;
import com.deviceinsight.services.ecommerce.impl.model.LineItem;
import com.deviceinsight.services.model.Product;
import com.deviceinsight.services.model.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CartServiceImpl implements CartService {

    @Autowired
    private LineItemDao lineItemDao;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private ProductDao<Product> productDao;

    @Override
    public CartDto getCart(String cartUuid) {

        List<LineItem> lineItems = lineItemDao.list(cartUuid);
        List<LineItemDto> lineItemDtos = lineItems.stream().map(i -> getLineItemDto(i)).collect(Collectors.toList());


        double totalInclTax = 0;

        for(Object ldto : lineItems) {
             totalInclTax = ((LineItem) ldto).getAmount()*((LineItem) ldto).getPrice();
        }

        CartDto cartDto = new CartDto(lineItemDtos, totalInclTax, 0d);



        return cartDto;
    }

    @Override
    public void addToCart(String cartUuid, String productUuid, int amount) {

        Product existingProduct = productDao.getByUUID(productUuid);

        if(existingProduct==null) {
            LineItem danglingLineItem = (LineItem) lineItemDao.getByCartUuidAndProductUuid(cartUuid, productUuid);
            lineItemDao.delete(danglingLineItem);
            throw new RuntimeException("product uuid does not exist");
        }

        LineItem existingLineItem = (LineItem) lineItemDao.getByCartUuidAndProductUuid(cartUuid, productUuid);

        if(existingLineItem==null) {
            LineItem lineItem = new LineItem();
            lineItem.setAmount(amount);
            lineItem.setCartUuid(cartUuid);
            lineItem.setPrice(existingProduct.getPrice());
            lineItem.setProductUuid(productUuid); // TODO
            lineItem.setTax(19f); // TODO
            lineItem.setTitle(existingProduct.getTitle());
            lineItemDao.save(lineItem);
        } else {
            if(amount==0) {
                lineItemDao.delete(existingLineItem);
            } else {
                existingLineItem.setAmount(amount);
                lineItemDao.save(existingLineItem);
            }
        }

    }

    @Override
    public String createCart(String sessionId) {
        Cart newCart = new Cart();
        newCart.setSessionId(sessionId);
        return cartDao.save(newCart).toString();
    }

    @Override
    public void synchronizeProductPropertiesToCart(String cartUuid, String productUuid) {


    }


    private LineItemDto getLineItemDto(LineItem i) {
        return new LineItemDto(i.getTitle(), i.getPrice(), i.getAmount(), i.getTax());
    }


}
