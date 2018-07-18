package com.pilicon.order.convert;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pilicon.order.dto.OrderDto;
import com.pilicon.order.entity.OrderDetail;
import com.pilicon.order.enums.ResultEnum;
import com.pilicon.order.exception.OrderException;
import com.pilicon.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDto {

    public static OrderDto convert(OrderForm orderForm){
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();

        Gson gson = new Gson();

        try {


        gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());

        }catch (Exception e) {
            log.error("Json转换错误,string={}",orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),ResultEnum.PARAM_ERROR.getMessage());
        }
        return orderDto;
    }
}
