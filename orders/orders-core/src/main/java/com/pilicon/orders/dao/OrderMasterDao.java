package com.pilicon.orders.dao;

import com.pilicon.orders.entity.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMasterDao extends JpaRepository<OrderMaster, String> {

    @Query(value = "select p from OrderMaster p where p.orderId=:orderId")
    public OrderMaster findByOrderId(@Param("orderId") String orderId);

}
