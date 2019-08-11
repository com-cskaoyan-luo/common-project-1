package com.cskaoyan.controller;

import com.cskaoyan.bean.COrder;
import com.cskaoyan.bean.Manufacture;
import com.cskaoyan.bean.PageBean;
import com.cskaoyan.service.ManufactureService;
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
@RequestMapping("manufacture")
public class ManufactureController {
    @Autowired
    ManufactureService manufactureService;

    //查询所有manufacture信息进行回显
    @RequestMapping("get_data")
    @ResponseBody
    public List<Manufacture> queryAllManufacture(){
        List<Manufacture> manufactures = manufactureService.queryAllManufacture();
        return manufactures;
    }

    //根据id查询单个生产计划进行回显
    @RequestMapping("get/{id}")
    @ResponseBody
    public Manufacture queryManufactureById(@PathVariable String id){
        Manufacture manufacture = manufactureService.queryManufactureById(id);
        return manufacture;
    }


    //根据manufactureTechnologyName模糊查询
    @RequestMapping("search_manufacture_by_manufactureTechnologyName")
    @ResponseBody
    public PageBean<Manufacture> searchManufactureByTechnologyName(String searchValue,int page,int rows){
        List<Manufacture> manufactures = manufactureService.searchManufactureByTechnologyName(searchValue,page,rows);
        PageInfo<Manufacture> pageInfo = new PageInfo<>(manufactures);
        PageBean<Manufacture> pageBean = new PageBean<>(manufactures, pageInfo.getTotal());
        return pageBean;
    }

    //根据manufactureOrderId模糊查询
    @RequestMapping("search_manufacture_by_manufactureOrderId")
    @ResponseBody
    public PageBean<Manufacture> searchManufactureByOrderId(String searchValue,int page,int rows){
        List<Manufacture> manufactures = manufactureService.searchManufactureByOrderId(searchValue,page,rows);
        PageInfo<Manufacture> pageInfo = new PageInfo<>(manufactures);
        PageBean<Manufacture> pageBean = new PageBean<>(manufactures, pageInfo.getTotal());
        return pageBean;
    }

    //根据manufactureSn模糊查询
    @RequestMapping("search_manufacture_by_manufactureSn")
    @ResponseBody
    public PageBean<Manufacture> searchWorkByProduct(String searchValue,int page,int rows){
        List<Manufacture> manufactures = manufactureService.searchWorkByManufactureSn(searchValue,page,rows);
        PageInfo<Manufacture> pageInfo = new PageInfo<>(manufactures);
        PageBean<Manufacture> pageBean = new PageBean<>(manufactures, pageInfo.getTotal());
        return pageBean;
    }

    //批量删除生产计划
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
        int i = manufactureService.deleteBatch(strings);
        Map<String, String> map = new HashMap<>();
        if(i == 1){
            map.put("status","200");}
        else {
            map.put("msg","删除失败！");}
        return map;
    }

    @RequestMapping("edit_judge")
    @ResponseBody
    public String editJudge(){
        return "null";
    }

    //跳转至生产计划编辑页面
    @RequestMapping("edit")
    public String edit(){
        return "manufacture_edit";
    }
    //更新编辑后的生产计划
    @RequestMapping("update_all")
    @ResponseBody
    public Map updateAll(Manufacture manufacture){
        int i = manufactureService.updateAllManufacture(manufacture);
        Map<String, String> map = new HashMap<>();
        if(i == 1){
            map.put("status","200");}
        else {
            map.put("msg","更新失败！");}
        return map;
    }

    @RequestMapping("add_judge")
    @ResponseBody
    public String addJudge(){
        return "null";
    }

    //跳转至生产计划新增页面
    @RequestMapping("add")
    public String add(){
        return "manufacture_add";
    }

    //新增生产计划
    @RequestMapping("insert")
    @ResponseBody
    public Map insert(Manufacture manufacture){
        int i = manufactureService.insertManufacture(manufacture);
        Map<String, String> map = new HashMap<>();
        if(i == 1){
            map.put("status","200");}
        else {
            map.put("msg","插入失败！");}
        return map;
    }

    //点生产计划管理的页面显示
    @RequestMapping("find")
    public String find(){
        return "manufacture_list";
    }

    @ResponseBody
    @RequestMapping("list")
    public PageBean<Manufacture> queryManufacture(int page, int rows){
        List<Manufacture> manufactures = manufactureService.queryManufacture(page, rows);
        //查询到的数据给到PageInfo ，只需要把结果集给到该对象，获取分页信息
        // 就可以通过该对象get方法拿到总页数，总记录数，等等你想要的数据
        PageInfo<Manufacture> pageInfo = new PageInfo<>(manufactures);
        PageBean<Manufacture> pageBean = new PageBean<>(manufactures, pageInfo.getTotal());
        /*for (COrder cOrder : cOrders) {
            System.out.println(cOrder);
        }*/
        return pageBean;
    }
}
