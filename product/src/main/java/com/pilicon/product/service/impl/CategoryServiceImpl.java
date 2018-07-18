package com.pilicon.product.service.impl;

import com.pilicon.product.dao.ProductCategoryDao;
import com.pilicon.product.entity.ProductCategory;
import com.pilicon.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> findByCategoryType(List<Integer> list) throws Exception {
        List<ProductCategory> productCategoryList = productCategoryDao.findByCategoryType(list);
        return productCategoryList;
    }
}
