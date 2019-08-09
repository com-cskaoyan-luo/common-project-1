package com.cskaoyan.service;


import com.cskaoyan.bean.*;
import com.cskaoyan.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnqualifyApplyImpl implements UnqualifyApply {
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


}
