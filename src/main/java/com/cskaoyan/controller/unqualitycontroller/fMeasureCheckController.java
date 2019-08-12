package com.cskaoyan.controller.unqualitycontroller;

import com.cskaoyan.mapper.COrderMapper;
import com.cskaoyan.service.unqualifyapply.UnqualifyApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("fMeasureCheck")
public class fMeasureCheckController {
    @Autowired
    UnqualifyApply unqualifyApply;
    @Autowired
    COrderMapper cOrderMapper;
    @RequestMapping("/add_judge")
    @ResponseBody
    public List unqualifyadd(){
        ArrayList<Object> list = new ArrayList<>();
        return list;
    }
    @RequestMapping("/delete_judge")
    @ResponseBody
    public String delete(){
        return "{}";
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


}
