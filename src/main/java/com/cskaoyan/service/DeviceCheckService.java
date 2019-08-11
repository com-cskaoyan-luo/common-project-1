package com.cskaoyan.service;

import com.cskaoyan.bean.DeviceCheck;
import com.cskaoyan.bean.DeviceCheck;
import com.cskaoyan.bean.DeviceType;

import java.util.List;

public interface DeviceCheckService {
    int updateByPrimaryKeySelective(DeviceCheck record);

    List<DeviceType> getDataByDeviceType();

    List<DeviceCheck> findAllDeviceCheck(int page, int rows);

    int insert(DeviceCheck record);

    int deleteByExample(List<String> strings);

    List<DeviceCheck> searchDeviceCheckByDeviceCheckId(String searchValue, int page, int rows);

    List<DeviceCheck> searchDeviceCheckByDeviceCheckName(String searchValue, int page, int rows);
}
