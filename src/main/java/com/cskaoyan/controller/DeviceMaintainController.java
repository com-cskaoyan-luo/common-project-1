package com.cskaoyan.controller;

import com.cskaoyan.bean.DeviceMaintain;
import com.cskaoyan.bean.DeviceMaintain;
import com.cskaoyan.bean.PageBean;
import com.cskaoyan.service.DeviceMaintainService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class DeviceMaintainController {
    @Autowired
    DeviceMaintainService deviceMaintainService;

    @RequestMapping("device/deviceMaintain")
    public String findDeviceMaintain(HttpServletRequest request) {
        List<String> list = new ArrayList<>();
        list.add("deviceMaintain:add");
        list.add("deviceMaintain:edit");
        list.add("deviceMaintain:delete");

        request.getSession().setAttribute("sysPermissionList",list);
        return "deviceMaintain";
    }

    @RequestMapping("deviceMaintain/list")
    @ResponseBody
    public PageBean<DeviceMaintain> list(int page, int rows) {
        List<DeviceMaintain> deviceMaintains = deviceMaintainService.findAllDeviceMaintain(page,rows);
        //查询到的数据给到PageInfo ，只需要把结果集给到该对象，获取分页信息
        // 就可以通过该对象get方法拿到总页数，总记录数，等等你想要的数据
        PageInfo<DeviceMaintain> pageInfo=new PageInfo<>(deviceMaintains);
        //根据前台需要在自定义一个分页对象
        //我的本次项目只需要传入页面需要的list集合，和total，同时json形式返回
        PageBean<DeviceMaintain> pageBean=new PageBean<>(deviceMaintains,pageInfo.getTotal());

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
    @RequestMapping("deviceMaintain/add_judge")
    @ResponseBody
    public String addJudge() {
        return "button";
    }

    @RequestMapping("deviceMaintain/add")
    public String add() {
        return "deviceMaintain_add";
    }

    // 增
    @RequestMapping("deviceMaintain/insert")
    @ResponseBody
    public Map insert(DeviceMaintain record) {
        int i = deviceMaintainService.insert(record);
        HashMap<String, String> map = new HashMap<>();
        int status = 200;
        if (i == 1) {
            map.put("status",status + "");
        }
        return map;
    }

    //删
    //删除按钮
    @RequestMapping("deviceMaintain/delete_judge")
    @ResponseBody
    public String deleteJudge() {
        return "deleteButton";
    }

    @RequestMapping("deviceMaintain/delete_batch")
    @ResponseBody
    public Map deleteByExample(String ids) {
        String[] split = ids.split(",");
        List<String> strings = Arrays.asList(split);
        int i = deviceMaintainService.deleteByExample(strings);

        HashMap<String, String> map = new HashMap<>();
        int status = 200;
        if (i == 1) {
            map.put("status",status + "");
        }
        return map;
    }

    //改
    @RequestMapping("deviceMaintain/edit_judge")
    @ResponseBody
    public String editJudge() {
        return "editButton";
    }

    @RequestMapping("deviceMaintain/edit")
    public String edit() {
        return "deviceMaintain_edit";
    }

    @RequestMapping("deviceMaintain/update")
    @ResponseBody
    public Map updateByPrimaryKeySelective(DeviceMaintain record) {
        int i = deviceMaintainService.updateByPrimaryKeySelective(record);
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
    @RequestMapping("deviceMaintain/search_deviceMaintain_by_deviceMaintainId")
    @ResponseBody
    public PageBean<DeviceMaintain> searchDeviceMaintainByDeviceMaintainId(String searchValue,int page,int rows) {
        List<DeviceMaintain> deviceMaintains = deviceMaintainService.searchDeviceMaintainByDeviceMaintainId(searchValue,page,rows);
        PageInfo<DeviceMaintain> PageInfo = new PageInfo<>(deviceMaintains);
        PageBean<DeviceMaintain> pageBean = new PageBean<>(deviceMaintains, PageInfo.getTotal());
        return pageBean;
    }

    /**
     *  模糊查询之根据设备名称查询
     */
    @RequestMapping("deviceMaintain/search_deviceMaintain_by_deviceFaultId")
    @ResponseBody
    public PageBean<DeviceMaintain> searchDeviceMaintainByDeviceMaintainName(String searchValue,int page,int rows) {
        List<DeviceMaintain> deviceMaintains = deviceMaintainService.searchDeviceMaintainByDeviceMaintainName(searchValue,page,rows);
        PageInfo<DeviceMaintain> PageInfo = new PageInfo<>(deviceMaintains);
        PageBean<DeviceMaintain> pageBean = new PageBean<>(deviceMaintains, PageInfo.getTotal());
        return pageBean;
    }
}
