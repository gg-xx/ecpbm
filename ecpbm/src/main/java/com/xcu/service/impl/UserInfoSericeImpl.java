package com.xcu.service.impl;

import com.xcu.dao.UserInfoDao;
import com.xcu.pojo.Pager;
import com.xcu.pojo.UserInfo;
import com.xcu.service.UserInfoService;
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
public class UserInfoSericeImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    public List<UserInfo> getValidUser() {
        return userInfoDao.getValidUser();
    }

    public UserInfo getUserInfoById(int id) {
        return userInfoDao.getUserInfoById(id);
    }

    public List<UserInfo> findUserInfo(UserInfo userInfo, Pager pager) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userInfo",userInfo);
        Integer recordCount = userInfoDao.count(params);
        pager.setRowCount(recordCount);
        if(recordCount>0){
            params.put("pager",pager);
        }
        return userInfoDao.selectByPage(params);
    }

    public Integer count(Map<String, Object> params) {
        return userInfoDao.count(params);
    }

    public void modifyStatus(String ids, int flag) {
        userInfoDao.updateState(ids,flag);
    }
}
