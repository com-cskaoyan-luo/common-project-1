package com.cskaoyan.controller;

import com.cskaoyan.bean.COrder;
import com.cskaoyan.bean.Manufacture;
import com.cskaoyan.bean.PageBean;
import com.cskaoyan.bean.Task;
import com.cskaoyan.service.TaskService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("task")
public class TaskController {
    @Autowired
    TaskService taskService;

    //根据ManufactureSn模糊查询
    @RequestMapping("search_task_by_taskManufactureSn")
    @ResponseBody
    public PageBean<Task> searchTaskByManufactureId(String searchValue, int page, int rows){
        List<Task> tasks = taskService.searchTaskByManufactureId(searchValue,page,rows);
        PageInfo<Task> pageInfo = new PageInfo<>(tasks);
        PageBean<Task> pageBean = new PageBean<>(tasks, pageInfo.getTotal());
        return pageBean;
    }

    //根据workId模糊查询
    @RequestMapping("search_task_by_taskWorkId")
    @ResponseBody
    public PageBean<Task> searchTaskByWorkId(String searchValue, int page, int rows){
        List<Task> tasks = taskService.searchTaskByWorkId(searchValue,page,rows);
        PageInfo<Task> pageInfo = new PageInfo<>(tasks);
        PageBean<Task> pageBean = new PageBean<>(tasks, pageInfo.getTotal());
        return pageBean;
    }

    //根据taskId模糊查询
    @RequestMapping("search_task_by_taskId")
    @ResponseBody
    public PageBean<Task> searchTaskById(String searchValue, int page, int rows){
        List<Task> tasks = taskService.searchTaskById(searchValue,page,rows);
        PageInfo<Task> pageInfo = new PageInfo<>(tasks);
        PageBean<Task> pageBean = new PageBean<>(tasks, pageInfo.getTotal());
        return pageBean;
    }

    //批量删除生产派工
    @RequestMapping("delete_judge")
    @ResponseBody
    public String deleteJudge(){
        return "null";
    }

    @RequestMapping("delete_batch")
    @ResponseBody
    public Map deleteBatch(String ids){
        //将id按逗号分隔成数组
        String[] split = ids.split(",");
        //将分割后的id存到List中
        List<String> strings = Arrays.asList(split);
        int i = taskService.deleteBatch(strings);
        Map<String, String> map = new HashMap<>();
        if(i == 1){
            map.put("status","200");}
        else {
            map.put("msg","删除失败！");}
        return map;
    }

    //跳转至作业编辑页面
    @RequestMapping("edit")
    public String edit(){
        return "task_edit";
    }

    //更新task
    @RequestMapping("edit_judge")
    @ResponseBody
    public String editJudge(){
        return "null";
    }

    //更新task
    @RequestMapping("update_all")
    @ResponseBody
    public Map updateAll(Task task){
        int i = taskService.updateSingleTask(task);
        Map<String, String> map = new HashMap<>();
        if(i == 1){
            map.put("status","200");}
        else {
            map.put("msg","提交失败！");}
        return map;
    }

    @RequestMapping("add_judge")
    @ResponseBody
    public String addJudge(){
        return "null";
    }

    //跳转至生产派工新增页面
    @RequestMapping("add")
    public String add(){
        return "task_add";
    }

    //新增生产派工
    @RequestMapping("insert")
    @ResponseBody
    public Map insert(Task task){
        int i = taskService.insertTask(task);
        Map<String, String> map = new HashMap<>();
        if(i == 1){
            map.put("status","200");}
        else {
            map.put("msg","插入失败！");}
        return map;
    }

    //点生产派工管理的页面显示
    @RequestMapping("find")
    public String find(){
        return "task_list";
    }

    @ResponseBody
    @RequestMapping("list")
    public PageBean<Task> queryTask(int page, int rows){
        List<Task> tasks = taskService.queryTask(page, rows);
        //查询到的数据给到PageInfo ，只需要把结果集给到该对象，获取分页信息
        // 就可以通过该对象get方法拿到总页数，总记录数，等等你想要的数据
        PageInfo<Task> pageInfo = new PageInfo<>(tasks);
        PageBean<Task> pageBean = new PageBean<>(tasks, pageInfo.getTotal());
        /*for (COrder cOrder : cOrders) {
            System.out.println(cOrder);
        }*/
        return pageBean;
    }
}
