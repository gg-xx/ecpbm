package com.xcu.service;

import com.xcu.pojo.Pager;
import com.xcu.pojo.ProductInfo;

import java.util.List;
import java.util.Map;

public interface ProductInfoService {
    //分页显示商品
    List<ProductInfo> findProductInfo(ProductInfo productInfo, Pager pager);

    //添加条件获取商品总数
    Integer count(Map<String,Object> params);
    //添加商品
    void addProductInfo(ProductInfo productInfo);

    //修改商品
    void modifyProductInfo(ProductInfo productInfo);

    //更新商品状态
    void modifyStatus(String ids,int flag);

    //获取在售商品列表
    List<ProductInfo> getOnSaleProduct();

    //根据商品id获取商品对象
    ProductInfo getProductInfoById(int id);
}
