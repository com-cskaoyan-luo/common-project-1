package com.cskaoyan.controller.unqualitycontroller;


import com.cskaoyan.bean.FinalCountCheck;
import com.cskaoyan.bean.FinalMeasuretCheck;
import com.cskaoyan.mapper.COrderMapper;
import com.cskaoyan.mapper.FinalMeasuretCheckMapper;
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
@RequestMapping("/measure")
public class MeasurementController {
    @Autowired
    UnqualifyApply unqualifyApply;

    @RequestMapping("/find")
    public String measure(){
        return "measurement_list";
    }
    @Autowired
    FinalMeasuretCheckMapper finalMeasuretCheckMapper;

    @RequestMapping("/list")
    @ResponseBody
    public Map queryAllFinalMeasureCheck(){
        List<FinalMeasuretCheck> finalMeasuretChecks = finalMeasuretCheckMapper.queryAllFinalMeasureCheck();
        HashMap<Object, Object> map = new HashMap<>();
        map.put("total",finalMeasuretChecks.size());
        map.put("rows",finalMeasuretChecks);
        return map;
    }
    @RequestMapping("add")

    public String add(){
        return "measurement_add";
    }
    @RequestMapping("/delete_batch")
    @ResponseBody
    public Map dele(String[] ids) {
        for (String id : ids) {
            finalMeasuretCheckMapper.deleteByPrimaryKey(id);
        }
        HashMap<Object, Object> map = new HashMap<>();
        map.put("data", null);
        map.put("msg", "ok");
        map.put("status", 200);
        return map;
    }
    @RequestMapping("/edit")
    public String edit(){
        return "measurement_edit";
    }

    @RequestMapping("/edit_judge")
    @ResponseBody
    public String edit_judge(){
        return "{}";
    }

    @RequestMapping(value = "update_all",method = RequestMethod.POST)
    @ResponseBody
    public Map updateAll(FinalMeasuretCheck finalMeasuretCheck){
       finalMeasuretCheckMapper.updateByPrimaryKey(finalMeasuretCheck);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("data", null);
        map.put("msg", "ok");
        map.put("status", 200);
        return map;
    }
    @PostMapping("/insert")
    @ResponseBody
    public Map getUnqualifyApply(FinalMeasuretCheck finalMeasuretCheck){
        finalMeasuretCheckMapper.insertSelective(finalMeasuretCheck);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("data",null);
        map.put("msg","ok");
        map.put("status",200);
        return map;
    }

    @RequestMapping("search_fMeasureCheck_by_fMeasureCheckId")
    @ResponseBody
    public Map querySearch(String searchValue){
        List<com.cskaoyan.bean.FinalMeasuretCheck> list=unqualifyApply.finalMeasureCheckSearch(searchValue);
        HashMap map = new HashMap();
        map.put("rows",list);
        map.put("total",list.size());
        return map;
    }
    @RequestMapping("search_fMeasureCheck_by_orderId")
    @ResponseBody
    public Map querySearchId(String searchValue){
        List<com.cskaoyan.bean.FinalMeasuretCheck> list=unqualifyApply.finalMeasureCheckSearchId(searchValue);
        HashMap map = new HashMap();
        map.put("rows",list);
        map.put("total",list.size());
        return map;
    }
}
