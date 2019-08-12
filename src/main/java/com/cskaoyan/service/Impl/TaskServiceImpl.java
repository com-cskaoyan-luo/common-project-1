package com.cskaoyan.service.impl;

import com.cskaoyan.bean.Manufacture;
import com.cskaoyan.bean.Task;
import com.cskaoyan.bean.TaskExample;
import com.cskaoyan.mapper.TaskMapper;
import com.cskaoyan.service.TaskService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskMapper taskMapper;

    //点击生产派工的页面显示查询
    @Override
    public List<Task> queryTask(int page, int rows) {
        PageHelper.startPage(page, rows);
        TaskExample taskExample = new TaskExample();
        List<Task> tasks = taskMapper.selectByExample(taskExample);
        return tasks;
    }

    //新增生产派工
    @Override
    public int insertTask(Task task) {
        int insert = taskMapper.insert(task);
        return insert;
    }

    //更新task
    @Override
    public int updateSingleTask(Task task) {
        return taskMapper.updateByPrimaryKeySelective(task);
    }

    //批量删除task
    @Override
    public int deleteBatch(List<String> strings) {
        int i = 0;
        for (String string : strings) {
            i = taskMapper.deleteByPrimaryKey(string);
        }
        return i;
    }

    //根据taskId模糊查询
    @Override
    public List<Task> searchTaskById(String searchValue, int page, int rows) {
        PageHelper.startPage(page,rows);
        String s = "%" + searchValue + "%";
        TaskExample taskExample = new TaskExample();
        taskExample.createCriteria().andTaskIdLike(s);
        List<Task> tasks = taskMapper.selectByExample(taskExample);
        return tasks;
    }

    //根据workId模糊查询
    @Override
    public List<Task> searchTaskByWorkId(String searchValue, int page, int rows) {
        PageHelper.startPage(page,rows);
        String s = "%" + searchValue + "%";
        TaskExample taskExample = new TaskExample();
        taskExample.createCriteria().andWorkIdLike(s);
        List<Task> tasks = taskMapper.selectByExample(taskExample);
        return tasks;
    }

    //根据manufactureId模糊查询
    @Override
    public List<Task> searchTaskByManufactureId(String searchValue, int page, int rows) {
        PageHelper.startPage(page,rows);
        String s = "%" + searchValue + "%";
        TaskExample taskExample = new TaskExample();
        taskExample.createCriteria().andManufactureSnLike(s);
        List<Task> tasks = taskMapper.selectByExample(taskExample);
        return tasks;
    }
}
