package com.cskaoyan.controller.unqualitycontroller;


import com.cskaoyan.bean.FinalMeasuretCheck;
import com.cskaoyan.mapper.FinalMeasuretCheckMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/measure")
public class MeasurementController {

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

}
