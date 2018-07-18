package com.pilicon.order.dao;

import com.pilicon.order.OrderApplicationTests;
import com.pilicon.order.entity.OrderDetail;
import com.pilicon.order.entity.OrderMaster;
import com.pilicon.order.enums.OrderStatusEnum;
import com.pilicon.order.enums.PayStatusEnum;
import org.hibernate.criterion.Order;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@Component
public class OrderMasterDaoTest extends OrderApplicationTests {
    @Autowired
    private OrderMasterDao orderMasterDao;

    @Test
    public void testSave(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("师兄");
        orderMaster.setBuyerPhone("18551874597");
        orderMaster.setBuyerAddress("慕课网总部");
        orderMaster.setBuyerOpenid("1101110");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());


        OrderMaster save = orderMasterDao.save(orderMaster);

        Assert.assertTrue(save!=null);
    }

}