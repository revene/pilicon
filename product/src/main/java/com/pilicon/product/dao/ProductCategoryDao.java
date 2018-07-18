package com.pilicon.product.dao;

import com.pilicon.product.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer> {

    @Query(value = "select p from ProductCategory p where p.categoryType in :list")
    List<ProductCategory> findByCategoryType(@Param("list") List<Integer> list);
}
