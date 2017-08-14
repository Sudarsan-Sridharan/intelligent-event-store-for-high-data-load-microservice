package com.deviceinsight.services.ecommerce.dto.cart;

import java.util.List;

public class CartDto {

    private List<LineItemDto> lineItems;

    private double totalInclTax;
    private double totalExclTax;

    public CartDto() {
    }

    public CartDto(List<LineItemDto> lineItems, double totalInclTax, double totalExclTax) {
        this.lineItems = lineItems;
        this.totalInclTax = totalInclTax;
        this.totalExclTax = totalExclTax;
    }

    public List<LineItemDto> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItemDto> lineItems) {
        this.lineItems = lineItems;
    }

    public double getTotalInclTax() {
        return totalInclTax;
    }

    public void setTotalInclTax(double totalInclTax) {
        this.totalInclTax = totalInclTax;
    }

    public double getTotalExclTax() {
        return totalExclTax;
    }

    public void setTotalExclTax(double totalExclTax) {
        this.totalExclTax = totalExclTax;
    }
}
