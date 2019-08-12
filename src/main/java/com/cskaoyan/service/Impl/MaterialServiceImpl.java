package com.cskaoyan.service.impl;

import com.cskaoyan.service.MaterialService;
import com.cskaoyan.bean.Material;
import com.cskaoyan.bean.MaterialExample;
import com.cskaoyan.mapper.MaterialMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    MaterialMapper materialMapper;
    @Override
    public List<Material> findMaterialList(int page, int rows) {
        PageHelper.startPage(page,rows);
        MaterialExample materialExample = new MaterialExample();
        List<Material> list = materialMapper.selectByExample(materialExample);
        return list;
    }

    @Override
    public Material getMaterialById(String materialId) {
        return materialMapper.selectByPrimaryKey(materialId);
    }

    @Override
    public List<Material> findAllMaterial() {
        MaterialExample materialExample = new MaterialExample();
        List<Material> list = materialMapper.selectByExample(materialExample);
        return list;
    }

    @Override
    public boolean insertMaterial(Material material) {
        try{
            materialMapper.insert(material);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateMaterial(Material material, MaterialExample materialExample) {
        try{
            materialMapper.updateByExample(material,materialExample);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteMaterials(MaterialExample materialExample) {
        try{
            materialMapper.deleteByExample(materialExample);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Material> searchMaterialList(int page, int rows, MaterialExample materialExample) {
        PageHelper.startPage(page,rows);
        List<Material> list = materialMapper.selectByExample(materialExample);
        return list;
    }
}
