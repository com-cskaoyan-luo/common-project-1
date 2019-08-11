package com.cskaoyan.controller;

import com.cskaoyan.bean.*;
import com.cskaoyan.service.DeviceCheckService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class DeviceCheckController {
    @Autowired
    DeviceCheckService deviceCheckService;

    @RequestMapping("device/deviceCheck")
    public String findDeviceCheck(HttpServletRequest request) {
        List<String> list = new ArrayList<>();
        list.add("deviceCheck:add");
        list.add("deviceCheck:edit");
        list.add("deviceCheck:delete");

        request.getSession().setAttribute("sysPermissionList",list);
        return "deviceCheck";
    }

    @RequestMapping("deviceCheck/list")
    @ResponseBody
    public PageBean<DeviceCheck> list(int page, int rows) {
        List<DeviceCheck> DeviceChecks = deviceCheckService.findAllDeviceCheck(page,rows);
        //查询到的数据给到PageInfo ，只需要把结果集给到该对象，获取分页信息
        // 就可以通过该对象get方法拿到总页数，总记录数，等等你想要的数据
        PageInfo<DeviceCheck> pageInfo=new PageInfo<>(DeviceChecks);
        //根据前台需要在自定义一个分页对象
        //我的本次项目只需要传入页面需要的list集合，和total，同时json形式返回
        PageBean<DeviceCheck> pageBean=new PageBean<>(DeviceChecks,pageInfo.getTotal());

        //把该对象json返回
        return  pageBean;
    }

    /*@RequestMapping("deviceType/get_data")
    @ResponseBody
    public List<DeviceType> getDataByDeviceType() {

        List<DeviceType> deviceTypes = deviceCheckService.getDataByDeviceType();
        return deviceTypes;
    }*/
    /*
    @RequestMapping("DeviceCheckType/get_data")
    @ResponseBody
    public List<DeviceCheckType> getDataByDeviceCheckType() {
        List<DeviceCheckType> DeviceChecks = DeviceCheckService.getDataByDeviceCheckType();
        return DeviceChecks;
    }

    @RequestMapping("department/get_data")
    @ResponseBody
    public List<Department> getDataByDepartment() {
        List<Department> DeviceChecks = DeviceCheckService.getDataByDepartment();
        return DeviceChecks;
    }

    @RequestMapping("DeviceCheckList/get_data")
    @ResponseBody
    public List<DeviceCheck> getDataByDeviceCheckList() {
        List<DeviceCheck> DeviceChecks = DeviceCheckService.getDataByDeviceCheckList();
        return DeviceChecks;
    }

    */


    // 增加判断(点击“增加”按钮)
    @RequestMapping("deviceCheck/add_judge")
    @ResponseBody
    public String addJudge() {
        return "button";
    }

    @RequestMapping("deviceCheck/add")
    public String add() {
        return "deviceCheck_add";
    }

    // 增
    @RequestMapping("deviceCheck/insert")
    @ResponseBody
    public Map insert(DeviceCheck record) {
        int i = deviceCheckService.insert(record);
        HashMap<String, String> map = new HashMap<>();
        int status = 200;
        if (i == 1) {
            map.put("status",status + "");
        }
        return map;
    }

    //删
    //删除按钮
    @RequestMapping("deviceCheck/delete_judge")
    @ResponseBody
    public String deleteJudge() {
        return "deleteButton";
    }

    @RequestMapping("deviceCheck/delete_batch")
    @ResponseBody
    public Map deleteByExample(String ids) {
        String[] split = ids.split(",");
        List<String> strings = Arrays.asList(split);
        int i = deviceCheckService.deleteByExample(strings);

        HashMap<String, String> map = new HashMap<>();
        int status = 200;
        if (i == 1) {
            map.put("status",status + "");
        }
        return map;
    }

    //改
    @RequestMapping("deviceCheck/edit_judge")
    @ResponseBody
    public String editJudge() {
        return "editButton";
    }

    @RequestMapping("deviceCheck/edit")
    public String edit() {
        return "deviceCheck_edit";
    }

    @RequestMapping("deviceCheck/update")
    @ResponseBody
    public Map updateByPrimaryKeySelective(DeviceCheck record) {
        int i = deviceCheckService.updateByPrimaryKeySelective(record);
        HashMap<String, String> map = new HashMap<>();
        int status = 200;
        if (i == 1) {
            map.put("status",status + "");
        }
        return map;
    }

    /**
     *  模糊查询之根据设备编号查询
     */
    @RequestMapping("deviceCheck/search_deviceCheck_by_deviceCheckId")
    @ResponseBody
    public PageBean<DeviceCheck> searchDeviceCheckByDeviceCheckId(String searchValue,int page,int rows) {
        List<DeviceCheck> deviceChecks = deviceCheckService.searchDeviceCheckByDeviceCheckId(searchValue,page,rows);
        PageInfo<DeviceCheck> PageInfo = new PageInfo<>(deviceChecks);
        PageBean<DeviceCheck> pageBean = new PageBean<>(deviceChecks, PageInfo.getTotal());
        return pageBean;
    }

    /**
     *  模糊查询之根据设备名称查询
     */
    @RequestMapping("deviceCheck/search_deviceCheck_by_deviceName")
    @ResponseBody
    public PageBean<DeviceCheck> searchDeviceCheckByDeviceCheckName(String searchValue,int page,int rows) {
        List<DeviceCheck> deviceChecks = deviceCheckService.searchDeviceCheckByDeviceCheckName(searchValue,page,rows);
        PageInfo<DeviceCheck> PageInfo = new PageInfo<>(deviceChecks);
        PageBean<DeviceCheck> pageBean = new PageBean<>(deviceChecks, PageInfo.getTotal());
        return pageBean;
    }
}

