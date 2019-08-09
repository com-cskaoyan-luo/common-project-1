package com.cskaoyan.controller.unqualitycontroller;

import com.cskaoyan.service.UnqualifyApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/unqualify")
public class QualityController {
    @Autowired
    UnqualifyApply unqualifyApply;
    @RequestMapping("/find")
    public String unqualify() {
        return "unqualify_list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map queryAll() {
        List<com.cskaoyan.bean.UnqualifyApply> list = unqualifyApply.queryAll();
        Map map = new HashMap();
        map.put("total",list.size());
        map.put("rows",list);
        return  map;
    }


}

