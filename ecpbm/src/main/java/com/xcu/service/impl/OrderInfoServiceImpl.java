package com.xcu.service.impl;

import com.xcu.dao.OrderInfoDao;
import com.xcu.dao.ProductInfoDao;
import com.xcu.pojo.OrderDetail;
import com.xcu.pojo.OrderInfo;
import com.xcu.pojo.Pager;
import com.xcu.service.OrderInfoService;
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
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderInfoDao orderInfoDao;

    public List<OrderInfo> findOrderInfo(OrderInfo orderInfo, Pager pager) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderInfo",orderInfo);
        int recordCount = orderInfoDao.count(params);
        pager.setRowCount(recordCount);
        if(recordCount>0){
            params.put("pager",pager);
        }
        return orderInfoDao.selectByPage(params);
    }

    public int count(Map<String, Object> params) {
        return orderInfoDao.count(params);
    }

    public int addOrderInfo(OrderInfo oi) {
        return orderInfoDao.saveOrderInfo(oi);
    }

    public int addOrderDetail(OrderDetail od) {
        return orderInfoDao.saveOrderDetail(od);
    }

    public OrderInfo getOrderInfoById(int id) {
        return orderInfoDao.getOrderInfoById(id);
    }

    public List<OrderDetail> getOrderDetailByOid(int oid) {
        return orderInfoDao.getOrderDetailByOid(oid);
    }

    public int deleteOrder(int id) {
        int result = 1;
        try {
            orderInfoDao.deleteOrderDetail(id);
            orderInfoDao.deleteOrderInfo(id);
        }catch (Exception e){
            result=0;
        }
        return result;
    }
}
