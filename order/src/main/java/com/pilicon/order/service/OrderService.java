package com.pilicon.order.service;

import com.pilicon.order.dto.OrderDto;

public interface OrderService {
    public OrderDto create(OrderDto orderDto)throws Exception;
}
