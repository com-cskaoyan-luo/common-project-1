package com.cskaoyan.service.impl;

import com.cskaoyan.bean.*;
import com.cskaoyan.mapper.DepartmentMapper;
import com.cskaoyan.mapper.DeviceMapper;
import com.cskaoyan.mapper.DeviceTypeMapper;
import com.cskaoyan.mapper.EmployeeMapper;
import com.cskaoyan.service.DeviceService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    DeviceMapper deviceMapper;

    //根据设备id进行模糊查询
    @Override
    public List<Device> searchDeviceByDeviceId(String searchValue, int page, int rows) {
        PageHelper.startPage(page, rows);
        List<Device> devices = deviceMapper.searchDeviceByDeviceId(searchValue);

        return devices;
    }
    // 根据设备名称进行模糊查询
    @Override
    public List<Device> searchDeviceByDeviceName(String searchValue, int page, int rows) {
        PageHelper.startPage(page, rows);
        String searchValueLike = "%" + searchValue + "%";
        List<Device> devices = deviceMapper.searchDeviceByDeviceName(searchValueLike);

        return devices;
    }
    // 根据设备种类名称进行模糊查询
    @Override
    public List<Device> searchDeviceByDeviceTypeName(String searchValue, int page, int rows) {
        PageHelper.startPage(page, rows);
        String searchValueLike = "%" + searchValue + "%";
        List<Device> devices = deviceMapper.searchDeviceByDeviceTypeName(searchValueLike);

        return devices;
    }

    @Override
    public List<Device> findAllDevice(int page, int rows) {
        //将参数传给方法实现分页
        PageHelper.startPage(page, rows);
        //DeviceExample example = new DeviceExample();
        //example.createCriteria().andDeviceIdBetween(String.valueOf((page-1)*rows),String.valueOf(rows));

        List<Device> devices = deviceMapper.selectPageDevice();

        return devices;
    }

    @Override
    public List<Device> getDataByDeviceList() {
        DeviceExample deviceExample = new DeviceExample();
        List<Device> deviceList = deviceMapper.selectByExample(deviceExample);
        return deviceList;
    }

    @Override
    public int insert(Device record) {
        int insert = deviceMapper.insert(record);
        return insert;
    }

    @Override
    public int deleteByExample(List<String> strings) {
        DeviceExample deviceExample = new DeviceExample();
        deviceExample.createCriteria().andDeviceIdIn(strings);
        int i = deviceMapper.deleteByExample(deviceExample);
        return i;
    }

    @Override
    public int updateByPrimaryKeySelective(Device record) {
        int update = deviceMapper.updateByPrimaryKeySelective(record);
        return update;
    }
}
