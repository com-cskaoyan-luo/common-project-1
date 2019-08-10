package com.cskaoyan.service.impl;

import com.cskaoyan.service.MaterialReceiveService;
import com.cskaoyan.bean.*;
import com.cskaoyan.mapper.MaterialMapper;
import com.cskaoyan.mapper.MaterialReceiveMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MaterialReceiveServiceImpl implements MaterialReceiveService {
    @Autowired
    MaterialReceiveMapper materialReceiveMapper;
    @Autowired
    MaterialMapper materialMapper;
    @Override
    public List<MaterialReceive> findMaterialReceiveList(int page, int rows) {
        PageHelper.startPage(page,rows);
        MaterialReceiveExample materialReceiveExample = new MaterialReceiveExample();
        List<MaterialReceive> list = materialReceiveMapper.selectByExample(materialReceiveExample);
        for (MaterialReceive materialReceive : list) {
            materialReceive.setMaterial(materialMapper.selectByPrimaryKey(materialReceive.getMaterialId()));
        }
        return list;
    }

    @Override
    public boolean insertMaterialReceive(MaterialReceive materialReceive) {
        try{
            materialReceiveMapper.insert(materialReceive);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateMaterialReceive(MaterialReceive materialReceive, MaterialReceiveExample materialReceiveExample) {
        try{
            materialReceiveMapper.updateByExample(materialReceive,materialReceiveExample);
            materialReceive.setMaterial(materialMapper.selectByPrimaryKey(materialReceive.getMaterialId()));
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteMaterialReceives(MaterialReceiveExample materialReceiveExample) {
        try{
            materialReceiveMapper.deleteByExample(materialReceiveExample);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<MaterialReceive> searchMaterialReceiveList(int page, int rows, MaterialReceiveExample materialReceiveExample) {
        PageHelper.startPage(page,rows);
        List<MaterialReceive> list = materialReceiveMapper.selectByExample(materialReceiveExample);
        for (MaterialReceive materialReceive : list) {
            materialReceive.setMaterial(materialMapper.selectByPrimaryKey(materialReceive.getMaterialId()));
        }
        return list;
    }
}
