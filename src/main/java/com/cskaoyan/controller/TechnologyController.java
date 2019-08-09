package com.cskaoyan.controller;

import com.cskaoyan.Service.Impl.TechnologyServiceImpl;
import com.cskaoyan.Service.TechnologyService;
import com.cskaoyan.bean.PageBean;
import com.cskaoyan.bean.Technology;
import com.cskaoyan.bean.TechnologyExample;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TechnologyController {
    @Autowired
    TechnologyService technologyService;
//展示
    @RequestMapping("technology/find")
    public String findList(HttpServletRequest request){
        List<String> list = new ArrayList<>();
        list.add("technology:add");
        list.add("technology:edit");
        list.add("technology:delete");
        request.getSession().setAttribute("sysPermissionList",list);
        return "technology_list";
    }
    @RequestMapping("technology/list")
    @ResponseBody
    public PageBean<Technology> list(int page, int rows){
        List<Technology> technologies = technologyService.findTechnologyList(page,rows);
        PageInfo<Technology> pageInfo = new PageInfo<>(technologies);
        PageBean<Technology> pageBean = new PageBean<>(technologies,pageInfo.getTotal());


        return pageBean;
    }
//新增
    @RequestMapping("technology/add_judge")
    @ResponseBody
    public String add_judge(){
        return "";
    }

    @RequestMapping("technology/add")
    public String add(){
        return "technology_add";
    }

    @RequestMapping("technology/insert")
    @ResponseBody
    public HashMap insert(Technology technology){
         boolean insertStatus = technologyService.insertTechnology(technology);
        return getMap(insertStatus);
    }
//编辑
    @RequestMapping("technology/edit_judge")
    @ResponseBody
    public String edit_judge(){
        return "";
    }
    @RequestMapping("technology/edit")
    public String edit(){
        return "technology_edit";
    }

    @RequestMapping("technology/update_all")
    @ResponseBody
    public HashMap update(Technology technology){
        TechnologyExample technologyExample = new TechnologyExample();
        technologyExample.createCriteria().andTechnologyIdEqualTo(String.valueOf(technology.getTechnologyId()));
        boolean editStatus = technologyService.updateTechnology(technology,technologyExample);
        return getMap(editStatus);
    }
//删除
    @RequestMapping("technology/delete_judge")
    @ResponseBody
    public String delete_judge(){
        return "";
    }

    @RequestMapping("technology/delete_batch")
    @ResponseBody
    public HashMap delete(String[] ids){
        boolean deleteStatus = true;
        for (String id : ids) {
            TechnologyExample technologyExample = new TechnologyExample();
            technologyExample.createCriteria().andTechnologyIdEqualTo(id);
            deleteStatus = technologyService.deleteTechnologies(technologyExample);
        }
        return getMap(deleteStatus);
    }
//搜索
    @RequestMapping("technology/search_technology_by_technologyId")
    @ResponseBody
    public PageBean<Technology> searchById(int page, int rows,String searchValue){
        TechnologyExample technologyExample = new TechnologyExample();
        technologyExample.createCriteria().andTechnologyIdLike("%"+searchValue+"%");
        List<Technology> technologies = technologyService.searchTechnologyList(page,rows,technologyExample);
        PageInfo<Technology> pageInfo = new PageInfo<>(technologies);
        PageBean<Technology> pageBean = new PageBean<>(technologies,pageInfo.getTotal());
        return pageBean;
    }
    @RequestMapping("technology/search_technology_by_technologyName")
    @ResponseBody
    public PageBean<Technology> searchByName(int page, int rows,String searchValue){
        TechnologyExample technologyExample = new TechnologyExample();
        technologyExample.createCriteria().andTechnologyNameLike("%"+searchValue+"%");
        List<Technology> technologies = technologyService.searchTechnologyList(page,rows,technologyExample);
        PageInfo<Technology> pageInfo = new PageInfo<>(technologies);
        PageBean<Technology> pageBean = new PageBean<>(technologies,pageInfo.getTotal());
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
