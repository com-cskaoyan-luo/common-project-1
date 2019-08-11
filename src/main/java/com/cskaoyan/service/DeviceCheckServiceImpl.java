package com.cskaoyan.service.impl;

import com.cskaoyan.bean.*;
import com.cskaoyan.mapper.DeviceCheckMapper;
import com.cskaoyan.mapper.DeviceTypeMapper;
import com.cskaoyan.service.DeviceCheckService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceCheckServiceImpl implements DeviceCheckService {
    @Autowired
    DeviceCheckMapper deviceCheckMapper;
    @Autowired
    DeviceTypeMapper deviceTypeMapper;

    @Override
    public int updateByPrimaryKeySelective(DeviceCheck record) {
        int i = deviceCheckMapper.updateByPrimaryKeySelective(record);
        return i;
    }

    @Override
    public List<DeviceType> getDataByDeviceType() {
        DeviceTypeExample deviceTypeExample = new DeviceTypeExample();
        List<DeviceType> deviceChecks = deviceTypeMapper.selectByExample(deviceTypeExample);
        return deviceChecks;
    }

    @Override
    public List<DeviceCheck> findAllDeviceCheck(int page, int rows) {
        //将参数传给方法实现分页
        PageHelper.startPage(page, rows);
        List<DeviceCheck> deviceChecks = deviceCheckMapper.selectPageDeviceCheck();
        return deviceChecks;
    }

    @Override
    public int insert(DeviceCheck record) {
        int insert = deviceCheckMapper.insert(record);
        return insert;
    }

    @Override
    public int deleteByExample(List<String> strings) {
        int i = 0;
        for (String string : strings) {
            i = deviceCheckMapper.deleteByPrimaryKey(string);
        }
        return i;
    }

    //根据设备id进行模糊查询
    @Override
    public List<DeviceCheck> searchDeviceCheckByDeviceCheckId(String searchValue, int page, int rows) {
        PageHelper.startPage(page, rows);
        List<DeviceCheck> deviceChecks = deviceCheckMapper.searchDeviceCheckByDeviceCheckId(searchValue);

        return deviceChecks;
    }
    // 根据设备名称进行模糊查询
    @Override
    public List<DeviceCheck> searchDeviceCheckByDeviceCheckName(String searchValue, int page, int rows) {
        PageHelper.startPage(page, rows);
        String searchValueLike = "%" + searchValue + "%";
        List<DeviceCheck> deviceChecks = deviceCheckMapper.searchDeviceCheckByDeviceCheckName(searchValueLike);

        return deviceChecks;
    }
}
