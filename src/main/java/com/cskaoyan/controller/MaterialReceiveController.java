package com.cskaoyan.controller;

import com.cskaoyan.service.MaterialReceiveService;
import com.cskaoyan.bean.PageBean;
import com.cskaoyan.bean.MaterialReceive;
import com.cskaoyan.bean.MaterialReceiveExample;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class MaterialReceiveController {
    @Autowired
    MaterialReceiveService materialReceiveService;
    //展示
    @RequestMapping("materialReceive/find")
    public String findList(HttpServletRequest request){
        List<String> list = new ArrayList<>();
        list.add("materialReceive:add");
        list.add("materialReceive:edit");
        list.add("materialReceive:delete");
        request.getSession().setAttribute("sysPermissionList",list);
        return "materialReceive_list";
    }
    @RequestMapping("materialReceive/list")
    @ResponseBody
    public PageBean<MaterialReceive> list(int page, int rows){
        List<MaterialReceive> technologies = materialReceiveService.findMaterialReceiveList(page,rows);
        PageInfo<MaterialReceive> pageInfo = new PageInfo<>(technologies);
        PageBean<MaterialReceive> pageBean = new PageBean<>(technologies,pageInfo.getTotal());

        return pageBean;
    }
    //    @RequestMapping("materialReceive/get/{materialReceiveId}")
//    @ResponseBody
//    public MaterialReceive getMaterialReceive(@PathVariable("materialReceiveId") String materialReceiveId){
//        return materialReceiveService.getMaterialReceiveById(materialReceiveId);
//    }
    //新增
    @RequestMapping("materialReceive/add_judge")
    @ResponseBody
    public String add_judge(){
        return "";
    }

    @RequestMapping("materialReceive/add")
    public String add(){
        return "materialReceive_add";
    }

    @RequestMapping("materialReceive/insert")
    @ResponseBody
    public HashMap insert(MaterialReceive materialReceive){
        boolean insertStatus = materialReceiveService.insertMaterialReceive(materialReceive);
        return getMap(insertStatus);
    }
    //编辑
    @RequestMapping("materialReceive/edit_judge")
    @ResponseBody
    public String edit_judge(){
        return "";
    }
    @RequestMapping("materialReceive/edit")
    public String edit(){
        return "materialReceive_edit";
    }

    @RequestMapping("materialReceive/update_all")
    @ResponseBody
    public HashMap update(MaterialReceive materialReceive){
        MaterialReceiveExample materialReceiveExample = new MaterialReceiveExample();
        materialReceiveExample.createCriteria().andReceiveIdEqualTo(String.valueOf(materialReceive.getReceiveId()));
        boolean editStatus = materialReceiveService.updateMaterialReceive(materialReceive,materialReceiveExample);
        return getMap(editStatus);
    }
    //删除
    @RequestMapping("materialReceive/delete_judge")
    @ResponseBody
    public String delete_judge(){
        return "";
    }

    @RequestMapping("materialReceive/delete_batch")
    @ResponseBody
    public HashMap delete(String[] ids){
        boolean deleteStatus = true;
        for (String id : ids) {
            MaterialReceiveExample materialReceiveExample = new MaterialReceiveExample();
            materialReceiveExample.createCriteria().andReceiveIdEqualTo(id);
            deleteStatus = materialReceiveService.deleteMaterialReceives(materialReceiveExample);
            if(deleteStatus==false){
                break;
            }
        }
        return getMap(deleteStatus);
    }
    //搜索
    @RequestMapping("materialReceive/search_materialReceive_by_receiveId")
    @ResponseBody
    public PageBean<MaterialReceive> searchById(int page, int rows,String searchValue){
        MaterialReceiveExample materialReceiveExample = new MaterialReceiveExample();
        materialReceiveExample.createCriteria().andReceiveIdLike("%"+searchValue+"%");
        List<MaterialReceive> technologies = materialReceiveService.searchMaterialReceiveList(page,rows,materialReceiveExample);
        PageInfo<MaterialReceive> pageInfo = new PageInfo<>(technologies);
        PageBean<MaterialReceive> pageBean = new PageBean<>(technologies,pageInfo.getTotal());
        return pageBean;
    }
    @RequestMapping("materialReceive/search_materialReceive_by_materialId")
    @ResponseBody
    public PageBean<MaterialReceive> searchByName(int page, int rows,String searchValue){
        MaterialReceiveExample materialReceiveExample = new MaterialReceiveExample();
        materialReceiveExample.createCriteria().andMaterialIdLike("%"+searchValue+"%");
        List<MaterialReceive> technologies = materialReceiveService.searchMaterialReceiveList(page,rows,materialReceiveExample);
        PageInfo<MaterialReceive> pageInfo = new PageInfo<>(technologies);
        PageBean<MaterialReceive> pageBean = new PageBean<>(technologies,pageInfo.getTotal());
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
