package com.cskaoyan.service.impl;


import com.cskaoyan.bean.Device;
import com.cskaoyan.bean.DeviceExample;
import com.cskaoyan.bean.DeviceType;
import com.cskaoyan.bean.DeviceTypeExample;
import com.cskaoyan.mapper.DeviceMapper;
import com.cskaoyan.mapper.DeviceTypeMapper;
import com.cskaoyan.service.DeviceTypeService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceTypeServiceImpl implements DeviceTypeService {
    @Autowired
    DeviceTypeMapper deviceTypeMapper;

    @Override
    public DeviceType getDataByDeviceType(String id) {
        DeviceType deviceType = deviceTypeMapper.selectByPrimaryKey(id);
        return deviceType;
    }

    @Override
    public List<DeviceType> getDataByDeviceTypeList() {
        DeviceTypeExample deviceTypeExample = new DeviceTypeExample();
        List<DeviceType> deviceTypes = deviceTypeMapper.selectByExample(deviceTypeExample);
        return deviceTypes;

    }

    @Override
    public List<DeviceType> findAllDeviceType(int page, int rows) {
        //将参数传给方法实现分页
        PageHelper.startPage(page, rows);

        //DeviceExample example = new DeviceTypeExample();
        //example.createCriteria().andDeviceIdBetween(String.valueOf((page-1)*rows),String.valueOf(rows));

        //List<DeviceType> deviceTypes = deviceTypeMapper.selectPageDeviceType();

        DeviceTypeExample deviceTypeExample = new DeviceTypeExample();
        List<DeviceType> deviceTypes = deviceTypeMapper.selectByExample(deviceTypeExample);
        return deviceTypes;
    }

    @Override
    public int insert(DeviceType record) {
        int insert = deviceTypeMapper.insert(record);
        return insert;
    }

    @Override
    public int deleteByExample(List<String> strings) {
        DeviceTypeExample deviceTypeExample = new DeviceTypeExample();
        deviceTypeExample.createCriteria().andDeviceTypeIdIn(strings);
        int delete = deviceTypeMapper.deleteByExample(deviceTypeExample);
        return delete;
    }

    @Override
    public int updateByPrimaryKeySelective(DeviceType record) {
        int update = deviceTypeMapper.updateByPrimaryKeySelective(record);
        return update;
    }

    //根据设备id进行模糊查询
    @Override
    public List<DeviceType> searchDeviceTypeByDeviceTypeId(String searchValue, int page, int rows) {
        PageHelper.startPage(page, rows);
        List<DeviceType> deviceTypes = deviceTypeMapper.searchDeviceTypeByDeviceTypeId(searchValue);

        return deviceTypes;
    }
    // 根据设备名称进行模糊查询
    @Override
    public List<DeviceType> searchDeviceTypeByDeviceTypeName(String searchValue, int page, int rows) {
        PageHelper.startPage(page, rows);
        String searchValueLike = "%" + searchValue + "%";
        List<DeviceType> deviceTypes = deviceTypeMapper.searchDeviceTypeByDeviceTypeName(searchValueLike);

        return deviceTypes;
    }
}
