package com.cskaoyan.mapper;

import com.cskaoyan.bean.Device;
import com.cskaoyan.bean.DeviceExample;
import java.util.List;

import com.cskaoyan.bean.Employee;
import org.apache.ibatis.annotations.Param;

public interface DeviceMapper {
    long countByExample(DeviceExample example);

    int deleteByExample(DeviceExample example);

    int deleteByPrimaryKey(String deviceId);

    int insert(Device record);

    int insertSelective(Device record);

    List<Device> selectByExample(DeviceExample example);

    Device selectByPrimaryKey(String deviceId);

    int updateByExampleSelective(@Param("record") Device record, @Param("example") DeviceExample example);

    int updateByExample(@Param("record") Device record, @Param("example") DeviceExample example);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);

    List<Device> selectPageDevice();

    List<Device> searchDeviceByDeviceId(@Param("searchValueId") String searchValue);

    List<Device> searchDeviceByDeviceName(@Param("searchValueName") String searchValueLike);

    List<Device> searchDeviceByDeviceTypeName(@Param("searchValueTypeName") String searchValueLike);
}