package com.cskaoyan.controller;


import com.alibaba.druid.sql.visitor.functions.Concat;
import com.cskaoyan.service.unqualifyapply.UnqualifyApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.alibaba.druid.sql.parser.Token.CONCAT;

@Controller
@RequestMapping("/unqualify")
public class QualityController {
    @Autowired
    UnqualifyApply unqualifyApply;
    @RequestMapping("/find")
    public String unqualify() {
        return "unqualify_list";
    }

    @RequestMapping("/add_judge")
    @ResponseBody
    public List unqualifyadd(){
        ArrayList<Object> list = new ArrayList<>();
        return list;
    }
    @RequestMapping("/add")
    public String un(){
        return "unqualify_add";
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
    @PostMapping("/insert")
    @ResponseBody
    public Map getUnqualifyApply(com.cskaoyan.bean.UnqualifyApply unqualifyApplyBean){
        unqualifyApply.insertUnqualifyApplyBean(unqualifyApplyBean);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("data",null);
        map.put("msg","ok");
        map.put("status",200);
        return map;
    }
    @RequestMapping("/delete_judge")
    @ResponseBody
    public String delete(){
        return "{}";
    }
    @RequestMapping("/delete_batch")
    @ResponseBody
    public Map dele(String[] ids) {
        unqualifyApply.delete(ids);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("data", null);
        map.put("msg", "ok");
        map.put("status", 200);
        return map;
    }
    @RequestMapping("/edit")
    public String edit(){
        return "unqualify_edit";
    }
    @RequestMapping("/edit_judge")
    @ResponseBody
    public String edit_judge(){
        return "{}";
    }
    @RequestMapping(value = "update_all",method = RequestMethod.POST)
    @ResponseBody
    public Map updateAll(com.cskaoyan.bean.UnqualifyApply unqualify){
        unqualifyApply.updateAll(unqualify);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("data", null);
        map.put("msg", "ok");
        map.put("status", 200);
        return map;
    }
    @RequestMapping("search_unqualify_by_productName")
    @ResponseBody
    public Map querySearch(String searchValue){
       //String s="%"+searchValue+"%";
        List<com.cskaoyan.bean.UnqualifyApply> list=unqualifyApply.unqualifySearch(searchValue);
        HashMap map = new HashMap();
        map.put("rows",list);
        map.put("total",list.size());
        return map;
    }
    @RequestMapping("search_unqualify_by_unqualifyId")
    @ResponseBody
    public Map querySearchId(String searchValue){
        List<com.cskaoyan.bean.UnqualifyApply> list=unqualifyApply.unqualifySearchId(searchValue);
        HashMap map = new HashMap();
        map.put("rows",list);
        map.put("total",list.size());
        return map;
    }

}

