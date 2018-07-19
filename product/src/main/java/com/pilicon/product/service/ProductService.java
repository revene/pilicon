package com.pilicon.product.service;

import com.pilicon.product.dto.CartDto;
import com.pilicon.product.entity.ProductInfo;

import java.util.List;

public interface ProductService {

    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findAll();

    /**
     * 查询商品列表
     * */
    List<ProductInfo> findByProductId(List<String> productIdList);

    /**
     * 扣库存
     */
    void decreateStore(List<CartDto> cartDtoList);
}
