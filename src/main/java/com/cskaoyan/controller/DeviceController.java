package com.cskaoyan.controller;

import com.cskaoyan.bean.*;
import com.cskaoyan.service.DeviceService;
import com.cskaoyan.service.impl.DeviceServiceImpl;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
/**
 * 功能：设备管理之设备台账功能
 * 日期：2019年8月11日19:04:17
 * 负责人：张鑫
 */

@Controller
public class DeviceController {
    @Autowired
    DeviceService deviceService;

    @RequestMapping("device/deviceList")
    public String findDevice(HttpServletRequest request) {
        List<String> list = new ArrayList<>();
        list.add("device:add");
        list.add("device:edit");
        list.add("device:delete");

        request.getSession().setAttribute("sysPermissionList",list);
        return "deviceList";
    }

    @RequestMapping("deviceList/list")
    @ResponseBody
    public PageBean<Device> list(int page, int rows) {
        List<Device> devices = deviceService.findAllDevice(page,rows);
        //查询到的数据给到PageInfo ，只需要把结果集给到该对象，获取分页信息
        // 就可以通过该对象get方法拿到总页数，总记录数，等等你想要的数据
        PageInfo<Device> pageInfo=new PageInfo<>(devices);
        //根据前台需要在自定义一个分页对象
        //我的本次项目只需要传入页面需要的list集合，和total，同时json形式返回
        PageBean<Device> pageBean=new PageBean<>(devices,pageInfo.getTotal());

        //把该对象json返回
        return  pageBean;
    }

    @RequestMapping("deviceList/get_data")
    @ResponseBody
    public List<Device> getDataByDeviceList() {
        List<Device> devices = deviceService.getDataByDeviceList();
        return devices;
    }


    // 增加判断(点击“增加”按钮)
    @RequestMapping("deviceList/add_judge")
    @ResponseBody
    public String addJudge() {
        return "button";
    }

    @RequestMapping("deviceList/add")
    public String add() {
        return "deviceList_add";
    }

    // 增
    @RequestMapping("deviceList/insert")
    @ResponseBody
    public Map insert(Device record) {
        int i = deviceService.insert(record);
        HashMap<String, String> map = new HashMap<>();
        int status = 200;
        if (i == 1) {
            map.put("status",status + "");
        }
        return map;
    }

    //删
    //删除按钮
    @RequestMapping("deviceList/delete_judge")
    @ResponseBody
    public String deleteJudge() {
        return "deleteButton";
    }

    @RequestMapping("deviceList/delete_batch")
    @ResponseBody
    public Map deleteByExample(String ids) {
        String[] split = ids.split(",");
        List<String> strings = Arrays.asList(split);
        int i = deviceService.deleteByExample(strings);

        HashMap<String, String> map = new HashMap<>();
        int status = 200;
        if (i == 1) {
            map.put("status",status + "");
        }
        return map;
    }

    //改
    @RequestMapping("deviceList/edit_judge")
    @ResponseBody
    public String editJudge() {
        return "editButton";
    }

    @RequestMapping("deviceList/edit")
    public String edit() {
        return "deviceList_edit";
    }

    /**
     *  模糊查询之根据设备编号查询
     */
    @RequestMapping("deviceList/search_device_by_deviceId")
    @ResponseBody
    public PageBean<Device> searchDeviceByDeviceId(String searchValue,int page,int rows) {
        List<Device> devices = deviceService.searchDeviceByDeviceId(searchValue,page,rows);
        PageInfo<Device> PageInfo = new PageInfo<>(devices);
        PageBean<Device> pageBean = new PageBean<>(devices, PageInfo.getTotal());
        return pageBean;
    }

    /**
     *  模糊查询之根据设备名称查询
     */
    @RequestMapping("deviceList/search_device_by_deviceName")
    @ResponseBody
    public PageBean<Device> searchDeviceByDeviceName(String searchValue,int page,int rows) {
        List<Device> devices = deviceService.searchDeviceByDeviceName(searchValue,page,rows);
        PageInfo<Device> PageInfo = new PageInfo<>(devices);
        PageBean<Device> pageBean = new PageBean<>(devices, PageInfo.getTotal());
        return pageBean;
    }

    /**
     *  模糊查询之根据设备种类名称查询
     */
    @RequestMapping("deviceList/search_device_by_deviceTypeName")
    @ResponseBody
    public PageBean<Device> searchDeviceByDeviceTypeName(String searchValue,int page,int rows) {
        List<Device> devices = deviceService.searchDeviceByDeviceTypeName(searchValue,page,rows);
        PageInfo<Device> PageInfo = new PageInfo<>(devices);
        PageBean<Device> pageBean = new PageBean<>(devices, PageInfo.getTotal());
        return pageBean;
    }

}

