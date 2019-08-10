package com.cskaoyan.service.impl;

import com.cskaoyan.bean.COrder;
import com.cskaoyan.bean.COrderExample;
import com.cskaoyan.bean.Custom;
import com.cskaoyan.bean.CustomExample;
import com.cskaoyan.mapper.COrderMapper;
import com.cskaoyan.mapper.CustomMapper;
import com.cskaoyan.service.OrderService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    COrderMapper cOrderMapper;

    //订单管理页面多对多查询
    @Override
    public List<COrder> queryOrder(int page, int rows) {
        //将参数传给方法实现分页
        PageHelper.startPage(page, rows);
        //COrderExample cOrderExample = new COrderExample();
        //cOrderExample.createCriteria().andOrderIdBetween(String.valueOf((page-1)*rows),String.valueOf(rows));
        //List<COrder> cOrders = cOrderMapper.selectByExample(cOrderExample);
        List<COrder> cOrders = cOrderMapper.selectPageCOrder();
        return cOrders;
    }

    //添加订单
    @Override
    public int insertOrder(COrder cOrder) {
        int i = cOrderMapper.insert(cOrder);
        return i;
    }

    //可选择性地更新订单
    @Override
    public int updateAllOrder(COrder cOrder) {
        int i = cOrderMapper.updateByPrimaryKeySelective(cOrder);
        return i;
    }

    //批量删除订单
    @Override
    public int deleteBatch(List<String> strings) {
        //使用example方法不能批量删除(不知道为啥)
        /*COrderExample cOrderExample = new COrderExample();
        cOrderExample.createCriteria().andCustomIdIn(strings);
        int i = cOrderMapper.deleteByExample(cOrderExample);*/
        int i = 0;
        for (String string : strings) {
            i = cOrderMapper.deleteByPrimaryKey(string);
        }
        return i;
    }

    //根据id模糊查询
    @Override
    public List<COrder> searchOrderById(String searchValue, int page, int rows) {
        PageHelper.startPage(page,rows);
        /*COrderExample cOrderExample = new COrderExample();*/
        String s = "%" + searchValue + "%";
        /*cOrderExample.createCriteria().andOrderIdLike(s);
        List<COrder> cOrders = cOrderMapper.selectByExample(cOrderExample);*/
        List<COrder> cOrders = cOrderMapper.selectPageCOrderById(s);
        return cOrders;
    }
    /*@Autowired
    CustomMapper customMapper;*/
    //根据客户名称模糊查询
    @Override
    public List<COrder> searchOrderByCustom(String searchValue, int page, int rows) {
        PageHelper.startPage(page,rows);
        String s = "%" + searchValue + "%";
        List<COrder> cOrders = cOrderMapper.selectPageCOrderByCustom(s);
        /*CustomExample customExample = new CustomExample();
        customExample.createCriteria().andCustomNameLike(s);
        List<Custom> customs = customMapper.selectByExample(customExample);
        List<COrder> cOrders = new ArrayList<>();
        for (Custom custom : customs) {
            String customId = custom.getCustomId();
            COrder cOrder = cOrderMapper.selectOrderByCustomId(customId);
            cOrders.add(cOrder);
        }*/
       /* COrderExample cOrderExample = new COrderExample();
        cOrderExample.createCriteria().andCustomIdIn(cOrders);
        List<COrder> cOrders1 = cOrderMapper.selectByExample(cOrderExample);*/
        return cOrders;
    }

    //可选择性地更新订单要求
    @Override
    public int updateNote(String orderId,String note) {
        COrder cOrder = new COrder();
        cOrder.setOrderId(orderId);
        cOrder.setNote(note);
        int i = cOrderMapper.updateByPrimaryKeySelective(cOrder);
        return i;
    }

    //根据产品名称模糊查询
    @Override
    public List<COrder> searchOrderByProduct(String searchValue, int page, int rows) {
        PageHelper.startPage(page,rows);
        String s = "%" + searchValue + "%";
        List<COrder> cOrders = cOrderMapper.selectPageCOrderByProduct(s);
        return cOrders;
    }
}
