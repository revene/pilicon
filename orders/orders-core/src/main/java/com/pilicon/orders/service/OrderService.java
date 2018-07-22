package com.pilicon.orders.service;

import com.pilicon.orders.dto.OrderDto;

public interface OrderService {
    public OrderDto create(OrderDto orderDto) throws Exception;
}
