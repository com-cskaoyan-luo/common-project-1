package com.cskaoyan.service;

import com.cskaoyan.bean.Device;
import com.cskaoyan.bean.DeviceType;
import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.util.List;

public interface DeviceTypeService {
    List<DeviceType> getDataByDeviceTypeList();

    DeviceType getDataByDeviceType(String id);

    List<DeviceType> findAllDeviceType(int page, int rows);

    int insert(DeviceType record);

    int deleteByExample(List<String> strings);

    int updateByPrimaryKeySelective(DeviceType record);

    List<DeviceType> searchDeviceTypeByDeviceTypeId(String searchValue, int page, int rows);

    List<DeviceType> searchDeviceTypeByDeviceTypeName(String searchValue, int page, int rows);
}
