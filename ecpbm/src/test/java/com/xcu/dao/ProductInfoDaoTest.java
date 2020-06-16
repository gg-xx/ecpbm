package com.xcu.dao;
import com.xcu.pojo.Pager;
import com.xcu.pojo.ProductInfo;
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
public class ProductInfoDaoTest {
    @Autowired
    private ProductInfoDao productInfoDao;
    @Test
    public void selectByPage() {
        Map<String, Object> params = new HashMap<String, Object>();
        Pager pager = new Pager();
        pager.setCurPage(1);
        pager.setPerPageRows(5);
        params.put("pager",pager);
        List<ProductInfo> productInfoList = productInfoDao.selectByPage(params);
        for (ProductInfo info : productInfoList) {
            System.out.println(info);
        }
    }

}
