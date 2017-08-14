package com.deviceinsight.services.ecommerce.controller;

import com.deviceinsight.services.ecommerce.dto.cart.LineItemDto;
import com.deviceinsight.services.ecommerce.impl.model.LineItem;

public class LineItemDifferenceDto {

    private LineItemDto before;
    private LineItemDto after;

    public LineItemDifferenceDto(LineItemDto before, LineItemDto after) {
        this.before = before;
        this.after = after;
    }

    public LineItemDto getBefore() {
        return before;
    }

    public void setBefore(LineItemDto before) {
        this.before = before;
    }

    public LineItemDto getAfter() {
        return after;
    }

    public void setAfter(LineItemDto after) {
        this.after = after;
    }
}
