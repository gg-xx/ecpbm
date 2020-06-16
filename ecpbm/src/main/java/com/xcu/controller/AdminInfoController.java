package com.xcu.controller;

import com.xcu.pojo.AdminInfo;
import com.xcu.pojo.Functions;
import com.xcu.pojo.TreeNode;
import com.xcu.service.impl.AdminInfoServiceImpl;
import com.xcu.util.JsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SessionAttributes(value = {"admin"})
@Controller
@RequestMapping("/admin")
public class AdminInfoController {

    @Autowired
    private AdminInfoServiceImpl adminInfoService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public String login(AdminInfo adminInfo, ModelMap model){
        AdminInfo adminInfo1 = adminInfoService.login(adminInfo);
        if(adminInfo1!=null&&adminInfo1.getName()!=null){
            if(adminInfoService.getAdminInfoAndFunctions(adminInfo1.getId()).getFs().size()>0){
                model.put("admin",adminInfo1);
                System.out.println("chenggong");
                return "{\"success\":\"true\",\"message\":\"登录成功\"}";
            }else{
                return "{\"success\":\"false\",\"message\":\"您没有权限，请联系超级管理员\"}";
            }
        }else {
            return "{\"success\":\"true\",\"message\":\"登录失败\"}";
        }
    }

    @RequestMapping("/getTree")
    @ResponseBody
    public List<TreeNode> getTree(@RequestParam(value = "adminid") String adminid){
        AdminInfo admininfo = adminInfoService.getAdminInfoAndFunctions(Integer.parseInt(adminid));
        List<Functions> functionsList = admininfo.getFs();
        List<TreeNode> nodes = new ArrayList<>();
        Collections.sort(functionsList);
        for (Functions functions : functionsList) {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(functions.getId());
            treeNode.setFid(functions.getParentid());
            treeNode.setText(functions.getName());
            nodes.add(treeNode);
        }
        List<TreeNode> treeNodes = JsonFactory.buildtree(nodes, 0);
        return treeNodes;
    }
    @RequestMapping("/logout")
    @ResponseBody
    public String logout(SessionStatus status){
        status.setComplete();
        return "{\"success\":\"true\",\"message\":\"注销成功\"}";
    }
}
