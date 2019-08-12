package com.cskaoyan.controller;

import com.cskaoyan.service.TechnologyPlanService;
import com.cskaoyan.service.TechnologyService;
import com.cskaoyan.bean.PageBean;
import com.cskaoyan.bean.TechnologyPlan;
import com.cskaoyan.bean.TechnologyPlanExample;
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
public class TechnologyPlanController {
    @Autowired
    TechnologyPlanService technologyPlanService;
    @Autowired
    TechnologyService technologyService;
    //展示
    @RequestMapping("technologyPlan/find")
    public String findList(){
        return "technologyPlan_list";
    }
    @RequestMapping("technologyPlan/list")
    @ResponseBody
    public PageBean<TechnologyPlan> list(int page, int rows){
        List<TechnologyPlan> technologyPlans = technologyPlanService.findTechnologyPlanList(page,rows);
        PageInfo<TechnologyPlan> pageInfo = new PageInfo<>(technologyPlans);
        PageBean<TechnologyPlan> pageBean = new PageBean<>(technologyPlans,pageInfo.getTotal());

        return pageBean;
    }


    //新增
    @RequestMapping("technologyPlan/add_judge")
    @ResponseBody
    public String add_judge(){
        return "";
    }

    @RequestMapping("technologyPlan/add")
    public String add(){
        return "technologyPlan_add";
    }

    @RequestMapping("technologyPlan/insert")
    @ResponseBody
    public HashMap insert(TechnologyPlan technologyPlan){
        boolean insertStatus = technologyPlanService.insertTechnologyPlan(technologyPlan);
        return getMap(insertStatus);
    }
    @RequestMapping("technologyPlan/get_data")
    @ResponseBody
    public List<TechnologyPlan> getdata(){
        List<TechnologyPlan> list = technologyPlanService.findAllTechnologyPlan();
        return list;
    }
    @RequestMapping("technologyPlan/get/{technologyPlanId}")
    @ResponseBody
    public TechnologyPlan getTechnology(@PathVariable("technologyPlanId") String technologyPlanId){
        return technologyPlanService.getTechnologyPlanById(technologyPlanId);
    }
    //编辑
    @RequestMapping("technologyPlan/edit_judge")
    @ResponseBody
    public String edit_judge(){
        return "";
    }
    @RequestMapping("technologyPlan/edit")
    public String edit(){
        return "technologyPlan_edit";
    }

    @RequestMapping("technologyPlan/update_all")
    @ResponseBody
    public HashMap update(TechnologyPlan technologyPlan){
        TechnologyPlanExample technologyPlanExample = new TechnologyPlanExample();
        technologyPlanExample.createCriteria().andTechnologyPlanIdEqualTo(String.valueOf(technologyPlan.getTechnologyPlanId()));
        boolean editStatus = technologyPlanService.updateTechnologyPlan(technologyPlan,technologyPlanExample);
        return getMap(editStatus);
    }
    //删除
    @RequestMapping("technologyPlan/delete_judge")
    @ResponseBody
    public String delete_judge(){
        return "";
    }

    @RequestMapping("technologyPlan/delete_batch")
    @ResponseBody
    public HashMap delete(String[] ids){
        boolean deleteStatus = true;
        for (String id : ids) {
            TechnologyPlanExample technologyPlanExample = new TechnologyPlanExample();
            technologyPlanExample.createCriteria().andTechnologyPlanIdEqualTo(id);
            deleteStatus = technologyPlanService.deleteTechnologyPlans(technologyPlanExample);
            if(deleteStatus==false){
                break;
            }
        }
        return getMap(deleteStatus);
    }
    //搜索
    @RequestMapping("technologyPlan/search_technologyPlan_by_technologyPlanId")
    @ResponseBody
    public PageBean<TechnologyPlan> searchById(int page, int rows,String searchValue){
        List<TechnologyPlan> technologyPlans = technologyPlanService.searchTechnologyPlanById(page,rows,"%"+searchValue+"%");
        PageInfo<TechnologyPlan> pageInfo = new PageInfo<>(technologyPlans);
        PageBean<TechnologyPlan> pageBean = new PageBean<>(technologyPlans,pageInfo.getTotal());
        return pageBean;
    }
    @RequestMapping("technologyPlan/search_technologyPlan_by_technologyName")
    @ResponseBody
    public PageBean<TechnologyPlan> searchByName(int page, int rows,String searchValue){
        List<TechnologyPlan> technologyPlans = technologyPlanService.searchTechnologyPlanByName(page,rows,"%"+searchValue+"%");
        PageInfo<TechnologyPlan> pageInfo = new PageInfo<>(technologyPlans);
        PageBean<TechnologyPlan> pageBean = new PageBean<>(technologyPlans,pageInfo.getTotal());
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
