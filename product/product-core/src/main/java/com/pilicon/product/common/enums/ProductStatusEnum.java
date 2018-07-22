package com.pilicon.product.common.enums;

import lombok.Getter;

/**
 * 枚举类
 */
//加入属性的get方法
@Getter
public enum ProductStatusEnum {
    UP(0,"在架"),
    DOWN(1,"下架"),
    ;

    private Integer code;

    private String message;


    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
