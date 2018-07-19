package com.pilicon.order.controller;

import com.pilicon.order.api.ProductClientApi;
import com.pilicon.order.convert.OrderForm2OrderDto;
import com.pilicon.order.dto.CartDto;
import com.pilicon.order.dto.OrderDto;
import com.pilicon.order.entity.ProductInfo;
import com.pilicon.order.enums.ResultEnum;
import com.pilicon.order.exception.OrderException;
import com.pilicon.order.form.OrderForm;
import com.pilicon.order.service.OrderService;
import com.sun.java.swing.action.OkAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductClientApi productClientApi;

    @RequestMapping(value = "create",method = RequestMethod.POST)
    public void create(@Valid @RequestBody OrderForm orderForm, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()){
            log.error("创建订单 参数不正确,orderForm={}",orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        //orderForm -> orderDto
        OrderDto orderDto = OrderForm2OrderDto.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            log.error("创建订单购物车为空");
            throw new OrderException(ResultEnum.CART_EMPTY.getCode(),ResultEnum.CART_EMPTY.getMessage());
        }

        OrderDto result = orderService.create(orderDto);

        Map<String,String> map = new HashMap<>();
        map.put("orderId",result.getOrderId());
    }

    @RequestMapping(value = "getPrpductList")
    public String getProductList(){
        List<ProductInfo> productInfoList = productClientApi.listForOrder(Arrays.asList("157875227953464068"));
        log.info("response={}",productInfoList);
        return "ok";
    }

    @RequestMapping(value = "decreaseStock",method = RequestMethod.GET)
    public void decreaseStock() throws Exception{
        productClientApi.decreaseStock(Arrays.asList(new CartDto("157875227953464068",1)));
    }
}
