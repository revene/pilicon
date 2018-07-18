package com.pilicon.product.dao;

import com.pilicon.product.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void findByCategoryType() {
        List<ProductCategory> productCategoryList = productCategoryDao.findByCategoryType(Arrays.asList(11,22));
        Assert.assertTrue(productCategoryList.size()>0);
    }
}