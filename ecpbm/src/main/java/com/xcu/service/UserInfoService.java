package com.xcu.service;

import com.xcu.pojo.Pager;
import com.xcu.pojo.UserInfo;
import org.apache.ibatis.annotations.Insert;

import java.util.List;
import java.util.Map;

public interface UserInfoService {
    //获取合法用户
    List<UserInfo> getValidUser();

    //根据客户编号查询客户
    UserInfo getUserInfoById(int id);

    //f分页显示客户
    List<UserInfo> findUserInfo(UserInfo userInfo, Pager pager);

    //客户计数
    Integer count(Map<String,Object> params);

    //修改指定用户编号的用户状态
    void modifyStatus(String ids,int flag);
}
