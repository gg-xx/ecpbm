package com.xcu.service.impl;

import com.xcu.dao.AdminInfoDao;
import com.xcu.pojo.AdminInfo;
import com.xcu.service.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
@Service("adminInfoService")
public class AdminInfoServiceImpl implements AdminInfoService {
    @Autowired
    private AdminInfoDao adminInfoDao;

    public AdminInfo login(AdminInfo adminInfo) {
        return adminInfoDao.selectByNameAndPwd(adminInfo);
    }

    public AdminInfo getAdminInfoAndFunctions(int id) {
        return adminInfoDao.selectById(id);
    }
}
