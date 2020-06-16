package com.xcu.service.impl;

import com.xcu.dao.ProductInfoDao;
import com.xcu.pojo.Pager;
import com.xcu.pojo.ProductInfo;
import com.xcu.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoDao productInfoDao;

    public List<ProductInfo> findProductInfo(ProductInfo productInfo, Pager pager) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("productInfo",productInfo);
        int recordCount = productInfoDao.count(params);
        pager.setRowCount(recordCount);
        if(recordCount>0){
            params.put("pager",pager);
        }
        return productInfoDao.selectByPage(params);
    }

    public Integer count(Map<String, Object> params) {
        return productInfoDao.count(params);
    }


    public void addProductInfo(ProductInfo productInfo) {
        productInfoDao.save(productInfo);
    }

    public void modifyProductInfo(ProductInfo productInfo) {
        productInfoDao.edit(productInfo);
    }

    public void modifyStatus(String ids, int flag) {
        productInfoDao.updateState(ids,flag);
    }

    public List<ProductInfo> getOnSaleProduct() {
        return productInfoDao.getOnSaleProduct();
    }

    public ProductInfo getProductInfoById(int id) {
        return productInfoDao.getProductInfoById(id);
    }
}
