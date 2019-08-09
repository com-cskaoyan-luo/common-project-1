package com.cskaoyan.controller.unqualitycontroller;

import com.cskaoyan.bean.FinalCountCheck;
import com.cskaoyan.service.UnqualifyApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("f_count_check")
public class f_count_checkController {
    @Autowired
    UnqualifyApply unqualifyApply;
    @RequestMapping("find")
    public String f_count_check(){
        return "f_count_check_list";
    }
    @RequestMapping("list")
    @ResponseBody
    public Map queryFinalCountCheck(){
        List<FinalCountCheck> list = unqualifyApply.queryAllCountCheck();
        HashMap<Object, Object> map = new HashMap<>();
        map.put("total",list.size());
        map.put("rows",list);
        return map;
    }
}
