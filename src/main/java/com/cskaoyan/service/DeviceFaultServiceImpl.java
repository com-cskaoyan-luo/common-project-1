package com.cskaoyan.service.impl;

import com.cskaoyan.bean.*;
import com.cskaoyan.mapper.DeviceFaultMapper;
import com.cskaoyan.mapper.DeviceFaultMapper;
import com.cskaoyan.mapper.DeviceTypeMapper;
import com.cskaoyan.service.DeviceFaultService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceFaultServiceImpl implements DeviceFaultService {
    @Autowired
    DeviceFaultMapper deviceFaultMapper;
    @Autowired
    DeviceTypeMapper deviceTypeMapper;

    @Override
    public int updateByPrimaryKeySelective(DeviceFault record) {
        int i = deviceFaultMapper.updateByPrimaryKeySelective(record);
        return i;
    }

    /*@Override
    public List<DeviceFault> getDataByDeviceFault() {
        
        DeviceTypeExample deviceTypeExample = new DeviceTypeExample();
        List<DeviceType> deviceFaults = deviceTypeMapper.selectByExample(deviceTypeExample);
        return deviceFaults;
    }*/

    @Override
    public List<DeviceFault> findAllDeviceFault(int page, int rows) {
        //将参数传给方法实现分页
        PageHelper.startPage(page, rows);
        //DeviceFaultExample deviceFaultExample = new DeviceFaultExample();
        List<DeviceFault> deviceFaults = deviceFaultMapper.selectPageDeviceFault();
        return deviceFaults;
    }

    @Override
    public int insert(DeviceFault record) {
        int insert = deviceFaultMapper.insert(record);
        return insert;
    }

    @Override
    public int deleteByExample(List<String> strings) {
        int i = 0;
        for (String string : strings) {
            i = deviceFaultMapper.deleteByPrimaryKey(string);
        }
        return i;
    }

    //根据设备id进行模糊查询
    @Override
    public List<DeviceFault> searchDeviceFaultByDeviceFaultId(String searchValue, int page, int rows) {
        PageHelper.startPage(page, rows);
        List<DeviceFault> deviceFaults = deviceFaultMapper.searchDeviceFaultByDeviceFaultId(searchValue);

        return deviceFaults;
    }
    // 根据设备名称进行模糊查询
    @Override
    public List<DeviceFault> searchDeviceFaultByDeviceFaultName(String searchValue, int page, int rows) {
        PageHelper.startPage(page, rows);
        String searchValueLike = "%" + searchValue + "%";
        List<DeviceFault> deviceFaults = deviceFaultMapper.searchDeviceFaultByDeviceFaultName(searchValueLike);

        return deviceFaults;
    }
}
