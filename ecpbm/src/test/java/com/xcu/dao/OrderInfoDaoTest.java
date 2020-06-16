package com.xcu.dao;

import com.xcu.pojo.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class OrderInfoDaoTest {
    @Autowired
    private OrderInfoDao orderInfoDao;

    @Test
    public void getOrderDetailByOid() {
        List<OrderDetail> orderDetailByOid = orderInfoDao.getOrderDetailByOid(1);
        for (OrderDetail orderDetail : orderDetailByOid) {
            System.out.println(orderDetail);
        }
    }
}
