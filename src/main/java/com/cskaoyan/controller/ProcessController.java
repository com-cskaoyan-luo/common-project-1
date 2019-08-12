package com.cskaoyan.controller;

import com.cskaoyan.service.ProcessService;
import com.cskaoyan.bean.PageBean;
import com.cskaoyan.bean.Process;
import com.cskaoyan.bean.ProcessExample;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class ProcessController {
    @Autowired
    ProcessService processService;
    //展示
    @RequestMapping("process/find")
    public String findList(){

        return "process_list";
    }
    @RequestMapping("process/list")
    @ResponseBody
    public PageBean<Process> list(int page, int rows){
        List<Process> technologies = processService.findProcessList(page,rows);
        PageInfo<Process> pageInfo = new PageInfo<>(technologies);
        PageBean<Process> pageBean = new PageBean<>(technologies,pageInfo.getTotal());


        return pageBean;
    }
    @RequestMapping("process/get/{processId}")
    @ResponseBody
    public Process getProcess(@PathVariable("processId") String processId){
        return processService.getProcessById(processId);
    }
    @RequestMapping("process/get_data")
    @ResponseBody
    public List<Process> getdata(){
        List<Process> list = processService.findAllProcess();
        return list;
    }
    //新增
    @RequestMapping("process/add_judge")
    @ResponseBody
    public String add_judge(){
        return "";
    }

    @RequestMapping("process/add")
    public String add(){
        return "process_add";
    }

    @RequestMapping("process/insert")
    @ResponseBody
    public HashMap insert(Process process){
        boolean insertStatus = processService.insertProcess(process);
        return getMap(insertStatus);
    }
    //编辑
    @RequestMapping("process/edit_judge")
    @ResponseBody
    public String edit_judge(){
        return "";
    }
    @RequestMapping("process/edit")
    public String edit(){
        return "process_edit";
    }

    @RequestMapping("process/update_all")
    @ResponseBody
    public HashMap update(Process process){
        ProcessExample processExample = new ProcessExample();
        processExample.createCriteria().andProcessIdEqualTo(String.valueOf(process.getProcessId()));
        boolean editStatus = processService.updateProcess(process,processExample);
        return getMap(editStatus);
    }
    //删除
    @RequestMapping("process/delete_judge")
    @ResponseBody
    public String delete_judge(){
        return "";
    }

    @RequestMapping("process/delete_batch")
    @ResponseBody
    public HashMap delete(String[] ids){
        boolean deleteStatus = true;
        for (String id : ids) {
            ProcessExample processExample = new ProcessExample();
            processExample.createCriteria().andProcessIdEqualTo(id);
            deleteStatus = processService.deleteProcesses(processExample);
            if(deleteStatus==false){
                break;
            }
        }
        return getMap(deleteStatus);
    }
    //搜索
    @RequestMapping("process/search_process_by_processId")
    @ResponseBody
    public PageBean<Process> searchById(int page, int rows,String searchValue){
        ProcessExample processExample = new ProcessExample();
        processExample.createCriteria().andProcessIdLike("%"+searchValue+"%");
        List<Process> technologies = processService.searchProcessList(page,rows,processExample);
        PageInfo<Process> pageInfo = new PageInfo<>(technologies);
        PageBean<Process> pageBean = new PageBean<>(technologies,pageInfo.getTotal());
        return pageBean;
    }
    @RequestMapping("process/search_process_by_technologyPlanId")
    @ResponseBody
    public PageBean<Process> searchByName(int page, int rows,String searchValue){
        ProcessExample processExample = new ProcessExample();
        processExample.createCriteria().andTechnologyPlanIdLike("%"+searchValue+"%");
        List<Process> technologies = processService.searchProcessList(page,rows,processExample);
        PageInfo<Process> pageInfo = new PageInfo<>(technologies);
        PageBean<Process> pageBean = new PageBean<>(technologies,pageInfo.getTotal());
        return pageBean;
    }
    public static HashMap getMap(boolean status){
        HashMap map = new HashMap<>();
        if(status){
            map.put("status",200);
            map.put("msg","OK");
        }else{
            map.put("status",500);
            map.put("msg","ERROR");
        }
        return map;
    }
}
