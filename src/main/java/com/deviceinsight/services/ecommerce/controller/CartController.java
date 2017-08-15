package com.deviceinsight.services.ecommerce.controller;

import com.deviceinsight.services.ecommerce.dto.cart.CartDto;
import com.deviceinsight.services.ecommerce.dto.cart.LineItemDto;
import com.deviceinsight.services.ecommerce.impl.dao.CartDao;
import com.deviceinsight.services.ecommerce.impl.dao.LineItemDao;
import com.deviceinsight.services.ecommerce.impl.model.Cart;
import com.deviceinsight.services.ecommerce.impl.model.LineItem;
import com.deviceinsight.services.ecommerce.service.CartService;
import com.deviceinsight.services.model.Product;
import com.deviceinsight.services.model.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private LineItemDao lineItemDao;

    @Autowired
    private ProductDao<Product> productDao;

    @Autowired
    private CartService cartService;

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public void saveProduct() {
        Product product = new Product();
        product.setTitle("the title");
        productDao.save(product);
    }

    /*  @Transactional
      @RequestMapping(value = "", method = RequestMethod.GET)
      public void add(HttpServletRequest httpServletRequest) {
          String sessionId = httpServletRequest.getSession().getId();
          Cart availableCart = (Cart) cartDao.findBySessionId(sessionId);
          if (availableCart == null) {
              Cart cart = new Cart(sessionId);
              UUID savedCart = cartDao.save(cart);
              LineItem lineItem = new LineItem();
              lineItem.setAmount(1);
              lineItem.setCartUuid(savedCart.toString());
              lineItem.setPrice(1f);
              lineItem.setProductUuid("pid");
              lineItem.setTax(19f);
              lineItem.setTitle("product");
              lineItemDao.save(lineItem);
          }
      }
  */
    @Transactional
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CartAddResponseDto post(HttpServletRequest httpServletRequest, @RequestBody CartAddRequestDto cartAddRequestDto) {

        CartAddResponseDto cartAddResponseDto = new CartAddResponseDto();
        LineItemDifferenceDto lineItemDifferenceDto = null;

        Product product = productDao.getByUUID(cartAddRequestDto.getProductUuid().toString());

        String sessionId = httpServletRequest.getSession().getId();
        Cart availableCart = (Cart) cartDao.findBySessionId(sessionId);
        Cart cart = null;
        if (availableCart == null) {
            String cartUuid = cartService.createCart(sessionId);
            cartService.addToCart(cartUuid, cartAddRequestDto.getProductUuid(), cartAddRequestDto.getAmount());

        } else {
            cart = availableCart;
            LineItem lineItem = (LineItem) lineItemDao.getByCartUuidAndProductUuid(cart.getUuid().toString(), cartAddRequestDto.getProductUuid().toString());

            if(lineItem==null) {
                cartService.addToCart(cart.getUuid().toString(), cartAddRequestDto.getProductUuid(), cartAddRequestDto.getAmount());

            } else {


                if (!lineItem.getTitle().equals(product.getTitle()) || lineItem.getPrice() != product.getPrice()) {
                    cartService.synchronizeProductPropertiesToCart(cart.getUuid().toString(), product.getUuid().toString());
                    lineItemDifferenceDto = new LineItemDifferenceDto(new LineItemDto(lineItem.getTitle(), lineItem.getPrice(), lineItem.getAmount(), lineItem.getTax()), new LineItemDto(product.getTitle(), product.getPrice(), cartAddRequestDto.getAmount(), product.getPrice())); // todo
                }
                cartAddResponseDto.setDifference(lineItemDifferenceDto);
                cartService.addToCart(cart.getUuid().toString(), cartAddRequestDto.getProductUuid(), cartAddRequestDto.getAmount());
            }
        }

        try {
        } catch (RuntimeException e) {
        }

        return cartAddResponseDto;
    }

    @Transactional
    @RequestMapping(value = "", method = RequestMethod.GET)
    public CartDto get(HttpServletRequest httpServletRequest) {
        String sessionId = httpServletRequest.getSession().getId();

        Cart cart = (Cart) cartDao.findBySessionId(sessionId);
        if (cart != null) {
            return cartService.getCart(cart.getUuid().toString());
        } else {
            return new CartDto();
        }
    }
}
