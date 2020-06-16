package com.xcu.dao;

import com.xcu.pojo.OrderDetail;
import com.xcu.pojo.OrderInfo;
import com.xcu.provider.OrderInfoDynaSqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderInfoDao {
    //分页获取订单
    @SelectProvider(type = OrderInfoDynaSqlProvider.class,method = "selectWithParam")
    @Results({
            @Result(column = "uid",property = "ui",one =
            @One(select = "com.xcu.dao.UserInfoDao.getUserInfoById",fetchType = FetchType.EAGER))})
    List<OrderInfo> selectByPage(Map<String,Object> params);
    //根据条件查询订单数
    @SelectProvider(type = OrderInfoDynaSqlProvider.class,method = "count")
    int count(Map<String,Object> params);

    //保存订单主表信息
    @Insert("insert into order_info(uid,status,ordertime,orderprice) values(#{uid}," +
            "#{status},#{ordertime},#{orderprice})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int saveOrderInfo(OrderInfo oi);

    //保存订单明细
    @Insert("insert into order_detail(oid,pid,num) values(#{oid},#{pid},#{num})")
    int saveOrderDetail(OrderDetail od);

    //根据订单编号获取订单对象
    @Select("select * from order_info where id=#{id}")
    @Results({
            @Result(column = "uid",property = "ui",one = @One(select = "com.xcu.dao.UserInfoDao.getUserInfoById",fetchType = FetchType.EAGER))})
    OrderInfo getOrderInfoById(int id);

    //根据订单编号获取订单明细
    @Results({
            @Result(column = "pid",property = "pi",one = @One(select = "com.xcu.dao.ProductInfoDao.getProductInfoById",fetchType = FetchType.EAGER))})
    @Select("select * from order_detail where oid=#{oid}")
    List<OrderDetail> getOrderDetailByOid(int oid);

    //根据订单编号删除订单主表记录
    @Delete("delete from order_info where id=#{id}")
    int deleteOrderInfo(int id);

    //根据订单明细删除订单明细记录
    @Delete("delete from order_detail where oid=#{id}")
    int deleteOrderDetail(int id);
}
