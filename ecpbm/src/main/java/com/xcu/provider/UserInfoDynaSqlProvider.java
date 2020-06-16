package com.xcu.provider;

import com.xcu.pojo.UserInfo;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class UserInfoDynaSqlProvider {
    public String selectWithParam(Map<String,Object> params){
        String sql = new SQL(){
            {
                SELECT("*");
                FROM("user_info");
                if(params.get("userInfo")!=null){
                    UserInfo userInfo = (UserInfo) params.get("userInfo");
                    if(userInfo.getUserName()!=null&&!userInfo.getUserName().equals("")){
                        WHERE(" userName like concat ('%',#{userInfo.userName},'%')");
                    }
                }
            }
        }.toString();
        if(params.get("pager")!=null){
            sql+=" limit #{pager.firstLimitParam},#{pager.perPageRows} ";
        }
        return sql;
    }
    public String count(Map<String ,Object> params){
        return new SQL(){
            {
                SELECT("count(*)");
                FROM("user_info");
                if(params.get("userInfo")!=null){
                    UserInfo userInfo = (UserInfo) params.get("userInfo");
                    if(userInfo.getUserName()!=null&&!userInfo.getUserName().equals("")){
                        WHERE(" userName Like concat ('%',#{userInfo.userName},'%')");
                    }
                }
            }
        }.toString();
    }
}
