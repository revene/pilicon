package com.pilicon.order.dao;

import com.pilicon.order.OrderApplicationTests;
import com.pilicon.order.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderDetailDaoTest extends OrderApplicationTests {
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    public void save(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123456");
        orderDetail.setOrderId("123456");
        orderDetail.setProductId("157875196366160022");
        orderDetail.setProductIcon("http://xxx.com");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(0.01));
        orderDetail.setProductQuantity(2);
        OrderDetail save = orderDetailDao.save(orderDetail);
        Assert.assertTrue(save != null);

    }
}