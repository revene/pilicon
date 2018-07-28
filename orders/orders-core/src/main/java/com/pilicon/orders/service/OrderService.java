package com.pilicon.orders.service;

import com.pilicon.orders.dto.OrderDto;

public interface OrderService {
    public OrderDto create(OrderDto orderDto) throws Exception;


    /**
     * 完结订单(只能卖家操作)
     * @param orderId
     * @return
     */
    OrderDto finish (String orderId);
}
