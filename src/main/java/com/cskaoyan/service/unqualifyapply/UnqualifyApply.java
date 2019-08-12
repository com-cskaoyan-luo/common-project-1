package com.cskaoyan.service.unqualifyapply;



        import com.cskaoyan.bean.*;
        import com.cskaoyan.bean.Process;

        import java.util.List;


public interface UnqualifyApply {
    List<com.cskaoyan.bean.UnqualifyApply> queryAll();
    Employee queryemployee(String id);
    Product queeryproduct(String id);
    List<FinalMeasuretCheck> queryAllFinalMeasureCheck();
    List<Product> queryProductList(ProductExample productExample);
    List<Custom> queryCustomList(CustomExample customExample);
    List<FinalCountCheck> queryAllCountCheck();
    COrder queryAllOrder (String id);
    List<ProcessMeasureCheck> queryAllPMeasureCheck();
    List<TechnologyPlan> queryTechologyPlan();
    Process queryProcessById(String id);
    List<ProcessCountCheck> queryAllPCountCheck();
    void insertUnqualifyApplyBean(com.cskaoyan.bean.UnqualifyApply unqualifyApplyBean);
    List<Employee> queryListEmployee();
    void delete(String[] ids);

    void updateAll(com.cskaoyan.bean.UnqualifyApply unqualify);


    List<COrder> queryOrder();

    List<Process> getProcessList();

    List<com.cskaoyan.bean.UnqualifyApply> unqualifySearch(String name);

    List<com.cskaoyan.bean.UnqualifyApply> unqualifySearchId(String searchValue);

    List<com.cskaoyan.bean.FinalMeasuretCheck> finalMeasureCheckSearch(String searchValue);

    List<com.cskaoyan.bean.FinalMeasuretCheck> finalMeasureCheckSearchId(String searchValue);

    List<com.cskaoyan.bean.FinalCountCheck> fccCheckIdSearch(String searchValue);

    List<com.cskaoyan.bean.FinalCountCheck> fccOrderIdSearch(String searchValue);

    List<ProcessMeasureCheck> pMeasureCheckIdSearch(String searchValue);

    List<ProcessCountCheck> processCountCheckIdSearch(String searchValue);
}
