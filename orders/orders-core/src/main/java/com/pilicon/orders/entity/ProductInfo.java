package com.pilicon.orders.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

//lombook下的注解 自动生成getset方法
@Data
//表名和类名一致的情况下,是不需要写@Table的
@Table(name = "product_info")
@Entity
public class ProductInfo {


    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer productStatus;

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

}
