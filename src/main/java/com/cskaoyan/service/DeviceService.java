package com.cskaoyan.service;

import com.cskaoyan.bean.*;

import java.util.List;

public interface DeviceService {
    List<Device> searchDeviceByDeviceId(String searchValue, int page, int rows);

    List<Device> findAllDevice(int page, int rows);

    int insert(Device record);

    int deleteByExample(List<String> strings);

    int updateByPrimaryKeySelective(Device record);

    List<Device> getDataByDeviceList();

    List<Device> searchDeviceByDeviceName(String searchValue, int page, int rows);

    List<Device> searchDeviceByDeviceTypeName(String searchValue, int page, int rows);
}
