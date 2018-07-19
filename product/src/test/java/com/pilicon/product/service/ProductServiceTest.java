package com.pilicon.product.service;

import com.pilicon.product.ProductApplicationTests;
import com.pilicon.product.dto.CartDto;
import com.pilicon.product.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductServiceTest extends ProductApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void findAll() {
        List<ProductInfo> productInfoList = productService.findAll();
        Assert.assertTrue(productInfoList.size()>0);
    }

    @Test
    public void decreaseStock() throws Exception{
        CartDto cartDto = new CartDto();
        cartDto.setProductId("157875227953464068");
        cartDto.setProductQuantity(2);
        productService.decreateStore(Arrays.asList(cartDto));
    }
}