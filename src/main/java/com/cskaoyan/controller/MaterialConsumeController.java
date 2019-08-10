package com.cskaoyan.controller;

import com.cskaoyan.service.MaterialConsumeService;
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
public class MaterialConsumeController {
    @Autowired
    MaterialConsumeService materialConsumeService;
    @Autowired
    TechnologyService technologyService;
    //展示
    @RequestMapping("materialConsume/find")
    public String findList(HttpServletRequest request){
        List<String> list = new ArrayList<>();
        list.add("materialConsume:add");
        list.add("materialConsume:edit");
        list.add("materialConsume:delete");
        request.getSession().setAttribute("sysPermissionList",list);
        return "materialConsume_list";
    }
    @RequestMapping("materialConsume/list")
    @ResponseBody
    public PageBean<MaterialConsume> list(int page, int rows){
        List<MaterialConsume> materialConsumes = materialConsumeService.findMaterialConsumeList(page,rows);
        PageInfo<MaterialConsume> pageInfo = new PageInfo<>(materialConsumes);
        PageBean<MaterialConsume> pageBean = new PageBean<>(materialConsumes,pageInfo.getTotal());

        return pageBean;
    }


    //新增
    @RequestMapping("materialConsume/add_judge")
    @ResponseBody
    public String add_judge(){
        return "";
    }

    @RequestMapping("materialConsume/add")
    public String add(){
        return "materialConsume_add";
    }

    @RequestMapping("materialConsume/insert")
    @ResponseBody
    public HashMap insert(MaterialConsume materialConsume){
        boolean insertStatus = materialConsumeService.insertMaterialConsume(materialConsume);
        return getMap(insertStatus);
    }
    @RequestMapping("materialConsume/get_data")
    @ResponseBody
    public List<Technology> getdata(){
        List<Technology> list = technologyService.findAllTechnology();
        return list;
    }
    //编辑
    @RequestMapping("materialConsume/edit_judge")
    @ResponseBody
    public String edit_judge(){
        return "";
    }
    @RequestMapping("materialConsume/edit")
    public String edit(){
        return "materialConsume_edit";
    }

    @RequestMapping("materialConsume/update_all")
    @ResponseBody
    public HashMap update(MaterialConsume materialConsume){
        MaterialConsumeExample materialConsumeExample = new MaterialConsumeExample();
        materialConsumeExample.createCriteria().andConsumeIdEqualTo(String.valueOf(materialConsume.getConsumeId()));
        boolean editStatus = materialConsumeService.updateMaterialConsume(materialConsume,materialConsumeExample);
        return getMap(editStatus);
    }
    //删除
    @RequestMapping("materialConsume/delete_judge")
    @ResponseBody
    public String delete_judge(){
        return "";
    }

    @RequestMapping("materialConsume/delete_batch")
    @ResponseBody
    public HashMap delete(String[] ids){
        boolean deleteStatus = true;
        for (String id : ids) {
            MaterialConsumeExample materialConsumeExample = new MaterialConsumeExample();
            materialConsumeExample.createCriteria().andConsumeIdEqualTo(id);
            deleteStatus = materialConsumeService.deleteMaterialConsumes(materialConsumeExample);
            if(deleteStatus==false){
                break;
            }
        }
        return getMap(deleteStatus);
    }
    //搜索
    @RequestMapping("materialConsume/search_materialConsume_by_consumeId")
    @ResponseBody
    public PageBean<MaterialConsume> searchByConsume(int page, int rows,String searchValue){
        MaterialConsumeExample materialConsumeExample = new MaterialConsumeExample();
        materialConsumeExample.createCriteria().andConsumeIdLike("%"+searchValue+"%");
        List<MaterialConsume> materialConsumes = materialConsumeService.searchMaterialConsumeByExample(page,rows,materialConsumeExample);
        PageInfo<MaterialConsume> pageInfo = new PageInfo<>(materialConsumes);
        PageBean<MaterialConsume> pageBean = new PageBean<>(materialConsumes,pageInfo.getTotal());
        return pageBean;
    }
    @RequestMapping("materialConsume/search_materialConsume_by_workId")
    @ResponseBody
    public PageBean<MaterialConsume> searchByWork(int page, int rows,String searchValue){
        MaterialConsumeExample materialConsumeExample = new MaterialConsumeExample();
        materialConsumeExample.createCriteria().andWorkIdLike("%"+searchValue+"%");
        List<MaterialConsume> materialConsumes = materialConsumeService.searchMaterialConsumeByExample(page,rows,materialConsumeExample);
        PageInfo<MaterialConsume> pageInfo = new PageInfo<>(materialConsumes);
        PageBean<MaterialConsume> pageBean = new PageBean<>(materialConsumes,pageInfo.getTotal());
        return pageBean;
    }
    @RequestMapping("materialConsume/search_materialConsume_by_materialId")
    @ResponseBody
    public PageBean<MaterialConsume> searchByMaterial(int page, int rows,String searchValue){
        MaterialConsumeExample materialConsumeExample = new MaterialConsumeExample();
        materialConsumeExample.createCriteria().andMaterialIdLike("%"+searchValue+"%");
        List<MaterialConsume> materialConsumes = materialConsumeService.searchMaterialConsumeByExample(page,rows,materialConsumeExample);
        PageInfo<MaterialConsume> pageInfo = new PageInfo<>(materialConsumes);
        PageBean<MaterialConsume> pageBean = new PageBean<>(materialConsumes,pageInfo.getTotal());
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
