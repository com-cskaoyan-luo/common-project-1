package com.cskaoyan.service;



import com.cskaoyan.bean.*;

import java.util.List;


public interface UnqualifyApply {
    List<com.cskaoyan.bean.UnqualifyApply> queryAll();
    Employee queryemployee(String id);
    Product queeryproduct(String id);
    List<FinalMeasuretCheck> queryAllFinalMeasureCheck();
    List<Product> queryProductList(ProductExample productExample);
    List<Custom> queryCustomList(CustomExample customExample);
    List<FinalCountCheck> queryAllCountCheck();
}
