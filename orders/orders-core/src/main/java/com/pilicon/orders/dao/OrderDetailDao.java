package com.pilicon.orders.dao;

import com.pilicon.orders.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailDao extends JpaRepository<OrderDetail, String> {

    @Query("select p from OrderDetail p where p.orderId = :orderId")
    public List<OrderDetail> findByOrderId(@Param("orderId") String orderId);
}
