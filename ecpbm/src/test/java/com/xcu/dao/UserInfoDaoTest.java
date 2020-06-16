package com.xcu.dao;

import com.xcu.pojo.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class UserInfoDaoTest {
    @Autowired
    private UserInfoDao userInfoDao;
    @Test
    public void getValidUser() {
        List<UserInfo> validUser = userInfoDao.getValidUser();
        for (UserInfo userInfo : validUser) {
            System.out.println(userInfo);
        }
    }

    @Test
    public void getUserInfoById() {
        UserInfo userInfoById = userInfoDao.getUserInfoById(5);
        System.out.println(userInfoById);
    }

    @Test
    public void count() {
        Map<String,Object> map = new HashMap<String,Object>();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("tom");
        map.put("userInfo",userInfo);
        Integer count = userInfoDao.count(map);
        System.out.println(count);
    }
}
