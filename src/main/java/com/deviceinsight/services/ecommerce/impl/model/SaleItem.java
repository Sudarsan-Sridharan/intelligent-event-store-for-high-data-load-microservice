package com.deviceinsight.services.ecommerce.impl.model;

import com.deviceinsight.services.model.IProduct;
import com.deviceinsight.services.model.dao.BaseUUIDEntity;

import javax.persistence.Entity;

@Entity
@javax.persistence.Table(name = "sale_items")
public class SaleItem extends BaseUUIDEntity implements IProduct {

    private String title;
    private float price;
    private int amount;
    private String cartUuid;
    private float tax;
    private String productUuid;

    public SaleItem() {
        super();
    }

    public SaleItem(String title, float price, int amount, String cartUuid, float tax, String productUuid) {
        this.title = title;
        this.price = price;
        this.amount = amount;
        this.cartUuid = cartUuid;
        this.tax = tax;
        this.productUuid = productUuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCartUuid() {
        return cartUuid;
    }

    public void setCartUuid(String cartUuid) {
        this.cartUuid = cartUuid;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public String getProductUuid() {
        return productUuid;
    }

    public void setProductUuid(String productUuid) {
        this.productUuid = productUuid;
    }
}
