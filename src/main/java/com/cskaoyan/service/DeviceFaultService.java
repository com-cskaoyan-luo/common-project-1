package com.cskaoyan.service;

import com.cskaoyan.bean.DeviceFault;
import com.cskaoyan.bean.DeviceFault;
import com.cskaoyan.bean.DeviceType;

import java.util.List;

public interface DeviceFaultService {
    int updateByPrimaryKeySelective(DeviceFault record);

    /*List<DeviceFault> getDataByDeviceFault();*/

    List<DeviceFault> findAllDeviceFault(int page, int rows);

    int insert(DeviceFault record);

    int deleteByExample(List<String> strings);

    List<DeviceFault> searchDeviceFaultByDeviceFaultId(String searchValue, int page, int rows);

    List<DeviceFault> searchDeviceFaultByDeviceFaultName(String searchValue, int page, int rows);
}
