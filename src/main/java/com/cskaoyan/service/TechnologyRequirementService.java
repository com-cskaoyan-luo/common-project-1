package com.cskaoyan.service;

import com.cskaoyan.bean.*;

import java.util.List;

public interface TechnologyRequirementService {
    public List<TechnologyRequirement> findTechnologyRequirementList(int page, int rows);

    boolean insertTechnologyRequirement(TechnologyRequirement technologyRequirement);

    boolean updateTechnologyRequirement(TechnologyRequirement technologyRequirement, TechnologyRequirementExample technologyRequirementExample);

    boolean deleteTechnologyRequirements(TechnologyRequirementExample technologyRequirementExample);


    List<TechnologyRequirement> searchTechnologyRequirementById(int page, int rows, String searchValue);

    List<TechnologyRequirement> searchTechnologyRequirementByName(int page, int rows, String searchValue);
}
