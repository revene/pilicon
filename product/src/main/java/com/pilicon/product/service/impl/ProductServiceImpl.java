package com.pilicon.product.service.impl;

import com.pilicon.product.dao.ProductInfoDao;
import com.pilicon.product.dto.CartDto;
import com.pilicon.product.entity.ProductInfo;
import com.pilicon.product.enums.ProductStatusEnum;
import com.pilicon.product.enums.ResultEnum;
import com.pilicon.product.exception.ProductException;
import com.pilicon.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public List<ProductInfo> findAll() {
         List<ProductInfo> allByProductStatus = productInfoDao.findAllByProductStatus(ProductStatusEnum.UP.getCode());
         return allByProductStatus;
    }

    @Override
    public List<ProductInfo> findByProductId(List<String> productIdList) {
        return productInfoDao.findByProductId(productIdList);
    }

    @Override
    @Transactional
    public void decreateStore(List<CartDto> cartDtoList) {
        for (CartDto cartDto: cartDtoList){
            Optional<ProductInfo> optionalProductInfo = productInfoDao.findById(cartDto.getProductId());
            //判断商品是不是存在
            if (!optionalProductInfo.isPresent()){
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            //判断商品的数量是否足够
            ProductInfo productInfo = optionalProductInfo.get();
            Integer result = productInfo.getProductStock() - cartDto.getProductQuantity();
            if (result < 0){
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            //扣除库存
            productInfo.setProductStock(result);
            productInfoDao.save(productInfo);
        }
    }
}
