package com.cskaoyan.controller.unqualitycontroller;

import com.cskaoyan.bean.FinalCountCheck;
import com.cskaoyan.bean.ProcessMeasureCheck;
import com.cskaoyan.mapper.ProcessCountCheckMapper;
import com.cskaoyan.mapper.ProcessMeasureCheckMapper;
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
@RequestMapping(value = {"/p_measure_check","pMeasureCheck"})
public class P_Measure_CheckController {
    @Autowired
    UnqualifyApply unqualifyApply;
    @Autowired
    ProcessCountCheckMapper processCountCheckMapper;
    @Autowired
    ProcessMeasureCheckMapper processMeasureCheckMapper;
    @RequestMapping("/find")
    public String p_Measure_CheckController(){
        return "p_measure_check_list";
    }
    @RequestMapping("list")
    @ResponseBody
    public Map queryAllPMeasureCheck(){
        HashMap map = new HashMap();
        List<ProcessMeasureCheck> list = unqualifyApply.queryAllPMeasureCheck();
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
        return "p_measure_check_edit";
    }
    @RequestMapping("/edit_judge")
    @ResponseBody
    public String edit_judge(){
        return "{}";
    }
    @RequestMapping(value = "update_all",method = RequestMethod.POST)
    @ResponseBody
    public Map updateAll(ProcessMeasureCheck processMeasureCheck){
        processMeasureCheckMapper.updateByPrimaryKey(processMeasureCheck);
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
        return "p_measure_check_add";
    }
    @PostMapping("/insert")
    @ResponseBody
    public Map getUnqualifyApply(ProcessMeasureCheck processMeasureCheck){
        processMeasureCheckMapper.insertSelective(processMeasureCheck);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("data",null);
        map.put("msg","ok");
        map.put("status",200);
        return map;
    }

    @RequestMapping("search_pMeasureCheck_by_pMeasureCheckId")
    @ResponseBody
    public Map querySearchId(String searchValue){
        List<com.cskaoyan.bean.ProcessMeasureCheck> list=unqualifyApply.pMeasureCheckIdSearch(searchValue);
        HashMap map = new HashMap();
        map.put("rows",list);
        map.put("total",list.size());
        return map;
    }

}
