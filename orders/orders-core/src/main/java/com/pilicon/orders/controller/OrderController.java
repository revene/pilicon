package com.pilicon.orders.controller;


import com.pilicon.orders.dto.OrderDto;
import com.pilicon.orders.enums.ResultEnum;
import com.pilicon.orders.exception.OrderException;
import com.pilicon.orders.service.OrderService;
import com.pilicon.orders.convert.OrderForm2OrderDto;
import com.pilicon.orders.form.OrderForm;
import com.pilicon.product.api.ProductClientApi;
import com.pilicon.product.common.DecreaseStockInput;
import com.pilicon.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("order")
@Slf4j
//这个注解用于保证我注入的配置文件里的env可以被刷新到
@RefreshScope
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductClientApi productClientApi;

    @Value("${env}")
    private String env;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public void create(@Valid @RequestBody OrderForm orderForm, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            log.error("创建订单 参数不正确,orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }

        //orderForm -> orderDto
        OrderDto orderDto = OrderForm2OrderDto.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("创建订单购物车为空");
            throw new OrderException(ResultEnum.CART_EMPTY.getCode(), ResultEnum.CART_EMPTY.getMessage());
        }

        OrderDto result = orderService.create(orderDto);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
    }

    @RequestMapping(value = "getPrpductList")
    public String getProductList() {
        List<ProductInfoOutput> productInfoList = productClientApi.listForOrder(Arrays.asList("157875227953464068"));
        log.info("response={}", productInfoList);
        return "ok";
    }

    @RequestMapping(value = "decreaseStock", method = RequestMethod.GET)
    public void decreaseStock() throws Exception {
        productClientApi.decreaseStock(Arrays.asList(new DecreaseStockInput("157875227953464068", 1)));
    }

    @RequestMapping(value = "getConfiguration", method = RequestMethod.GET)
    public String getConfiguration() throws Exception {
        return "hello";
    }

    @RequestMapping(value = "env",method = RequestMethod.GET)
    public String getEnv()throws Exception{
        return env;
    }
}
