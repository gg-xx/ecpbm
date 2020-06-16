package com.xcu.service;
import com.xcu.pojo.AdminInfo;
import com.xcu.service.impl.AdminInfoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class AdminInfoServiceTest {

    @Autowired
    private AdminInfoServiceImpl adminInfoService;
    @Test
    public void login() {
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setName("admin");
        adminInfo.setPwd("123456");
        AdminInfo login = adminInfoService.login(adminInfo);
        System.out.println(login);
    }
    @Test
    public void getAdminInfoAndFunctionsTest(){
        AdminInfo adminInfoAndFunctions = adminInfoService.getAdminInfoAndFunctions(1);
        System.out.println(adminInfoAndFunctions);
    }
}
