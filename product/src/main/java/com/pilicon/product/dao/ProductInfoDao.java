package com.pilicon.product.dao;

import com.pilicon.product.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductInfoDao extends JpaRepository<ProductInfo,String> {

    @Query(value = "select p from ProductInfo p where p.productStatus=:productStatus")
    public List<ProductInfo> findAllByProductStatus(@Param("productStatus") Integer productStatus);


}
