package com.pilicon.orders.service.impl;


import com.pilicon.orders.entity.OrderDetail;
import com.pilicon.orders.enums.ResultEnum;
import com.pilicon.orders.exception.OrderException;
import com.pilicon.orders.service.OrderService;
import com.pilicon.orders.enums.OrderStatusEnum;
import com.pilicon.orders.enums.PayStatusEnum;
import com.pilicon.orders.utils.KeyUtil;
import com.pilicon.orders.dao.OrderDetailDao;
import com.pilicon.orders.dao.OrderMasterDao;
import com.pilicon.orders.dto.OrderDto;
import com.pilicon.orders.entity.OrderMaster;
import com.pilicon.product.api.ProductClientApi;
import com.pilicon.product.common.DecreaseStockInput;
import com.pilicon.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private OrderMasterDao orderMasterDao;
    @Autowired
    private ProductClientApi productClientApi;


    @Override
    public OrderDto create(OrderDto orderDto) throws Exception {
        String orderId = KeyUtil.genUniqueKey();
        //todo 查询商品信息(调用商品服务)
        /** lamda表达式 获取一个对象中的List中的某一个String字段组成一个新的集合的写法*/
        List<String> productIdList = orderDto.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productClientApi.listForOrder(productIdList);
        //todo 计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDto.getOrderDetailList()) {
            //总价=单价*数量
            for (ProductInfoOutput productInfo : productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    //订单详情入库
                    orderDetailDao.save(orderDetail);
                }
            }
        }
        //todo 扣库存
        /** 这个lamda表达式可以轻松的用来根据已经有的list重构一个我希望的另一个list*/
        List<DecreaseStockInput> cartDtoList = orderDto.getOrderDetailList().stream()
                .map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClientApi.decreaseStock(cartDtoList);

        //订单入库
        OrderMaster orderMaster = new OrderMaster();

        orderDto.setOrderId(orderId);

        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        OrderMaster save = orderMasterDao.save(orderMaster);


        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto finish(String orderId) {
        //1 .先查询订单
        OrderMaster orderMaster = orderMasterDao.findByOrderId(orderId);
        if (orderMaster == null){
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }
        //2. 判断订单状态
        if (OrderStatusEnum.NEW.getCode() != orderMaster.getOrderStatus()){
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //3. 修改订单状态为完结
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster save = orderMasterDao.save(orderMaster);

        OrderDto orderDto = new OrderDto();
        //查询订单详情
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)){
            throw new OrderException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }

        //复制对象属性
        BeanUtils.copyProperties(orderMaster,orderDto);
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }
}
