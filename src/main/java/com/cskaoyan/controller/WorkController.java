package com.cskaoyan.controller;

import com.cskaoyan.bean.COrder;
import com.cskaoyan.bean.PageBean;
import com.cskaoyan.bean.Product;
import com.cskaoyan.bean.Work;
import com.cskaoyan.service.WorkService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("work")
public class WorkController {
    @Autowired
    WorkService workService;


    //根据workProcess模糊查询
    @RequestMapping("search_work_by_workProcess")
    @ResponseBody
    public PageBean<Work> searchWorkByProcess(String searchValue,int page,int rows){
        List<Work> works = workService.searchWorkByProcess(searchValue,page,rows);
        PageInfo<Work> pageInfo = new PageInfo<>(works);
        PageBean<Work> pageBean = new PageBean<>(works, pageInfo.getTotal());
        return pageBean;
    }
    //根据workDevice模糊查询
    @RequestMapping("search_work_by_workDevice")
    @ResponseBody
    public PageBean<Work> searchWorkByDevice(String searchValue,int page,int rows){
        List<Work> works = workService.searchWorkByDevice(searchValue,page,rows);
        PageInfo<Work> pageInfo = new PageInfo<>(works);
        PageBean<Work> pageBean = new PageBean<>(works, pageInfo.getTotal());
        return pageBean;
    }

    //根据workProduct模糊查询
    @RequestMapping("search_work_by_workProduct")
    @ResponseBody
    public PageBean<Work> searchWorkByProduct(String searchValue,int page,int rows){
        List<Work> works = workService.searchWorkByProduct(searchValue,page,rows);
        PageInfo<Work> pageInfo = new PageInfo<>(works);
        PageBean<Work> pageBean = new PageBean<>(works, pageInfo.getTotal());
        return pageBean;
    }

    //根据workId模糊查询
    @RequestMapping("search_work_by_workId")
    @ResponseBody
    public PageBean<Work> searchWorkById(String searchValue,int page,int rows){
        List<Work> works = workService.searchWorkById(searchValue,page,rows);
        PageInfo<Work> pageInfo = new PageInfo<>(works);
        PageBean<Work> pageBean = new PageBean<>(works, pageInfo.getTotal());
        return pageBean;
    }

    //批量删除作业
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
        int i = workService.deleteBatch(strings);
        Map<String, String> map = new HashMap<>();
        if(i == 1){
            map.put("status","200");}
        else {
            map.put("msg","删除失败！");}
        return map;
    }

    //跳转至作业编辑页面，更新编辑后的作业会直接调用updateAll的方法（因为与单个work的更新一样）
    @RequestMapping("edit")
    public String edit(){
        return "work_edit";
    }

    @RequestMapping("add_judge")
    @ResponseBody
    public String addJudge(){
        return "null";
    }

    //跳转至作业新增页面
    @RequestMapping("add")
    public String add(){
        return "work_add";
    }

    //新增订单
    @RequestMapping("insert")
    @ResponseBody
    public Map insert(Work work){
        int i = workService.insertWork(work);
        Map<String, String> map = new HashMap<>();
        if(i == 1){
            map.put("status","200");}
        else {
            map.put("msg","插入失败！");}
        return map;
    }

    //更新work
    @RequestMapping("edit_judge")
    @ResponseBody
    public String editJudge(){
        return "null";
    }

    //更新单个Work
    @RequestMapping("update_all")
    @ResponseBody
    public Map updateAll(Work work){
        int i = workService.updateSingleWork(work);
        Map<String, String> map = new HashMap<>();
        if(i == 1){
            map.put("status","200");}
        else {
            map.put("msg","提交失败！");}
        return map;
    }

    //根据id查询单个作业进行回显
    @RequestMapping("get/{id}")
    @ResponseBody
    public Work queryWorkById(@PathVariable String id){
        Work work = workService.queryWorkById(id);
        return work;
    }

    //查询所有work信息进行回显
    @RequestMapping("get_data")
    @ResponseBody
    public List<Work> queryAllWork(){
        List<Work> works = workService.queryAllWork();
        return works;
    }
    //点作业管理的页面显示
    @RequestMapping("find")
    public String find(){
        return "work_list";
    }


    @RequestMapping("list")
    @ResponseBody
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
