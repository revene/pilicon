package com.pilicon.product.service.impl;

import com.pilicon.product.ProductApplicationTests;
import com.pilicon.product.entity.ProductInfo;
import com.pilicon.product.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Component
public class ProductServiceImplTest extends ProductApplicationTests {
    @Autowired
    private ProductService productService;

    @Test
    public void findByProductId()throws Exception {
        List<ProductInfo> productInfoList = productService.findByProductId(Arrays.asList("157875196366160022", "157875227953464068"));
        Assert.assertTrue(productInfoList.size()>0);
    }
}