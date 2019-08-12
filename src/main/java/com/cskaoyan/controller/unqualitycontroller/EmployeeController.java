package com.cskaoyan.controller.unqualitycontroller;

import com.cskaoyan.bean.Employee;

import com.cskaoyan.service.unqualifyapply.UnqualifyApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    UnqualifyApply unqualifyApply;
    @RequestMapping("/get/{id}")
    @ResponseBody
    public Employee queryEmployee(@PathVariable("id")String id){
        return unqualifyApply.queryemployee(id);
    }
    @PostMapping("/get_data")
    @ResponseBody
    public List<Employee> queryListEmployee(){
        List<Employee> list=unqualifyApply.queryListEmployee();
        return list;
    }
}
