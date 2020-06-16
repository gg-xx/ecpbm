package com.xcu.service;

import com.xcu.pojo.OrderDetail;
import com.xcu.pojo.OrderInfo;
import com.xcu.pojo.Pager;

import java.util.List;
import java.util.Map;

public interface OrderInfoService {
    //分页显示订单
    List<OrderInfo> findOrderInfo(OrderInfo orderInfo, Pager pager);

    //根据条件查询订单数
    int count(Map<String,Object> params);

    //保存订单主表信息
    int addOrderInfo(OrderInfo oi);

    //保存订单明细
    int addOrderDetail(OrderDetail od);

    //根据订单编号获取订单对象
    OrderInfo getOrderInfoById(int id);

    //根据订单编号获取订单明细
    List<OrderDetail> getOrderDetailByOid(int oid);

    //根据订单编号删除订单
    int deleteOrder(int id);
}
