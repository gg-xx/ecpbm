package com.xcu.service;

import com.xcu.pojo.AdminInfo;

public interface AdminInfoService {
    //登录验证
    AdminInfo login(AdminInfo adminInfo);

    //根据管理员编号获取管理员对象及关联的功能权限
    AdminInfo getAdminInfoAndFunctions(int id);
}