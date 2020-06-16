package com.xcu.controller;

import com.xcu.pojo.Pager;
import com.xcu.pojo.Type;
import com.xcu.pojo.UserInfo;
import com.xcu.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @RequestMapping("/getType/{flag}")
    @ResponseBody
    public List<Type> getType(@PathVariable("flag") Integer flag){
        List<Type> types = typeService.getAll();
        if(flag==1){
            Type t = new Type();
            t.setId(0);
            t.setName("请选择...");
            types.add(0,t);
        }
        return types;
    }
    @ResponseBody
    @RequestMapping("/list")
    public List<Type> list(ModelAndView model) {
        List<Type> typeList = typeService.getAll();
        //model.addObject("typeList",typeList);
        return typeList;
    }
}
