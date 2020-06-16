package com.xcu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.xcu.pojo.OrderDetail;
import com.xcu.pojo.OrderInfo;
import com.xcu.pojo.Pager;
import com.xcu.pojo.ProductInfo;
import com.xcu.service.OrderInfoService;
import com.xcu.service.ProductInfoService;
import com.xcu.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orderinfo")
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private ProductInfoService productInfoService;

    @RequestMapping("/commitOrder")
    @ResponseBody
    public String commitOrder(String inserted,String orderinfo){
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
            OrderInfo oi = mapper.readValue(orderinfo,OrderInfo[].class)[0];
            orderInfoService.addOrderInfo(oi);
            List<OrderDetail> odList = mapper.readValue(inserted,new TypeReference<ArrayList<OrderDetail>>(){});
            for (OrderDetail od : odList) {
                od.setOid(oi.getId());
                orderInfoService.addOrderDetail(od);
            }
            return "success";
        }catch (Exception e){
            return "false";
        }
    }

    @ResponseBody
    @RequestMapping("/list")
    public Map<String,Object> list(Integer page, Integer rows, OrderInfo orderInfo){
        Pager pager = new Pager();
        pager.setCurPage(page);
        pager.setPerPageRows(rows);
        Map<String, Object> params = new HashMap<>();
        params.put("orderInfo", orderInfo);
        int totalCont = orderInfoService.count(params);
        List<OrderInfo> orderInfos = orderInfoService.findOrderInfo(orderInfo, pager);
        Map<String, Object> result = new HashMap<>();
        result.put("total", totalCont);
        result.put("rows", orderInfos);
        return result;
    }
    @RequestMapping("/deleteOrder")
    @ResponseBody
    public String deleteOrder(String oids){
        String str="";
        int delNums = 0;
        try{
            oids = oids.substring(0,oids.length()-1);
            String[] ids = oids.split(",");
            for (String id : ids) {
                orderInfoService.deleteOrder(Integer.parseInt(id));
                delNums++;
            }
            str = "{\"success\":\"true\",\"message\":\"删除成功\",\"delNums\":" + delNums + "}";
        }catch (Exception e){
            str = "{\"success\":\"false\",\"message\":\"删除失败\",\"delNums\":0}";
        }
        return str;
    }
    @RequestMapping("/getOrderInfo")
    public String getOrderInfo(String oid, Model model){
        OrderInfo oi = orderInfoService.getOrderInfoById(Integer.parseInt(oid));
        model.addAttribute("oi",oi);
        return "orderdetail";
    }

    @RequestMapping("/getOrderDetails")
    @ResponseBody
    public List<OrderDetail> getOrderDetails(String oid){
        List<OrderDetail> ods = orderInfoService.getOrderDetailByOid(Integer.parseInt(oid));
        for (OrderDetail od : ods) {
            od.setPrice(od.getPi().getPrice());
            od.setTotalprice(od.getPi().getPrice()*od.getNum());
        }
        return ods;
    }
}
