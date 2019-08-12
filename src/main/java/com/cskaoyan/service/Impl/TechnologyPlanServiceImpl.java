package com.cskaoyan.service.impl;

import com.cskaoyan.service.TechnologyPlanService;
import com.cskaoyan.bean.*;
import com.cskaoyan.mapper.TechnologyPlanMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyPlanServiceImpl implements TechnologyPlanService {
    @Autowired
    TechnologyPlanMapper technologyPlanMapper;
    @Override
    public List<TechnologyPlan> findTechnologyPlanList(int page, int rows) {
        PageHelper.startPage(page,rows);
        List<TechnologyPlan> list = technologyPlanMapper.selectAll();
        return list;
    }

    @Override
    public boolean insertTechnologyPlan(TechnologyPlan technologyPlan) {
        try{
            technologyPlanMapper.insert(technologyPlan);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateTechnologyPlan(TechnologyPlan technologyPlan, TechnologyPlanExample technologyPlanExample) {
        try{
            technologyPlanMapper.updateByExample(technologyPlan,technologyPlanExample);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteTechnologyPlans(TechnologyPlanExample technologyPlanExample) {
        try{
            technologyPlanMapper.deleteByExample(technologyPlanExample);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<TechnologyPlan> searchTechnologyPlanById(int page, int rows, String searchValue) {
        PageHelper.startPage(page,rows);
        List<TechnologyPlan> list = technologyPlanMapper.searchById(searchValue);
        return list;
    }

    @Override
    public List<TechnologyPlan> searchTechnologyPlanByName(int page, int rows, String searchValue) {
        PageHelper.startPage(page,rows);
        List<TechnologyPlan> list = technologyPlanMapper.searchByName(searchValue);
        return list;
    }

    @Override
    public List<TechnologyPlan> findAllTechnologyPlan() {
        TechnologyPlanExample technologyPlanExample = new TechnologyPlanExample();
        List<TechnologyPlan> list = technologyPlanMapper.selectByExample(technologyPlanExample);
        return list;
    }

    @Override
    public TechnologyPlan getTechnologyPlanById(String technologyPlanId) {
        return technologyPlanMapper.selectByPrimaryKey(technologyPlanId);
    }
}
