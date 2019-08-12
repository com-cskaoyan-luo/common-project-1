package com.cskaoyan.controller.unqualitycontroller;

import com.cskaoyan.bean.Process;
import com.cskaoyan.service.unqualifyapply.UnqualifyApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/process")
public class ProcessController {
    @Autowired
    UnqualifyApply unqualifyApply;
    @RequestMapping("/get/{id}")
    @ResponseBody
    public Process qurreyProcessById(@PathVariable("id")String id){
    return unqualifyApply.queryProcessById(id);
    }

    @RequestMapping("get_data")
    @ResponseBody
    public List<Process> getData(){
        return unqualifyApply.getProcessList();
    }
}
