package com.xcu.dao;

import com.xcu.pojo.ProductInfo;
import com.xcu.provider.ProductInfoDynaSqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface ProductInfoDao {
    @SelectProvider(type = ProductInfoDynaSqlProvider.class,method = "selectWithParam")
    @Results({
            @Result(id =true,column = "id",property = "id"),
            @Result(column = "code",property = "code"),
            @Result(column = "name",property = "name"),
            @Result(column = "brand",property = "brand"),
            @Result(column = "pic",property = "pic"),
            @Result(column = "num",property = "num"),
            @Result(column = "price",property = "price"),
            @Result(column = "intro",property = "intro"),
            @Result(column = "status",property = "status"),
            @Result(column = "tid",property = "type",one = @One(select = "com.xcu.dao.TypeDao.selectById",fetchType = FetchType.EAGER))    })
    List<ProductInfo> selectByPage(Map<String,Object> params);

    @SelectProvider(type = ProductInfoDynaSqlProvider.class,method = "count")
    int count(Map<String,Object> params);

    @Insert("insert into product_info(code,name,tid,brand,pic,num,price,intro,status)" +
            " values(#{code},#{name},#{type.id},#{brand},#{pic},#{num},#{price},#{intro},#{status})")
    void save(ProductInfo pi);

    @Update("update product_info set code=#{code},name=#{name},tid=#{type.id},brand=#{brand}," +
            "pic=#{pic},num=#{num},price=#{price},intro=#{intro},status=#{status} where id=#{id} ")
    void edit(ProductInfo pi);

    @Update("update product_info set status = #{flag} where id in (#{ids})")
    void updateState(@Param("ids") String ids, @Param("flag") int flag);

    @Select("select * from product_info where status=1")
    List<ProductInfo> getOnSaleProduct();

    @Select("select * from product_info where id=#{id}")
    ProductInfo getProductInfoById(int id);
}
