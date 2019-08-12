package com.cskaoyan.service.impl;

import com.cskaoyan.bean.Custom;
import com.cskaoyan.bean.CustomExample;
import com.cskaoyan.mapper.CustomMapper;
import com.cskaoyan.service.CustomService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomServiceImpl implements CustomService {
    @Autowired
    CustomMapper customMapper;
    @Override
    public Custom queryCustomById(String id) {
        Custom custom = customMapper.selectByPrimaryKey(id);
        return custom;
    }

    @Override
    public List<Custom> queryAllCustom() {
        CustomExample customExample = new CustomExample();
        List<Custom> customs = customMapper.selectByExample(customExample);
        return customs;
    }

    //可选择性地更新单个Custom
    @Override
    public int updateSingleCustom(Custom custom) {
        int i = customMapper.updateByPrimaryKeySelective(custom);
        return i;
    }

    //查询所有的custom进行显示
    @Override
    public List<Custom> queryCustom(int page, int rows) {
        PageHelper.startPage(page,rows);
        CustomExample customExample = new CustomExample();
        List<Custom> customs = customMapper.selectByExample(customExample);
        return customs;
    }

    //新增客户
    @Override
    public int insertCustom(Custom custom) {
        int i = customMapper.insert(custom);
        return i;
    }


    //批量删除客户
    @Override
    public int deleteBatch(List<String> strings) {
        int i = 0;
        for (String string : strings) {
            i = customMapper.deleteByPrimaryKey(string);
        }
        return i;
    }

    //根据id模糊查询
    @Override
    public List<Custom> searchCustomById(String searchValue, int page, int rows) {
        PageHelper.startPage(page,rows);
        String s = "%" + searchValue + "%";
        CustomExample customExample = new CustomExample();
        customExample.createCriteria().andCustomIdLike(s);
        List<Custom> customs = customMapper.selectByExample(customExample);
        return customs;
    }

    //根据name模糊查询
    @Override
    public List<Custom> searchCustomByName(String searchValue, int page, int rows) {
        PageHelper.startPage(page,rows);
        String s = "%" + searchValue + "%";
        CustomExample customExample = new CustomExample();
        customExample.createCriteria().andCustomNameLike(s);
        List<Custom> customs = customMapper.selectByExample(customExample);
        return customs;
    }
}
