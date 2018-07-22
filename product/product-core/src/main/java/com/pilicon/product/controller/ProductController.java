package com.pilicon.product.controller;

import com.pilicon.product.dto.CartDto;
import com.pilicon.product.dto.ProductInfoDto;
import com.pilicon.product.entity.ProductCategory;
import com.pilicon.product.entity.ProductInfo;
import com.pilicon.product.service.CategoryService;
import com.pilicon.product.service.ProductService;
import com.pilicon.product.vo.ProductVO;
import com.pilicon.product.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 1. 查询所有在架的商品
     * 2. 获取类目type列表
     * 3. 查询类目
     * 4. 构造数据
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ResultVO<ProductVO> list() throws Exception {
        List<ProductInfo> productInfoList = productService.findAll();
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryType(categoryTypeList);

        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory: productCategoryList){
            ProductVO productVO=new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoDto> productInfoDtoList = new ArrayList<>();
            for (ProductInfo productInfo: productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoDto productInfoDto = new ProductInfoDto();
                    BeanUtils.copyProperties(productInfo, productInfoDto);
                    productInfoDtoList.add(productInfoDto);
                }
            }
            productVO.setProductInfoDtoList(productInfoDtoList);
            productVOList.add(productVO);

        }

        ResultVO resultVO = new ResultVO();
        resultVO.setData(productVOList);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }


    /**
     * 获取商品列表(给定单服务用的)
     * @param productIdlist
     * @return
     */
    @RequestMapping(value = "listForOrder",method = RequestMethod.POST)
    public List<ProductInfo> listForOrder(@RequestBody List<String> productIdlist){
        return productService.findByProductId(productIdlist);
    }


    /**
     * 扣除库存
     * @param cartDtoList
     * @throws Exception
     */
    @RequestMapping(value = "decreaseStock",method = RequestMethod.POST)
    public void decreaseStock(@RequestBody List<CartDto> cartDtoList)throws Exception{
        productService.decreateStore(cartDtoList);
    }
}

