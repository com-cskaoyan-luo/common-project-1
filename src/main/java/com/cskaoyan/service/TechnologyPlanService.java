package com.cskaoyan.service;

import com.cskaoyan.bean.TechnologyPlan;
import com.cskaoyan.bean.TechnologyPlanExample;

import java.util.List;

public interface TechnologyPlanService {
    List<TechnologyPlan> findTechnologyPlanList(int page, int rows);

    boolean insertTechnologyPlan(TechnologyPlan technologyPlan);

    boolean updateTechnologyPlan(TechnologyPlan technologyPlan, TechnologyPlanExample technologyPlanExample);

    boolean deleteTechnologyPlans(TechnologyPlanExample technologyPlanExample);

    List<TechnologyPlan> searchTechnologyPlanById(int page, int rows, String searchValue);

    List<TechnologyPlan> searchTechnologyPlanByName(int page, int rows, String searchValues);

    List<TechnologyPlan> findAllTechnologyPlan();

    TechnologyPlan getTechnologyPlanById(String technologyPlanId);
}
