package com.xcu.provider;

import com.xcu.pojo.ProductInfo;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class ProductInfoDynaSqlProvider {
    public String selectWithParam(Map<String,Object> params){
        String sql = new SQL(){
            {
                SELECT("*");
                FROM("product_info");
                if(params.get("productInfo") !=null){
                    ProductInfo productInfo = (ProductInfo)params.get("productInfo");
                    if(productInfo.getCode()!=null&&!"".equals(productInfo.getCode())){
                        WHERE(" code = #{productInfo.code} ");
                    }
                    if(productInfo.getName()!=null&&!"".equals(productInfo.getName())){
                        WHERE(" name like concat ('%',#{productInfo.name},'%') ");
                    }
                    if(productInfo.getBrand()!=null&&!"".equals(productInfo.getBrand())){
                        WHERE(" brand like concat ('%',#{productInfo.brand},'%') ");
                    }
                    if(productInfo.getType()!=null&&!"".equals(productInfo.getType().getName())){
                        WHERE(" tid = #{productInfo.type.id} ");
                    }
                    if(productInfo.getPriceFrom() > 0){
                        WHERE(" price > #{productInfo.priceFrom} ");
                    }
                    if(productInfo.getPriceTo() > 0){
                        WHERE(" price <= #{productInfo.priceTo} ");
                    }
                }
            }
        }.toString();
        if(params.get("pager")!=null){
            sql +=" limit #{pager.firstLimitParam},#{pager.perPageRows} ";
        }
        return sql;
    }
    public String count(Map<String,Object> params){
        return new SQL(){
            {
                SELECT("count(*)");
                FROM("product_info");
                if(params.get("productInfo") !=null){
                    ProductInfo productInfo = (ProductInfo)params.get("productInfo");
                    if(productInfo.getCode()!=null && !"".equals(productInfo.getCode())){
                        WHERE(" code = #{productInfo.code} ");
                    }
                    if(productInfo.getName()!=null && !"".equals(productInfo.getName())){
                        WHERE(" name like concat ('%',#{productInfo.name},'%') ");
                    }
                    if(productInfo.getBrand()!=null && !"".equals(productInfo.getBrand())){
                        WHERE(" brand like concat ('%',#{productInfo.brand},'%') ");
                    }
                    if(productInfo.getType()!=null && !"".equals(productInfo.getType())){
                        WHERE(" tid = #{productInfo.type.id} ");
                    }
                    if(productInfo.getPriceFrom() > 0){
                        WHERE(" price > #{productInfo.priceFrom} ");
                    }
                    if(productInfo.getPriceTo() > 0){
                        WHERE(" price <= #{productInfo.priceTo} ");
                    }
                }
            }
        }.toString();
    }
}
