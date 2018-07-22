package com.pilicon.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductInfoDto {

    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;

    private String categoryName;

    @JsonIgnore
    private Integer productStock;

    @JsonIgnore
    private Integer productStatus;

    @JsonIgnore
    private Integer categoryType;

    @JsonIgnore
    private Date createTime;

    @JsonIgnore
    private Date updateTime;
}
