package com.pilicon.product.service;

import com.pilicon.product.entity.ProductCategory;

import java.util.List;

public interface CategoryService {

    public List<ProductCategory> findByCategoryType(List<Integer> list)throws Exception;
}
