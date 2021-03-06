package com.pilicon.product.api;


import com.pilicon.product.common.DecreaseStockInput;
import com.pilicon.product.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "product", path = "product",fallback =ProductClientApi.ProductClientFallBack.class )
public interface ProductClientApi {

    @RequestMapping(value = "/msg", method = RequestMethod.GET)
    String productMsg();

    //注意,用了@RequestBody,则必须要使用POST方法
    @RequestMapping(value = "listForOrder", method = RequestMethod.POST)
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @RequestMapping(value = "decreaseStock", method = RequestMethod.POST)
    void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList) throws Exception;

    @Component
    static class ProductClientFallBack implements ProductClientApi{

        @Override
        public String productMsg() {
            return "fallback";
        }

        @Override
        public List<ProductInfoOutput> listForOrder(List<String> productIdList) {
            return null;
        }

        @Override
        public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) throws Exception {

        }
    }
}

