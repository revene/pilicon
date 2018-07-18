package com.pilicon.product.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pilicon.product.dto.ProductInfoDto;
import lombok.Data;

import java.util.List;

@Data
public class ProductVO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    List<ProductInfoDto> productInfoDtoList;
}
