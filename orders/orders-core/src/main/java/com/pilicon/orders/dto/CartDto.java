package com.pilicon.orders.dto;

import lombok.Data;

@Data
public class CartDto {

    private String productId;

    private Integer productQuantity;

    public CartDto() {
    }

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
