package com.cskaoyan.controller;

import com.cskaoyan.bean.COrder;
import com.cskaoyan.bean.Custom;
import com.cskaoyan.bean.PageBean;
import com.cskaoyan.service.CustomService;
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
@RequestMapping("custom")
public class CustomController {
    @Autowired
    CustomService customService;

    //根据customName模糊查询
    @RequestMapping("search_custom_by_customName")
    @ResponseBody
    public PageBean<Custom> searchCustomByName(String searchValue,int page,int rows){
        List<Custom> customs = customService.searchCustomByName(searchValue,page,rows);
        PageInfo<Custom> pageInfo = new PageInfo<>(customs);
        PageBean<Custom> pageBean = new PageBean<>(customs, pageInfo.getTotal());
        return pageBean;
    }

    //根据customId模糊查询
    @RequestMapping("search_custom_by_customId")
    @ResponseBody
    public PageBean<Custom> searchCustomById(String searchValue,int page,int rows){
        List<Custom> customs = customService.searchCustomById(searchValue,page,rows);
        PageInfo<Custom> pageInfo = new PageInfo<>(customs);
        PageBean<Custom> pageBean = new PageBean<>(customs, pageInfo.getTotal());
        return pageBean;
    }

    //批量删除客户
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
        int i = customService.deleteBatch(strings);
        Map<String, String> map = new HashMap<>();
        if(i == 1){
            map.put("status","200");}
        else {
            map.put("msg","删除失败！");}
        return map;
    }

    //跳转至客户编辑页面
    @RequestMapping("edit")
    public String edit(){
        return "custom_edit";
    }


    @RequestMapping("add_judge")
    @ResponseBody
    public String addJudge(){
        return "null";
    }

    //跳转至客户新增页面
    @RequestMapping("add")
    public String add(){
        return "custom_add";
    }

    //新增客户
    @RequestMapping("insert")
    @ResponseBody
    public Map insert(Custom custom){
        int i = customService.insertCustom(custom);
        Map<String, String> map = new HashMap<>();
        if(i == 1){
            map.put("status","200");}
        else {
            map.put("msg","插入失败！");}
        return map;
    }

    //更新Custom
    @RequestMapping("edit_judge")
    @ResponseBody
    public String editJudge(){
        return "null";
    }

    //更新单个custom
    @RequestMapping("update_all")
    @ResponseBody
    public Map updateAll(Custom custom){
        int i = customService.updateSingleCustom(custom);
        Map<String, String> map = new HashMap<>();
        if(i == 1){
            map.put("status","200");}
        else {
            map.put("msg","提交失败！");}
        return map;
    }

    //查询所有客户信息进行回显
    @RequestMapping("get_data")
    @ResponseBody
    public List<Custom> queryAllCustom(){
        List<Custom> customs = customService.queryAllCustom();
        return customs;
    }


    //根据id查询单个顾客进行回显
    @RequestMapping("get/{id}")
    @ResponseBody
    public Custom queryCustomById(@PathVariable String id){
        Custom custom = customService.queryCustomById(id);
        return custom;
    }

    //点客户管理的页面显示
    @RequestMapping("find")
    public String find(){
        return "custom_list";
    }

    @ResponseBody
    @RequestMapping("list")
    public PageBean<Custom> queryCustom(int page, int rows){
        List<Custom> customs = customService.queryCustom(page, rows);
        //查询到的数据给到PageInfo ，只需要把结果集给到该对象，获取分页信息
        // 就可以通过该对象get方法拿到总页数，总记录数，等等你想要的数据
        PageInfo<Custom> pageInfo = new PageInfo<>(customs);
        PageBean<Custom> pageBean = new PageBean<>(customs, pageInfo.getTotal());
        /*for (COrder cOrder : cOrders) {
            System.out.println(cOrder);
        }*/
        return pageBean;
    }
}
