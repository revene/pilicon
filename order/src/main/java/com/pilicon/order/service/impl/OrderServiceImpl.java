package com.pilicon.order.service.impl;

import com.netflix.discovery.converters.Auto;
import com.pilicon.order.dao.OrderDetailDao;
import com.pilicon.order.dao.OrderMasterDao;
import com.pilicon.order.dto.OrderDto;
import com.pilicon.order.entity.OrderMaster;
import com.pilicon.order.enums.OrderStatusEnum;
import com.pilicon.order.enums.PayStatusEnum;
import com.pilicon.order.service.OrderService;
import com.pilicon.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private OrderMasterDao orderMasterDao;


    @Override
    public OrderDto create(OrderDto orderDto) {
        //todo 查询商品信息(调用商品服务)
        //todo 计算总价
        //todo 扣库存

        //订单入库
        OrderMaster orderMaster = new OrderMaster();

        orderDto.setOrderId(KeyUtil.genUniqueKey());

        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        OrderMaster save = orderMasterDao.save(orderMaster);


        return orderDto;
    }
}
