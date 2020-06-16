package com.xcu.controller;

import com.xcu.pojo.Pager;
import com.xcu.pojo.ProductInfo;
import com.xcu.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/productinfo")
public class ProductInfoController {

    @Autowired
    private ProductInfoService productInfoService;

    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> list(Integer page, Integer rows, ProductInfo productInfo) {
        Pager pager = new Pager();
        pager.setCurPage(page);
        pager.setPerPageRows(rows);
        Map<String, Object> params = new HashMap<>();
        params.put("productInfo", productInfo);
        int totalCont = productInfoService.count(params);
        List<ProductInfo> productInfoList = productInfoService.findProductInfo(productInfo, pager);
        Map<String, Object> result = new HashMap<>();
        result.put("total", totalCont);
        result.put("rows", productInfoList);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/addProduct", produces = "text/html;charset=UTF-8")
    public String addProduct(MultipartFile file, ProductInfo productInfo,
                             HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("/product_images/");
        File file1 = new File(path);
        if (!file1.exists())
            file1.mkdirs();
        String fileName = file.getOriginalFilename();
        try {
            file.transferTo(new File(path, fileName));
            productInfo.setPic(fileName);
            productInfoService.addProductInfo(productInfo);
            return "{\"success\":\"true\",\"message\":\"success\"}";
        } catch (Exception e) {
            return "{\"success\":\"true\",\"message\":\"faliure\"}";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteProduct", produces = "text/html;charset=UTF-8")
    public String deleteProduct(@RequestParam(value = "id") String id, @RequestParam(value = "flag") String flag) {
        String str = "";
        try {
            int delNums = 0;
            int num = id.split(",").length;
            String[] strs = new String[num];
            strs = id.split(",");
            for (String s : strs) {
                productInfoService.modifyStatus(s, Integer.parseInt(flag));
                delNums++;
            }
//            productInfoService.modifyStatus( id.substring(0,id.length()-1) ,Integer.parseInt(flag));
//            System.out.println(id);
//            int delNums = id.length();
            str = "{\"success\":\"true\",\"message\":\"下架成功\",\"delNums\":" + delNums + "}";
        } catch (Exception e) {
            str = "{\"success\":\"false\",\"message\":\"下架失败\",\"delNums\":0}";
        }
        return str;
    }

    @ResponseBody
    @RequestMapping(value = "/updateProduct", produces = "text/html;charset=UTF-8")
    public String updateProduct(MultipartFile file, ProductInfo productInfo,
                                HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("/product_images/");
        File file1 = new File(path);
        if (!file1.exists())
            file1.mkdirs();
        try {
            String fileName = file.getOriginalFilename();
            file.transferTo(new File(path, fileName));
            productInfo.setPic(fileName);
            productInfoService.modifyProductInfo(productInfo);
            return "{\"success\":\"true\",\"message\":\"修改成功\"}";
        } catch (Exception e) {
            return "{\"success\":\"true\",\"message\":\"修改失败\"}";
        }
    }
    @ResponseBody
    @RequestMapping("/getOnSaleProduct")
    public List<ProductInfo> getOnSaleProduct(){
        List<ProductInfo> plist = productInfoService.getOnSaleProduct();
        return plist;
    }
    @ResponseBody
    @RequestMapping("/getPriceById")
    public String getPriceById(@RequestParam(value = "pid") String pid){
        if(pid!=null && !"".equals(pid)){
            ProductInfo pi = productInfoService.getProductInfoById(Integer.parseInt(pid));
            return pi.getPrice()+"";
        }else{
            return "";
        }
    }
}