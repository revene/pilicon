package com.pilicon.orders.exception;

import com.pilicon.orders.enums.ResultEnum;

public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
