package com.cskaoyan.service.impl;

import com.cskaoyan.bean.COrder;
import com.cskaoyan.bean.Work;
import com.cskaoyan.bean.WorkExample;
import com.cskaoyan.mapper.WorkMapper;
import com.cskaoyan.service.WorkService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkServiceImpl implements WorkService {
    @Autowired
    WorkMapper workMapper;
    WorkExample workExample =new WorkExample();

    //查询所有的work以回显
    @Override
    public List<Work> queryAllWork() {
        return workMapper.selectByExample(workExample);
    }

    //作业管理页面多对多查询
    @Override
    public List<Work> queryWork(int page, int rows) {
        PageHelper.startPage(page,rows);
        List<Work> works = workMapper.selectPageWork();
        return works;
    }

    //根据id回显单个作业
    @Override
    public Work queryWorkById(String id) {
        Work work = workMapper.selectWorkByPrimaryKey(id);
        return work;
    }

    //可选择性地更新单个Work
    @Override
    public int updateSingleWork(Work work) {
        int i = workMapper.updateByPrimaryKey(work);
        return i;
    }

    //新增作业
    @Override
    public int insertWork(Work work) {
        int insert = workMapper.insert(work);
        return insert;
    }

    //批量删除作业
    @Override
    public int deleteBatch(List<String> strings) {
        int i = 0;
        for (String string : strings) {
            i = workMapper.deleteByPrimaryKey(string);
        }
        return i;
    }

    //根据id模糊查询
    @Override
    public List<Work> searchWorkById(String searchValue, int page, int rows) {
        PageHelper.startPage(page,rows);
        String s = "%" + searchValue + "%";
        List<Work> works = workMapper.selectPageWorkById(s);
        return works;
    }

    //根据productName模糊查询
    @Override
    public List<Work> searchWorkByProduct(String searchValue, int page, int rows) {
        PageHelper.startPage(page,rows);
        String s = "%" + searchValue + "%";
        List<Work> works = workMapper.selectPageWorkByProduct(s);
        return works;
    }

    //根据deviceName模糊查询
    @Override
    public List<Work> searchWorkByDevice(String searchValue, int page, int rows) {
        PageHelper.startPage(page,rows);
        String s = "%" + searchValue + "%";
        List<Work> works = workMapper.selectPageWorkByDevice(s);
        return works;
    }

    //根据process模糊查询
    @Override
    public List<Work> searchWorkByProcess(String searchValue, int page, int rows) {
        PageHelper.startPage(page,rows);
        String s = "%" + searchValue + "%";
        List<Work> works = workMapper.selectPageWorkByProcess(s);
        return works;
    }

}
