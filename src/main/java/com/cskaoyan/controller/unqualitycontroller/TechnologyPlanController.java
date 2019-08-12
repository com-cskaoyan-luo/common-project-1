package com.cskaoyan.controller.unqualitycontroller;

import com.cskaoyan.bean.TechnologyPlan;

import com.cskaoyan.service.unqualifyapply.UnqualifyApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/technologyPlan")
public class TechnologyPlanController {
    @Autowired
    UnqualifyApply unqualifyApply;
    @RequestMapping("get_data")
    @ResponseBody
    public List<TechnologyPlan> queryTechologyPlan(){
       return unqualifyApply.queryTechologyPlan();
    }
}
