package com.cskaoyan.controller;

import com.cskaoyan.service.TechnologyRequirementService;
import com.cskaoyan.service.TechnologyService;
import com.cskaoyan.bean.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class TechnologyRequirementController {
    @Autowired
    TechnologyRequirementService technologyRequirementService;
    @Autowired
    TechnologyService technologyService;
    //展示
    @RequestMapping("technologyRequirement/find")
    public String findList(HttpServletRequest request){
        return "technologyRequirement_list";
    }
    @RequestMapping("technologyRequirement/list")
    @ResponseBody
    public PageBean<TechnologyRequirement> list(int page, int rows){
        List<TechnologyRequirement> technologyRequirements = technologyRequirementService.findTechnologyRequirementList(page,rows);
        PageInfo<TechnologyRequirement> pageInfo = new PageInfo<>(technologyRequirements);
        PageBean<TechnologyRequirement> pageBean = new PageBean<>(technologyRequirements,pageInfo.getTotal());

        return pageBean;
    }


    //新增
    @RequestMapping("technologyRequirement/add_judge")
    @ResponseBody
    public String add_judge(){
        return "";
    }

    @RequestMapping("technologyRequirement/add")
    public String add(){
        return "technologyRequirement_add";
    }

    @RequestMapping("technologyRequirement/insert")
    @ResponseBody
    public HashMap insert(TechnologyRequirement technologyRequirement){
        boolean insertStatus = technologyRequirementService.insertTechnologyRequirement(technologyRequirement);
        return getMap(insertStatus);
    }
    @RequestMapping("technologyRequirement/get_data")
    @ResponseBody
    public List<Technology> getdata(){
        List<Technology> list = technologyService.findAllTechnology();
        return list;
    }
    //编辑
    @RequestMapping("technologyRequirement/edit_judge")
    @ResponseBody
    public String edit_judge(){
        return "";
    }
    @RequestMapping("technologyRequirement/edit")
    public String edit(){
        return "technologyRequirement_edit";
    }

    @RequestMapping("technologyRequirement/update_all")
    @ResponseBody
    public HashMap update(TechnologyRequirement technologyRequirement){
        TechnologyRequirementExample technologyRequirementExample = new TechnologyRequirementExample();
        technologyRequirementExample.createCriteria().andTechnologyRequirementIdEqualTo(String.valueOf(technologyRequirement.getTechnologyRequirementId()));
        boolean editStatus = technologyRequirementService.updateTechnologyRequirement(technologyRequirement,technologyRequirementExample);
        return getMap(editStatus);
    }
    //删除
    @RequestMapping("technologyRequirement/delete_judge")
    @ResponseBody
    public String delete_judge(){
        return "";
    }

    @RequestMapping("technologyRequirement/delete_batch")
    @ResponseBody
    public HashMap delete(String[] ids){
        boolean deleteStatus = true;
        for (String id : ids) {
            TechnologyRequirementExample technologyRequirementExample = new TechnologyRequirementExample();
            technologyRequirementExample.createCriteria().andTechnologyRequirementIdEqualTo(id);
            deleteStatus = technologyRequirementService.deleteTechnologyRequirements(technologyRequirementExample);
            if(deleteStatus==false){
                break;
            }
        }
        return getMap(deleteStatus);
    }
    //搜索
    @RequestMapping("technologyRequirement/search_technologyRequirement_by_technologyRequirementId")
    @ResponseBody
    public PageBean<TechnologyRequirement> searchById(int page, int rows,String searchValue){
        List<TechnologyRequirement> technologyRequirements = technologyRequirementService.searchTechnologyRequirementById(page,rows,"%"+searchValue+"%");
        PageInfo<TechnologyRequirement> pageInfo = new PageInfo<>(technologyRequirements);
        PageBean<TechnologyRequirement> pageBean = new PageBean<>(technologyRequirements,pageInfo.getTotal());
        return pageBean;
    }
    @RequestMapping("technologyRequirement/search_technologyRequirement_by_technologyName")
    @ResponseBody
    public PageBean<TechnologyRequirement> searchByName(int page, int rows,String searchValue){
        List<TechnologyRequirement> technologyRequirements = technologyRequirementService.searchTechnologyRequirementByName(page,rows,"%"+searchValue+"%");
        PageInfo<TechnologyRequirement> pageInfo = new PageInfo<>(technologyRequirements);
        PageBean<TechnologyRequirement> pageBean = new PageBean<>(technologyRequirements,pageInfo.getTotal());
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
