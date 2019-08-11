package com.cskaoyan.service.impl;

import com.cskaoyan.bean.COrder;
import com.cskaoyan.bean.Manufacture;
import com.cskaoyan.bean.ManufactureExample;
import com.cskaoyan.mapper.ManufactureMapper;
import com.cskaoyan.service.ManufactureService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufactureServiceImpl implements ManufactureService {
    @Autowired
    ManufactureMapper manufactureMapper;

    //点击生产计划管理的页面显示
    @Override
    public List<Manufacture> queryManufacture(int page, int rows) {
        //将参数传给方法实现分页
        PageHelper.startPage(page, rows);
        //COrderExample cOrderExample = new COrderExample();
        //cOrderExample.createCriteria().andOrderIdBetween(String.valueOf((page-1)*rows),String.valueOf(rows));
        //List<COrder> cOrders = cOrderMapper.selectByExample(cOrderExample);
        List<Manufacture> manufactures = manufactureMapper.selectPageManufacture();
        return manufactures;
    }

    //新增生产计划
    @Override
    public int insertManufacture(Manufacture manufacture) {
        return manufactureMapper.insert(manufacture);
    }

    //更新生产计划
    @Override
    public int updateAllManufacture(Manufacture manufacture) {
        int i = manufactureMapper.updateByPrimaryKeySelective(manufacture);
        return i;
    }

    //批量删除生产计划
    @Override
    public int deleteBatch(List<String> strings) {
        int i = 0;
        for (String string : strings) {
            i = manufactureMapper.deleteByPrimaryKey(string);
        }
        return i;
    }

    //根据id模糊查询
    @Override
    public List<Manufacture> searchWorkByManufactureSn(String searchValue, int page, int rows) {
        PageHelper.startPage(page,rows);
        String s = "%" + searchValue + "%";
        List<Manufacture>  manufactures = manufactureMapper.selectPageManufactureById(s);
        return manufactures;
    }

    //根据manufactureOrderId模糊查询
    @Override
    public List<Manufacture> searchManufactureByOrderId(String searchValue, int page, int rows) {
        PageHelper.startPage(page,rows);
        String s = "%" + searchValue + "%";
        List<Manufacture> manufactures = manufactureMapper.selectManufactureByOrderId(s);
        return manufactures;
    }

    //根据manufactureTechnologyName模糊查询
    @Override
    public List<Manufacture> searchManufactureByTechnologyName(String searchValue, int page, int rows) {
        PageHelper.startPage(page,rows);
        String s = "%" + searchValue + "%";
        List<Manufacture> manufactures = manufactureMapper.selectManufactureByTechnologyName(s);
        return manufactures;
    }

    //根据id查询回显单个生产计划
    @Override
    public Manufacture queryManufactureById(String id) {
        Manufacture manufacture = manufactureMapper.selectManufactureByPrimaryKey(id);
        return manufacture;
    }

    //查询所有的Manufacture以回显
    @Override
    public List<Manufacture> queryAllManufacture() {
        ManufactureExample manufactureExample = new ManufactureExample();
        List<Manufacture> manufactures = manufactureMapper.selectByExample(manufactureExample);
        return manufactures;
    }
}
