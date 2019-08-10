package com.cskaoyan.service.impl;

import com.cskaoyan.service.ProcessService;
import com.cskaoyan.bean.Process;
import com.cskaoyan.bean.ProcessExample;
import com.cskaoyan.mapper.ProcessMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProcessServiceImpl implements ProcessService {
    @Autowired
    ProcessMapper processMapper;
    @Override
    public List<Process> findProcessList(int page, int rows) {
        PageHelper.startPage(page,rows);
        ProcessExample processExample = new ProcessExample();
        List<Process> list = processMapper.selectByExample(processExample);
        return list;
    }

    @Override
    public Process getProcessById(String processId) {
        return null;
    }

    @Override
    public boolean insertProcess(Process process) {
        try{
            processMapper.insert(process);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateProcess(Process process, ProcessExample processExample) {
        try{
            processMapper.updateByExample(process,processExample);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteProcesses(ProcessExample processExample) {
        try{
            processMapper.deleteByExample(processExample);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Process> searchProcessList(int page, int rows, ProcessExample processExample) {
        PageHelper.startPage(page,rows);
        List<Process> list = processMapper.selectByExample(processExample);
        return list;
    }

    @Override
    public List<Process> findAllProcess() {
        ProcessExample processExample = new ProcessExample();
        List<Process> list = processMapper.selectByExample(processExample);
        return list;
    }
}
