package com.pilicon.product.dao;

import com.pilicon.product.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {
    @Autowired
    private ProductInfoDao productInfoDao;

    @Test
    public void findAllByProductStatus() {
        List<ProductInfo> productInfoList=productInfoDao.findAllByProductStatus(0);
        Assert.assertTrue(productInfoList.size()>0);
    }
}