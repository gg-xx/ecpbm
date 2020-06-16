package com.xcu.service;

import com.xcu.pojo.Pager;
import com.xcu.pojo.ProductInfo;
import com.xcu.service.impl.ProductInfoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class ProductInfoServiceTest {
    @Autowired
    private ProductInfoServiceImpl productInfoService;
    @Test
    public void getProductInfoById() {
        ProductInfo productInfoById = productInfoService.getProductInfoById(1);
        System.out.println(productInfoById);
    }
    @Test
    public void findProductInfo() {
        Map<String, Object> params = new HashMap<String, Object>();
        ProductInfo productInfo = new ProductInfo();
        Pager pager = new Pager();
        int recordCount = 5;
        pager.setCurPage(2);
        pager.setPerPageRows(5);
        pager.setRowCount(recordCount);
        if(recordCount>0){
            params.put("pager",pager);
        }
        List<ProductInfo> productInfos = productInfoService.findProductInfo(productInfo,pager);
        for (ProductInfo info : productInfos) {
            System.out.println(info);
        }
    }
    @Test
    public void addProductInfo() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setName("123");
        productInfo.setBrand("联想");
        productInfo.setNum(100);
        productInfoService.addProductInfo(productInfo);
    }

    @Test
    public void modifyStatus(){

        String ids="28,29,30";
//        String sql = "update product_info set status = 0 where id in (#{ids})";
//
//        String id2="";
//        String[] split = ids.split(",");
//        for (String s : split) {
//            id2 += "'"+s+"'"+",";
//        }
//        id2 = id2.substring(0,id2.length()-1);
//        System.out.println(id2);
        productInfoService.modifyStatus("(28,29,30)",0);
    }
}
