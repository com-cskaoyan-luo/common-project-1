package com.cskaoyan.service.impl;

import com.cskaoyan.service.MaterialConsumeService;
import com.cskaoyan.bean.MaterialConsume;
import com.cskaoyan.bean.MaterialConsumeExample;
import com.cskaoyan.mapper.MaterialConsumeMapper;
import com.cskaoyan.mapper.MaterialMapper;
import com.cskaoyan.mapper.WorkMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MaterialConsumeServiceImpl implements MaterialConsumeService {
    @Autowired
    MaterialConsumeMapper materialConsumeMapper;
    @Autowired
    MaterialMapper materialMapper;
    @Autowired
    WorkMapper workMapper;
    @Override
    public List<MaterialConsume> findMaterialConsumeList(int page, int rows){
        PageHelper.startPage(page,rows);
        MaterialConsumeExample materialConsumeExample= new MaterialConsumeExample();
        List<MaterialConsume> list = materialConsumeMapper.selectByExample(materialConsumeExample);
        for (MaterialConsume materialConsume : list) {
            materialConsume.setMaterial(materialMapper.selectByPrimaryKey(materialConsume.getMaterialId()));
            materialConsume.setWork(workMapper.selectByPrimaryKey(materialConsume.getWorkId()));
        }
        return list;
    }

    @Override
    public boolean insertMaterialConsume(MaterialConsume materialConsume) {
        try{
           materialConsumeMapper.insert(materialConsume);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateMaterialConsume(MaterialConsume materialConsume, MaterialConsumeExample materialConsumeExample) {
        try{
            materialConsumeMapper.updateByExample(materialConsume,materialConsumeExample);
            materialConsume.setMaterial(materialMapper.selectByPrimaryKey(materialConsume.getMaterialId()));
            materialConsume.setWork(workMapper.selectByPrimaryKey(materialConsume.getWorkId()));
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteMaterialConsumes(MaterialConsumeExample materialConsumeExample) {
        try{
            materialConsumeMapper.deleteByExample(materialConsumeExample);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<MaterialConsume> searchMaterialConsumeByExample(int page, int rows, MaterialConsumeExample materialConsumeExample) {
        PageHelper.startPage(page,rows);
        List<MaterialConsume> list = materialConsumeMapper.selectByExample(materialConsumeExample);
        for (MaterialConsume materialConsume : list) {
            materialConsume.setMaterial(materialMapper.selectByPrimaryKey(materialConsume.getMaterialId()));
            materialConsume.setWork(workMapper.selectByPrimaryKey(materialConsume.getWorkId()));
        }
        return list;
    }
}
