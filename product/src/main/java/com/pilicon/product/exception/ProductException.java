package com.pilicon.product.exception;

import com.pilicon.product.enums.ResultEnum;

public class ProductException extends RuntimeException {
    private Integer code;

    public ProductException(Integer code,String message) {
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum) {
        /** 加上这句话是因为子类的构造过程中必须去构造父类,如果子类的构造器中没有显示的调用父类的
         * 构造方法,就会默认去调用父类的无参的构造方法,此时如果父类没有无参的构造方法,就会报错
         * 而写个super(a..)这样的显示的调用父类的构造方法,就不会因为父类没有无参构造方法而报错了*/
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

}
