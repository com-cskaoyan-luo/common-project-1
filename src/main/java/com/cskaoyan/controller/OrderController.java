package com.cskaoyan.controller;

import com.cskaoyan.bean.COrder;
import com.cskaoyan.bean.PageBean;
import com.cskaoyan.service.impl.OrderServiceImpl;
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
@RequestMapping("order")
public class OrderController {
    @Autowired
    OrderServiceImpl orderService;

    //更新订单要求
    @RequestMapping("update_note")
    @ResponseBody
    public Map updateNote(String orderId,String note){
        int i = orderService.updateNote(orderId,note);
        Map<String, String> map = new HashMap<>();
        if(i == 1){
            map.put("status","200");}
        else {
            map.put("msg","订单要求更新失败！");}
        return map;
    }

    //根据orderProduct模糊查询
    @RequestMapping("search_order_by_orderProduct")
    @ResponseBody
    public PageBean<COrder> searchOrderByProduct(String searchValue,int page,int rows){
        List<COrder> cOrders = orderService.searchOrderByProduct(searchValue,page,rows);
        PageInfo<COrder> pageInfo = new PageInfo<>(cOrders);
        PageBean<COrder> pageBean = new PageBean<>(cOrders, pageInfo.getTotal());
        return pageBean;
    }

    //根据orderCustom模糊查询
    @RequestMapping("search_order_by_orderCustom")
    @ResponseBody
    public PageBean<COrder> searchOrderByCustom(String searchValue,int page,int rows){
        List<COrder> cOrders = orderService.searchOrderByCustom(searchValue,page,rows);
        PageInfo<COrder> pageInfo = new PageInfo<>(cOrders);
        PageBean<COrder> pageBean = new PageBean<>(cOrders, pageInfo.getTotal());
        return pageBean;
    }

    //根据orderId模糊查询
    @RequestMapping("search_order_by_orderId")
    @ResponseBody
    public PageBean<COrder> searchOrderById(String searchValue,int page,int rows){
        List<COrder> cOrders = orderService.searchOrderById(searchValue,page,rows);
        PageInfo<COrder> pageInfo = new PageInfo<>(cOrders);
        PageBean<COrder> pageBean = new PageBean<>(cOrders, pageInfo.getTotal());
        return pageBean;
    }

    //批量删除订单
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
        int i = orderService.deleteBatch(strings);
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

    //跳转至订单编辑页面
    @RequestMapping("edit")
    public String edit(){
        return "order_edit";
    }
    //更新编辑后的订单
    @RequestMapping("update_all")
    @ResponseBody
    public Map updateAll(COrder cOrder){
        int i = orderService.updateAllOrder(cOrder);
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

    //跳转至订单新增页面
    @RequestMapping("add")
    public String add(){
        return "order_add";
    }

    //新增订单
    @RequestMapping("insert")
    @ResponseBody
    public Map insert(COrder cOrder){
        int i = orderService.insertOrder(cOrder);
        Map<String, String> map = new HashMap<>();
        if(i == 1){
            map.put("status","200");}
        else {
            map.put("msg","插入失败！");}
        return map;
    }

    //点订单管理的页面显示
    @RequestMapping("find")
    public String find(){
        return "order_list";
    }

    @ResponseBody
    @RequestMapping("list")
    public PageBean<COrder> queryOrder(int page, int rows){
        List<COrder> cOrders = orderService.queryOrder(page, rows);
        //查询到的数据给到PageInfo ，只需要把结果集给到该对象，获取分页信息
        // 就可以通过该对象get方法拿到总页数，总记录数，等等你想要的数据
        PageInfo<COrder> pageInfo = new PageInfo<>(cOrders);
        PageBean<COrder> pageBean = new PageBean<>(cOrders, pageInfo.getTotal());
        /*for (COrder cOrder : cOrders) {
            System.out.println(cOrder);
        }*/
        return pageBean;
    }
}
