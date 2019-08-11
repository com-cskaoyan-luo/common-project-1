package com.cskaoyan.controller;

import com.cskaoyan.bean.COrder;
import com.cskaoyan.bean.PageBean;
import com.cskaoyan.bean.Work;
import com.cskaoyan.service.WorkService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("work")
public class WorkController {
    @Autowired
    WorkService workService;

    //查询所以work信息进行回显
    @RequestMapping("get_data")
    @ResponseBody
    public List<Work> queryAllCustom(){
        List<Work> works = workService.queryAllWork();
        return works;
    }
    //点作业管理的页面显示
    @RequestMapping("find")
    public String find(){
        return "work_list";
    }
	@RequestMapping("get/{workId}")
    @ResponseBody
    public Work getProcess(@PathVariable("workId") String workId){
        return workService.getWorkById(workId);
    }
    @ResponseBody
    @RequestMapping("list")
    public PageBean<Work> queryWork(int page, int rows){
        List<Work> works = workService.queryWork(page, rows);
        //查询到的数据给到PageInfo ，只需要把结果集给到该对象，获取分页信息
        // 就可以通过该对象get方法拿到总页数，总记录数，等等你想要的数据
        PageInfo<Work> pageInfo = new PageInfo<>(works);
        PageBean<Work> pageBean = new PageBean<>(works, pageInfo.getTotal());
        /*for (COrder cOrder : cOrders) {
            System.out.println(cOrder);
        }*/
        return pageBean;
    }
}
