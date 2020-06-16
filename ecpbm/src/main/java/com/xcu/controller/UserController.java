package com.xcu.controller;

import com.xcu.pojo.Pager;
import com.xcu.pojo.ProductInfo;
import com.xcu.pojo.UserInfo;
import com.xcu.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/userinfo")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/getValidUser")
    @ResponseBody
    public List<UserInfo> getValidUser(){
        List<UserInfo> uiList = userInfoService.getValidUser();
        UserInfo ui = new UserInfo();
        ui.setId(0);
        ui.setUserName("请选择...");
        uiList.add(0,ui);
        return uiList;
    }

    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> list(Integer page, Integer rows, UserInfo userInfo) {
        Pager pager = new Pager();
        pager.setCurPage(page);
        pager.setPerPageRows(rows);
        Map<String, Object> params = new HashMap<>();
        params.put("userInfo", userInfo);
        int totalCont = userInfoService.count(params);
        List<UserInfo> userInfoList = userInfoService.findUserInfo(userInfo, pager);
        Map<String, Object> result = new HashMap<>();
        result.put("total", totalCont);
        result.put("rows", userInfoList);
        return result;
    }

    @ResponseBody//
    @RequestMapping(value = "/setIsEnableUser", produces = "text/html;charset=UTF-8")
    public String setIsEnableUser(@RequestParam(value = "uids") String uids, @RequestParam(value = "flag")String flag) {
        String str = "";
        int delNums = 0;
        try {
            uids = uids.substring(0,uids.length()-1);
            String[] ids = uids.split(",");
            for (String id : ids) {
                System.out.println(id);
                System.out.println(flag);
                userInfoService.modifyStatus(id,Integer.parseInt(flag));
                delNums++;
            }

//            userInfoService.modifyStatus(uids.substring(0,uids.length()-1),Integer.parseInt(flag));
            str = "{\"success\":\"true\",\"message\":\"成功\",\"delNums\":" + delNums + "}";
        } catch (Exception e) {
            str = "{\"success\":\"false\",\"message\":\"失败\",\"delNums\":0}";
        }
        return str;
    }
}
