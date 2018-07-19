package com.pilicon.order.api;

import com.pilicon.order.dto.CartDto;
import com.pilicon.order.entity.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "product",path = "product")
public interface ProductClientApi {

    @RequestMapping(value = "/msg",method = RequestMethod.GET)
    String productMsg();

    //注意,用了@RequestBody,则必须要使用POST方法
    @RequestMapping(value = "listForOrder",method = RequestMethod.POST)
    List<ProductInfo> listForOrder(@RequestBody List<String> productIdList);

    @RequestMapping(value = "decreaseStock",method = RequestMethod.POST)
    void decreaseStock(@RequestBody List<CartDto> cartDtoList)throws Exception;
}

