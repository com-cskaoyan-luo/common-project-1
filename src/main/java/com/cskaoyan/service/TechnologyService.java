package com.cskaoyan.Service;

import com.cskaoyan.bean.Technology;
import com.cskaoyan.bean.TechnologyExample;

import java.util.List;

public interface TechnologyService {
    public List<Technology> findTechnologyList(int page,int rows);

    boolean insertTechnology(Technology technology);

    boolean updateTechnology(Technology technology, TechnologyExample technologyExample);

    boolean deleteTechnologies(TechnologyExample technologyExample);

    List<Technology> findAllTechnology();

    Technology getTechnologyById(String technologyId);

    List<Technology> searchTechnologyList(int page, int rows, TechnologyExample technologyExample);
}
