package com.cskaoyan.service;

import com.cskaoyan.bean.DeviceMaintain;
import com.cskaoyan.bean.DeviceMaintain;

import java.util.List;

public interface DeviceMaintainService {
    int updateByPrimaryKeySelective(DeviceMaintain record);

    /*List<DeviceMaintain> getDataByDeviceMaintain();*/

    List<DeviceMaintain> findAllDeviceMaintain(int page, int rows);

    int insert(DeviceMaintain record);

    int deleteByExample(List<String> strings);

    List<DeviceMaintain> searchDeviceMaintainByDeviceMaintainId(String searchValue, int page, int rows);

    List<DeviceMaintain> searchDeviceMaintainByDeviceMaintainName(String searchValue, int page, int rows);

    int updateNote(DeviceMaintain record);
}
