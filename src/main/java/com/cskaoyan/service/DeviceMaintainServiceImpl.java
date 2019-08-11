package com.cskaoyan.service.impl;

import com.cskaoyan.bean.DeviceMaintain;
import com.cskaoyan.bean.DeviceMaintain;
import com.cskaoyan.bean.DeviceMaintainExample;
import com.cskaoyan.mapper.DeviceMaintainMapper;
import com.cskaoyan.mapper.DeviceTypeMapper;
import com.cskaoyan.service.DeviceMaintainService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceMaintainServiceImpl implements DeviceMaintainService {
    @Autowired
    DeviceMaintainMapper deviceMaintainMapper;
    @Autowired
    DeviceTypeMapper deviceTypeMapper;

    @Override
    public int updateByPrimaryKeySelective(DeviceMaintain record) {
        int i = deviceMaintainMapper.updateByPrimaryKeySelective(record);
        return i;
    }

    /*@Override
    public List<DeviceMaintain> getDataByDeviceMaintain() {
        
        DeviceTypeExample deviceTypeExample = new DeviceTypeExample();
        List<DeviceType> deviceMaintains = deviceTypeMapper.selectByExample(deviceTypeExample);
        return deviceMaintains;
    }*/

    @Override
    public List<DeviceMaintain> findAllDeviceMaintain(int page, int rows) {
        //将参数传给方法实现分页
        PageHelper.startPage(page, rows);
        List<DeviceMaintain> deviceMaintains = deviceMaintainMapper.selectPageDeviceMaintain();
        return deviceMaintains;
    }

    @Override
    public int insert(DeviceMaintain record) {
        int insert = deviceMaintainMapper.insert(record);
        return insert;
    }

    @Override
    public int deleteByExample(List<String> strings) {
        int i = 0;
        for (String string : strings) {
            i = deviceMaintainMapper.deleteByPrimaryKey(string);
        }
        return i;
    }

    //根据设备id进行模糊查询
    @Override
    public List<DeviceMaintain> searchDeviceMaintainByDeviceMaintainId(String searchValue, int page, int rows) {
        PageHelper.startPage(page, rows);
        List<DeviceMaintain> deviceMaintains = deviceMaintainMapper.searchDeviceMaintainByDeviceMaintainId(searchValue);

        return deviceMaintains;
    }
    // 根据设备名称进行模糊查询
    @Override
    public List<DeviceMaintain> searchDeviceMaintainByDeviceMaintainName(String searchValue, int page, int rows) {
        PageHelper.startPage(page, rows);
        String searchValueLike = "%" + searchValue + "%";
        List<DeviceMaintain> deviceMaintains = deviceMaintainMapper.searchDeviceMaintainByDeviceMaintainName(searchValueLike);

        return deviceMaintains;
    }
}
