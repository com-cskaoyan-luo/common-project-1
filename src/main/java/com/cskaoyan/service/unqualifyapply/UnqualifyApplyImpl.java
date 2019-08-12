package com.cskaoyan.service.unqualifyapply;


import com.cskaoyan.bean.*;
import com.cskaoyan.bean.Process;
import com.cskaoyan.mapper.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnqualifyApplyImpl implements com.cskaoyan.service.unqualifyapply.UnqualifyApply {
    @Autowired
    UnqualifyApplyMapper unqualifyApplyMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    FinalMeasuretCheckMapper finalMeasuretCheckMapper;
    @Autowired
    CustomMapper customMapper;
    @Autowired
    FinalCountCheckMapper finalCountCheckMapper;
    @Autowired
    COrderMapper cOrderMapper;
    @Autowired
    ProcessMeasureCheckMapper processMeasureCheckMapper;
    @Autowired
    TechnologyPlanMapper technologyPlanMapper;
    @Autowired
    ProcessMapper processMapper;
    @Autowired
    ProcessCountCheckMapper processCountCheckMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Override
    public List<com.cskaoyan.bean.UnqualifyApply> queryAll() {
        return unqualifyApplyMapper.queryAll();
    }

    @Override
    public Employee queryemployee(String id) {
        return unqualifyApplyMapper.queryemployee(id);
    }

    @Override
    public Product queeryproduct(String id) {
        return  productMapper.selectByPrimaryKey(id);

    }

    @Override
    public List<FinalMeasuretCheck> queryAllFinalMeasureCheck() {

        return finalMeasuretCheckMapper.queryAllFinalMeasureCheck();
    }

    @Override
    public List<Product> queryProductList(ProductExample productExample) {

        return productMapper.selectByExample(productExample);
    }

    @Override
    public List<Custom> queryCustomList(CustomExample customExample) {
        List<Custom> customs = customMapper.selectByExample(customExample);
        return customs;
    }

    @Override
    public List<FinalCountCheck> queryAllCountCheck() {
        List<FinalCountCheck> finalCountChecks = finalCountCheckMapper.queryAllFinalCountCheck();
        return finalCountChecks;
    }

    @Override
    public COrder queryAllOrder(String id) {
        COrder cOrder = cOrderMapper.queryOrderById(id);
        return cOrder;
    }

    @Override
    public List<ProcessMeasureCheck> queryAllPMeasureCheck() {

        return processMeasureCheckMapper.queryAllPMeasureCheck();
    }

    @Override
    public List<TechnologyPlan> queryTechologyPlan() {
        TechnologyPlanExample example = new TechnologyPlanExample();
        example.createCriteria().andTechnologyIdIsNotNull();
        List<TechnologyPlan> list = technologyPlanMapper.selectByExample(example);
        return list;
    }

    @Override
    public Process queryProcessById(String id) {
        Process process = processMapper.selectByPrimaryKey(id);
        return process;
    }

    @Override
    public List<ProcessCountCheck> queryAllPCountCheck() {
        ;
        return processCountCheckMapper.queryAllPCountCheck();
    }

    @Override
    public void insertUnqualifyApplyBean(com.cskaoyan.bean.UnqualifyApply unqualifyApplyBean) {
        unqualifyApplyMapper.insertSelective(unqualifyApplyBean);
    }

    @Override
    public List<Employee> queryListEmployee() {
        EmployeeExample example = new EmployeeExample();
        example.createCriteria().andEmpIdIsNotNull();
        return employeeMapper.selectByExample(example);

    }

    @Override
    public void delete(String[] ids) {
        if(ids.length!=0){
            for (String id : ids) {
            unqualifyApplyMapper.deleteByPrimaryKey(id);
            }
        }
    }

    @Override
    public void updateAll(com.cskaoyan.bean.UnqualifyApply unqualify) {
        unqualifyApplyMapper.updateByPrimaryKey(unqualify);
    }

    @Override
    public List<COrder> queryOrder() {
        return cOrderMapper.queryOrderList();

    }

    @Override
    public List<Process> getProcessList() {
        ProcessExample example = new ProcessExample();
        example.createCriteria().andProcessIdIsNotNull();
        List<Process> processes = processMapper.selectByExample(example);
        return processes;
    }

    @Override
    public List<com.cskaoyan.bean.UnqualifyApply> unqualifySearch(@Param("name") String name) {
        return unqualifyApplyMapper.unqualifySearch(name);

    }

    @Override
    public List<com.cskaoyan.bean.UnqualifyApply> unqualifySearchId(@Param("id")String searchValue) {
        return unqualifyApplyMapper.unqualifySearchId(searchValue);

    }

    @Override
    public List<com.cskaoyan.bean.FinalMeasuretCheck> finalMeasureCheckSearch(@Param("id")String searchValue) {
        return finalMeasuretCheckMapper.finalMeasureCheckIdSearch(searchValue);
    }

    @Override
    public List<com.cskaoyan.bean.FinalMeasuretCheck> finalMeasureCheckSearchId(@Param("id")String searchValue) {
        return finalMeasuretCheckMapper.finalMeasureOrderIdSearch(searchValue);
    }

    @Override
    public List<FinalCountCheck> fccCheckIdSearch(String searchValue) {
        return finalCountCheckMapper.fccCheckIdSearch(searchValue);
    }

    @Override
    public List<FinalCountCheck> fccOrderIdSearch(String searchValue) {
        return finalCountCheckMapper.fccOrderIdSearch(searchValue);
    }

    @Override
    public List<ProcessMeasureCheck> pMeasureCheckIdSearch(String searchValue) {
        return processMeasureCheckMapper.pMeasureCheckIdSearch(searchValue);
    }

    @Override
    public List<ProcessCountCheck> processCountCheckIdSearch(String searchValue) {
        return processCountCheckMapper.processCountCheckIdSearch(searchValue);
    }


}
