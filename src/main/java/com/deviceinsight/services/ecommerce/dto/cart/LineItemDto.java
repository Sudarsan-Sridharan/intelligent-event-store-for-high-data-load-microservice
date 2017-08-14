package com.deviceinsight.services.ecommerce.dto.cart;

public class LineItemDto {

    private String title;
    private float price;
    private int amount;
    private float tax;

    public LineItemDto(String title, float price, int amount, float tax) {
        this.title = title;
        this.price = price;
        this.amount = amount;
        this.tax = tax;
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

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }
}
