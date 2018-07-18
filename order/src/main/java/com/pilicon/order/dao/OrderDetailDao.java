package com.pilicon.order.dao;

import com.pilicon.order.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailDao extends JpaRepository<OrderDetail,String> {
}
