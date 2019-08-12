package com.cskaoyan.controller.unqualitycontroller;

import com.cskaoyan.bean.FinalCountCheck;
import com.cskaoyan.mapper.FinalCountCheckMapper;
import com.cskaoyan.service.unqualifyapply.UnqualifyApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = {"f_count_check","fCountCheck"})
public class f_count_checkController {
    @Autowired
    UnqualifyApply unqualifyApply;
    @Autowired
    FinalCountCheckMapper finalCountCheckMapper;
    @RequestMapping("/find")
    public String f_count_check(){
        return "f_count_check_list";
    }
    @RequestMapping("/list")
    @ResponseBody
    public Map queryFinalCountCheck(){
        List<FinalCountCheck> list = unqualifyApply.queryAllCountCheck();
        HashMap<Object, Object> map = new HashMap<>();
        map.put("total",list.size());
        map.put("rows",list);
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
        for (String id : ids) {
            finalCountCheckMapper.deleteByPrimaryKey(id);
        }
        HashMap<Object, Object> map = new HashMap<>();
        map.put("data", null);
        map.put("msg", "ok");
        map.put("status", 200);
        return map;
    }

    @RequestMapping("/edit")
    public String edit(){
        return "f_count_check_edit";
    }
    @RequestMapping("/edit_judge")
    @ResponseBody
    public String edit_judge(){
        return "{}";
    }
    @RequestMapping(value = "update_all",method = RequestMethod.POST)
    @ResponseBody
    public Map updateAll(FinalCountCheck finalCountCheck){
        finalCountCheckMapper.updateByPrimaryKey(finalCountCheck);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("data", null);
        map.put("msg", "ok");
        map.put("status", 200);
        return map;
    }
    @RequestMapping("/add_judge")
    @ResponseBody
    public List unqualifyadd(){
        ArrayList<Object> list = new ArrayList<>();
        return list;
    }
    @RequestMapping("/add")
    public String un(){
        return "f_count_check_add";
    }
    @PostMapping("/insert")
    @ResponseBody
    public Map getUnqualifyApply(FinalCountCheck finalCountCheck){
        finalCountCheckMapper.insertSelective(finalCountCheck);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("data",null);
        map.put("msg","ok");
        map.put("status",200);
        return map;
    }
    @RequestMapping("search_fCountCheck_by_fCountCheckId")
    @ResponseBody
    public Map querySearch(String searchValue){
        List<com.cskaoyan.bean.FinalCountCheck> list=unqualifyApply.fccCheckIdSearch(searchValue);
        HashMap map = new HashMap();
        map.put("rows",list);
        map.put("total",list.size());
        return map;
    }
    @RequestMapping("search_fCountCheck_by_orderId")
    @ResponseBody
    public Map querySearchId(String searchValue){
        List<com.cskaoyan.bean.FinalCountCheck> list=unqualifyApply.fccOrderIdSearch(searchValue);
        HashMap map = new HashMap();
        map.put("rows",list);
        map.put("total",list.size());
        return map;
    }
}
