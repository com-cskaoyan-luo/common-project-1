package com.cskaoyan.service.impl;

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
    @Override
    public List<Work> queryAllWork() {
        return workMapper.selectByExample(workExample);
    }
	@Override
    public Work getWorkById(String workId) {
        return workMapper.selectByPrimaryKey(workId);
    }
    //作业管理页面多对多查询
    @Override
    public List<Work> queryWork(int page, int rows) {
        PageHelper.startPage(page,rows);
        List<Work> works = workMapper.selectPageWork();
        return works;
    }
}
