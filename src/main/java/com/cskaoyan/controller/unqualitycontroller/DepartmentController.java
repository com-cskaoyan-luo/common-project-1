package com.cskaoyan.controller.unqualitycontroller;

import com.cskaoyan.bean.Department;
import com.cskaoyan.bean.DepartmentExample;
import com.cskaoyan.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentMapper departmentMapper;
    @RequestMapping("/get_data")
    @ResponseBody
    public List queryDepartment(){
        DepartmentExample departmentExample = new DepartmentExample();
        departmentExample.createCriteria().andDepartmentIdIsNotNull();
        List<Department> departments = departmentMapper.selectByExample(departmentExample);
        return departments;
    }
}
