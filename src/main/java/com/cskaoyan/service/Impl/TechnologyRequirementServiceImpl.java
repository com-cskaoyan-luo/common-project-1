package com.cskaoyan.Service.Impl;

import com.cskaoyan.Service.TechnologyRequirementService;
import com.cskaoyan.bean.*;
import com.cskaoyan.mapper.TechnologyMapper;
import com.cskaoyan.mapper.TechnologyRequirementMapper;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TechnologyRequirementServiceImpl implements TechnologyRequirementService {
    @Autowired
    TechnologyRequirementMapper technologyRequirementMapper;
    @Override
    public List<TechnologyRequirement> findTechnologyRequirementList(int page, int rows) {
        PageHelper.startPage(page,rows);
        List<TechnologyRequirement> list = technologyRequirementMapper.selectAll();
        return list;
    }

    @Override
    public boolean insertTechnologyRequirement(TechnologyRequirement technologyRequirement) {
        try{
            technologyRequirementMapper.insert(technologyRequirement);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateTechnologyRequirement(TechnologyRequirement technologyRequirement,TechnologyRequirementExample technologyRequirementExample) {
        try{
            technologyRequirementMapper.updateByExample(technologyRequirement,technologyRequirementExample);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteTechnologyRequirements(TechnologyRequirementExample technologyRequirementExample) {
        try{
            technologyRequirementMapper.deleteByExample(technologyRequirementExample);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<TechnologyRequirement> searchTechnologyRequirementById(int page, int rows, @Param("searchValue") String searchValue) {
        PageHelper.startPage(page,rows);
        List<TechnologyRequirement> list = technologyRequirementMapper.searchById(searchValue);
        return list;
    }

    @Override
    public List<TechnologyRequirement> searchTechnologyRequirementByName(int page, int rows, @Param("searchValue") String searchValue) {
        PageHelper.startPage(page,rows);
        List<TechnologyRequirement> list = technologyRequirementMapper.searchByName(searchValue);
        return list;
    }

}
