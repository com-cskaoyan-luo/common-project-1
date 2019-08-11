package com.cskaoyan.controller;

import com.cskaoyan.bean.DeviceFault;
import com.cskaoyan.bean.DeviceFault;
import com.cskaoyan.bean.PageBean;
import com.cskaoyan.service.DeviceFaultService;
import com.cskaoyan.service.DeviceFaultService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class DeviceFaultController {
    @Autowired
    DeviceFaultService deviceFaultService;

    @RequestMapping("device/deviceFault")
    public String findDeviceFault(HttpServletRequest request) {
        List<String> list = new ArrayList<>();
        list.add("deviceFault:add");
        list.add("deviceFault:edit");
        list.add("deviceFault:delete");

        request.getSession().setAttribute("sysPermissionList",list);
        return "deviceFault";
    }

    @RequestMapping("deviceFault/list")
    @ResponseBody
    public PageBean<DeviceFault> list(int page, int rows) {
        List<DeviceFault> deviceFaults = deviceFaultService.findAllDeviceFault(page,rows);
        //查询到的数据给到PageInfo ，只需要把结果集给到该对象，获取分页信息
        // 就可以通过该对象get方法拿到总页数，总记录数，等等你想要的数据
        PageInfo<DeviceFault> pageInfo=new PageInfo<>(deviceFaults);
        //根据前台需要在自定义一个分页对象
        //我的本次项目只需要传入页面需要的list集合，和total，同时json形式返回
        PageBean<DeviceFault> pageBean=new PageBean<>(deviceFaults,pageInfo.getTotal());

        //把该对象json返回
        return  pageBean;
    }

    /*@RequestMapping("deviceType/get_data")
    @ResponseBody
    public List<DeviceType> getDataByDeviceType() {

        List<DeviceType> deviceTypes = deviceFaultService.getDataByDeviceType();
        return deviceTypes;
    }*/
    /*
    @RequestMapping("DeviceFaultType/get_data")
    @ResponseBody
    public List<DeviceFaultType> getDataByDeviceFaultType() {
        List<DeviceFaultType> DeviceFaults = DeviceFaultService.getDataByDeviceFaultType();
        return DeviceFaults;
    }

    @RequestMapping("department/get_data")
    @ResponseBody
    public List<Department> getDataByDepartment() {
        List<Department> DeviceFaults = DeviceFaultService.getDataByDepartment();
        return DeviceFaults;
    }

    @RequestMapping("DeviceFaultList/get_data")
    @ResponseBody
    public List<DeviceFault> getDataByDeviceFaultList() {
        List<DeviceFault> DeviceFaults = DeviceFaultService.getDataByDeviceFaultList();
        return DeviceFaults;
    }

    */

    // 增加判断(点击“增加”按钮)
    @RequestMapping("deviceFault/add_judge")
    @ResponseBody
    public String addJudge() {
        return "button";
    }

    @RequestMapping("deviceFault/add")
    public String add() {
        return "deviceFault_add";
    }

    // 增
    @RequestMapping("deviceFault/insert")
    @ResponseBody
    public Map insert(DeviceFault record) {
        int i = deviceFaultService.insert(record);
        HashMap<String, String> map = new HashMap<>();
        int status = 200;
        if (i == 1) {
            map.put("status",status + "");
        }
        return map;
    }

    //删
    //删除按钮
    @RequestMapping("deviceFault/delete_judge")
    @ResponseBody
    public String deleteJudge() {
        return "deleteButton";
    }

    @RequestMapping("deviceFault/delete_batch")
    @ResponseBody
    public Map deleteByExample(String ids) {
        String[] split = ids.split(",");
        List<String> strings = Arrays.asList(split);
        int i = deviceFaultService.deleteByExample(strings);

        HashMap<String, String> map = new HashMap<>();
        int status = 200;
        if (i == 1) {
            map.put("status",status + "");
        }
        return map;
    }

    //改
    @RequestMapping("deviceFault/edit_judge")
    @ResponseBody
    public String editJudge() {
        return "editButton";
    }

    @RequestMapping("deviceFault/edit")
    public String edit() {
        return "deviceFault_edit";
    }

    @RequestMapping("deviceFault/update")
    @ResponseBody
    public Map updateByPrimaryKeySelective(DeviceFault record) {
        int i = deviceFaultService.updateByPrimaryKeySelective(record);
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
    @RequestMapping("deviceFault/search_deviceFault_by_deviceFaultId")
    @ResponseBody
    public PageBean<DeviceFault> searchDeviceFaultByDeviceFaultId(String searchValue,int page,int rows) {
        List<DeviceFault> deviceFaults = deviceFaultService.searchDeviceFaultByDeviceFaultId(searchValue,page,rows);
        PageInfo<DeviceFault> PageInfo = new PageInfo<>(deviceFaults);
        PageBean<DeviceFault> pageBean = new PageBean<>(deviceFaults, PageInfo.getTotal());
        return pageBean;
    }

    /**
     *  模糊查询之根据设备名称查询
     */
    @RequestMapping("deviceFault/search_deviceFault_by_deviceName")
    @ResponseBody
    public PageBean<DeviceFault> searchDeviceFaultByDeviceFaultName(String searchValue,int page,int rows) {
        List<DeviceFault> deviceFaults = deviceFaultService.searchDeviceFaultByDeviceFaultName(searchValue,page,rows);
        PageInfo<DeviceFault> PageInfo = new PageInfo<>(deviceFaults);
        PageBean<DeviceFault> pageBean = new PageBean<>(deviceFaults, PageInfo.getTotal());
        return pageBean;
    }
}
