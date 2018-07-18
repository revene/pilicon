package com.pilicon.product.service.impl;

import com.pilicon.product.dao.ProductInfoDao;
import com.pilicon.product.entity.ProductInfo;
import com.pilicon.product.enums.ProductStatusEnum;
import com.pilicon.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public List<ProductInfo> findAll() {
         List<ProductInfo> allByProductStatus = productInfoDao.findAllByProductStatus(ProductStatusEnum.UP.getCode());
         return allByProductStatus;
    }
}
