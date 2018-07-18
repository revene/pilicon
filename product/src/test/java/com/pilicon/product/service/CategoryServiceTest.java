package com.pilicon.product.service;

import com.netflix.discovery.converters.Auto;
import com.pilicon.product.ProductApplicationTests;
import com.pilicon.product.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Component
public class CategoryServiceTest extends ProductApplicationTests {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findByCategoryType() throws Exception {
        List<ProductCategory> productCategoryList = categoryService.findByCategoryType(Arrays.asList(11, 22));
        Assert.assertTrue(productCategoryList.size()>0);
    }
}