package com.cskaoyan.service;

import com.cskaoyan.bean.Manufacture;

import java.util.List;

public interface ManufactureService {
    List<Manufacture> queryManufacture(int page, int rows);

    int insertManufacture(Manufacture manufacture);

    int updateAllManufacture(Manufacture manufacture);

    int deleteBatch(List<String> strings);

    List<Manufacture> searchWorkByManufactureSn(String searchValue, int page, int rows);

    List<Manufacture> searchManufactureByOrderId(String searchValue, int page, int rows);

    List<Manufacture> searchManufactureByTechnologyName(String searchValue, int page, int rows);

    Manufacture queryManufactureById(String id);

    List<Manufacture> queryAllManufacture();

}
