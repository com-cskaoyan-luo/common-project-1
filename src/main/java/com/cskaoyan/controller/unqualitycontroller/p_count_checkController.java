package com.cskaoyan.controller.unqualitycontroller;

import com.cskaoyan.bean.FinalCountCheck;
import com.cskaoyan.bean.ProcessCountCheck;
import com.cskaoyan.mapper.ProcessCountCheckMapper;
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
@RequestMapping(value = {"p_count_check","pCountCheck"})
public class p_count_checkController {
    @Autowired
    UnqualifyApply unqualifyApply;
    @Autowired
    ProcessCountCheckMapper processCountCheckMapper;
    @RequestMapping("find")
    public String pcountcheckController(){
        return "p_count_check_list";
    }
    @RequestMapping("/list")
    @ResponseBody
    public Map queryAllPCountCheck(){
        List<ProcessCountCheck> list = unqualifyApply.queryAllPCountCheck();
        HashMap map = new HashMap();
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
    public Map delet(String[] ids) {
        for (String id : ids) {
            processCountCheckMapper.deleteByPrimaryKey(id);
        }
        HashMap<Object, Object> map = new HashMap<>();
        map.put("data", null);
        map.put("msg", "ok");
        map.put("status", 200);
        return map;
    }

    @RequestMapping("/edit")
    public String edit(){
        return "p_count_check_edit";
    }
    @RequestMapping("/edit_judge")
    @ResponseBody
    public String edit_judge(){
        return "{}";
    }
    @RequestMapping(value = "update_all",method = RequestMethod.POST)
    @ResponseBody
    public Map updateAll(ProcessCountCheck processCountCheck){
        processCountCheckMapper.updateByPrimaryKey(processCountCheck);
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
        return "p_count_check_add";
    }
    @PostMapping("/insert")
    @ResponseBody
    public Map getUnqualifyApply(ProcessCountCheck processCountCheck){
        processCountCheckMapper.insertSelective(processCountCheck);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("data",null);
        map.put("msg","ok");
        map.put("status",200);
        return map;
    }

    @RequestMapping("search_pCountCheck_by_pCountCheckId")
    @ResponseBody
    public Map querySearchId(String searchValue){
        List<com.cskaoyan.bean.ProcessCountCheck> list=unqualifyApply.processCountCheckIdSearch(searchValue);
        HashMap map = new HashMap();
        map.put("rows",list);
        map.put("total",list.size());
        return map;
    }
}
