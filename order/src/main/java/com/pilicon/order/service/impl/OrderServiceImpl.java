package com.pilicon.order.service.impl;

import com.pilicon.order.api.ProductClientApi;
import com.pilicon.order.dao.OrderDetailDao;
import com.pilicon.order.dao.OrderMasterDao;
import com.pilicon.order.dto.CartDto;
import com.pilicon.order.dto.OrderDto;
import com.pilicon.order.entity.OrderDetail;
import com.pilicon.order.entity.OrderMaster;
import com.pilicon.order.entity.ProductInfo;
import com.pilicon.order.enums.OrderStatusEnum;
import com.pilicon.order.enums.PayStatusEnum;
import com.pilicon.order.service.OrderService;
import com.pilicon.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<ProductInfo> productInfoList = productClientApi.listForOrder(productIdList);
        //todo 计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail: orderDto.getOrderDetailList()){
            //总价=单价*数量
            for (ProductInfo productInfo: productInfoList){
                if (productInfo.getProductId().equals(orderDetail.getProductId())){
                    orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
                    BeanUtils.copyProperties(productInfo,orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    //订单详情入库
                    orderDetailDao.save(orderDetail);
                }
            }
        }
        //todo 扣库存
        /** 这个lamda表达式可以轻松的用来根据已经有的list重构一个我希望的另一个list*/
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream()
                .map(e -> new CartDto(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productClientApi.decreaseStock(cartDtoList);

        //订单入库
        OrderMaster orderMaster = new OrderMaster();

        orderDto.setOrderId(orderId);

        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        OrderMaster save = orderMasterDao.save(orderMaster);


        return orderDto;
    }
}
