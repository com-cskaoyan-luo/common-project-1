package com.cskaoyan.controller;

import com.cskaoyan.bean.ActiveUser;
import com.cskaoyan.service.MaterialService;
import com.cskaoyan.bean.PageBean;
import com.cskaoyan.bean.Material;
import com.cskaoyan.bean.MaterialExample;
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
public class MaterialController {
    @Autowired
    MaterialService materialService;

    //展示
    @RequestMapping("material/find")
    public String findList(HttpServletRequest request){

        return "material_list";
    }
    @RequestMapping("material/list")
    @ResponseBody
    public PageBean<Material> list(int page, int rows){
        List<Material> technologies = materialService.findMaterialList(page,rows);
        PageInfo<Material> pageInfo = new PageInfo<>(technologies);
        PageBean<Material> pageBean = new PageBean<>(technologies,pageInfo.getTotal());


        return pageBean;
    }
    @RequestMapping("material/get/{materialId}")
    @ResponseBody
    public Material getMaterial(@PathVariable("materialId") String materialId){
        return materialService.getMaterialById(materialId);
    }
    @RequestMapping("material/get_data")
    @ResponseBody
    public List<Material> getdata(){
        List<Material> list = materialService.findAllMaterial();
        return list;
    }
    //新增
    @RequestMapping("material/add_judge")
    @ResponseBody
    public String add_judge(){
        return "";
    }

    @RequestMapping("material/add")
    public String add(){
        return "material_add";
    }

    @RequestMapping("material/insert")
    @ResponseBody
    public HashMap insert(Material material){
        boolean insertStatus = materialService.insertMaterial(material);
        return getMap(insertStatus);
    }
    //编辑
    @RequestMapping("material/edit_judge")
    @ResponseBody
    public String edit_judge(){
        return "";
    }
    @RequestMapping("material/edit")
    public String edit(){
        return "material_edit";
    }

    @RequestMapping("material/update_all")
    @ResponseBody
    public HashMap update(Material material){
        MaterialExample materialExample = new MaterialExample();
        materialExample.createCriteria().andMaterialIdEqualTo(String.valueOf(material.getMaterialId()));
        boolean editStatus = materialService.updateMaterial(material,materialExample);
        return getMap(editStatus);
    }
    //删除
    @RequestMapping("material/delete_judge")
    @ResponseBody
    public String delete_judge(){
        return "";
    }

    @RequestMapping("material/delete_batch")
    @ResponseBody
    public HashMap delete(String[] ids){
        boolean deleteStatus = true;
        for (String id : ids) {
            MaterialExample materialExample = new MaterialExample();
            materialExample.createCriteria().andMaterialIdEqualTo(id);
            deleteStatus = materialService.deleteMaterials(materialExample);
            if(deleteStatus==false){
                break;
            }
        }
        return getMap(deleteStatus);
    }
    //搜索
    @RequestMapping("material/search_material_by_materialId")
    @ResponseBody
    public PageBean<Material> searchById(int page, int rows,String searchValue){
        MaterialExample materialExample = new MaterialExample();
        materialExample.createCriteria().andMaterialIdLike("%"+searchValue+"%");
        List<Material> technologies = materialService.searchMaterialList(page,rows,materialExample);
        PageInfo<Material> pageInfo = new PageInfo<>(technologies);
        PageBean<Material> pageBean = new PageBean<>(technologies,pageInfo.getTotal());
        return pageBean;
    }
    @RequestMapping("material/search_material_by_materialType")
    @ResponseBody
    public PageBean<Material> searchByName(int page, int rows,String searchValue){
        MaterialExample materialExample = new MaterialExample();
        materialExample.createCriteria().andMaterialTypeLike("%"+searchValue+"%");
        List<Material> technologies = materialService.searchMaterialList(page,rows,materialExample);
        PageInfo<Material> pageInfo = new PageInfo<>(technologies);
        PageBean<Material> pageBean = new PageBean<>(technologies,pageInfo.getTotal());
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
