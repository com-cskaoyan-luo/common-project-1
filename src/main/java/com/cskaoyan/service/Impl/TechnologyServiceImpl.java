package com.cskaoyan.Service.Impl;

import com.cskaoyan.Service.TechnologyService;
import com.cskaoyan.bean.Technology;
import com.cskaoyan.bean.TechnologyExample;
import com.cskaoyan.mapper.TechnologyMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@Service
public class TechnologyServiceImpl implements TechnologyService {
    @Autowired
    TechnologyMapper technologyMapper;
    @Override
    public List<Technology> findTechnologyList(int page,int rows) {
        PageHelper.startPage(page,rows);
        TechnologyExample technologyExample = new TechnologyExample();
        List<Technology> list = technologyMapper.selectByExample(technologyExample);
        return list;
    }

    @Override
    public boolean insertTechnology(Technology technology) {
        try{
            technologyMapper.insert(technology);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateTechnology(Technology technology,TechnologyExample technologyExample) {
        try{
            technologyMapper.updateByExample(technology,technologyExample);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteTechnologies(TechnologyExample technologyExample) {
        try{
            technologyMapper.deleteByExample(technologyExample);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Technology> findAllTechnology() {
        TechnologyExample technologyExample = new TechnologyExample();
        List<Technology> list = technologyMapper.selectByExample(technologyExample);
        return list;
    }

    @Override
    public Technology getTechnologyById(String technologyId) {
        return technologyMapper.selectByPrimaryKey(technologyId);
    }

    @Override
    public List<Technology> searchTechnologyList(int page, int rows, TechnologyExample technologyExample) {
        PageHelper.startPage(page,rows);
        List<Technology> list = technologyMapper.selectByExample(technologyExample);
        return list;
    }
}
