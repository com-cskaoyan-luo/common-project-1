package com.cskaoyan.controller;

import com.cskaoyan.bean.*;
import com.cskaoyan.service.DeviceTypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class DeviceTypeController {
    @Autowired
    DeviceTypeService deviceTypeService;

    @RequestMapping("device/deviceType")
    public String findDeviceType(HttpServletRequest request) {
        List<String> list = new ArrayList<>();
        list.add("deviceType:add");
        list.add("deviceType:edit");
        list.add("deviceType:delete");

        request.getSession().setAttribute("sysPermissionList",list);
        return "deviceType";
    }

    @RequestMapping("deviceType/list")
    @ResponseBody
    public PageBean<DeviceType> list(int page, int rows) {
        List<DeviceType> DeviceTypes = deviceTypeService.findAllDeviceType(page,rows);
        //查询到的数据给到PageInfo ，只需要把结果集给到该对象，获取分页信息
        // 就可以通过该对象get方法拿到总页数，总记录数，等等你想要的数据
        PageInfo<DeviceType> pageInfo=new PageInfo<>(DeviceTypes);
        //根据前台需要在自定义一个分页对象
        //我的本次项目只需要传入页面需要的list集合，和total，同时json形式返回
        PageBean<DeviceType> pageBean=new PageBean<>(DeviceTypes,pageInfo.getTotal());

        //把该对象json返回
        return  pageBean;
    }

    @RequestMapping("deviceType/get/{id}")
    @ResponseBody
    public DeviceType getDataByDeviceType(@PathVariable String id) {

        DeviceType deviceType = deviceTypeService.getDataByDeviceType(id);
        return deviceType;
    }

    @RequestMapping("DeviceTypeType/get_data")
    @ResponseBody
    public List<DeviceType> getDataByDeviceTypeList() {
        List<DeviceType> DeviceTypes = deviceTypeService.getDataByDeviceTypeList();
        return DeviceTypes;
    }
    /*
    @RequestMapping("department/get_data")
    @ResponseBody
    public List<Department> getDataByDepartment() {
        List<Department> DeviceTypes = DeviceTypeService.getDataByDepartment();
        return DeviceTypes;
    }

    @RequestMapping("DeviceTypeList/get_data")
    @ResponseBody
    public List<DeviceType> getDataByDeviceTypeList() {
        List<DeviceType> DeviceTypes = DeviceTypeService.getDataByDeviceTypeList();
        return DeviceTypes;
    }

    */

    // 增加判断(点击“增加”按钮)
    @RequestMapping("deviceType/add_judge")
    @ResponseBody
    public String addJudge() {
        return "button";
    }

    @RequestMapping("deviceType/add")
    public String add() {
        return "deviceType_add";
    }

    // 增
    @RequestMapping("deviceType/insert")
    @ResponseBody
    public Map insert(DeviceType record) {
        int i = deviceTypeService.insert(record);
        HashMap<String, String> map = new HashMap<>();
        int status = 200;
        if (i == 1) {
            map.put("status",status + "");
        }
        return map;
    }

    //删
    //删除按钮
    @RequestMapping("deviceType/delete_judge")
    @ResponseBody
    public String deleteJudge() {
        return "deleteButton";
    }

    @RequestMapping("deviceType/delete_batch")
    @ResponseBody
    public Map deleteByExample(String ids) {
        String[] split = ids.split(",");
        List<String> strings = Arrays.asList(split);
        int i = deviceTypeService.deleteByExample(strings);

        HashMap<String, String> map = new HashMap<>();
        int status = 200;
        if (i == 1) {
            map.put("status",status + "");
        }
        return map;
    }

    //改
    @RequestMapping("deviceType/edit_judge")
    @ResponseBody
    public String editJudge() {
        return "editButton";
    }

    @RequestMapping("deviceType/edit")
    public String edit() {
        return "deviceType_edit";
    }

    @RequestMapping("deviceType/update")
    @ResponseBody
    public Map updateByPrimaryKeySelective(DeviceType record) {
        int i = deviceTypeService.updateByPrimaryKeySelective(record);
        HashMap<String, String> map = new HashMap<>();
        int status = 200;
        if (i == 1) {
            map.put("status",status + "");
        }
        return map;
    }

    // 从DeviceController过来的updateAll
    @RequestMapping("deviceType/update_all")
    @ResponseBody
    public Map updateAll(DeviceType record) {
        int i = deviceTypeService.updateByPrimaryKeySelective(record);
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
    @RequestMapping("deviceType/search_deviceType_by_deviceTypeId")
    @ResponseBody
    public PageBean<DeviceType> searchDeviceTypeByDeviceTypeId(String searchValue,int page,int rows) {
        List<DeviceType> deviceTypes = deviceTypeService.searchDeviceTypeByDeviceTypeId(searchValue,page,rows);
        PageInfo<DeviceType> PageInfo = new PageInfo<>(deviceTypes);
        PageBean<DeviceType> pageBean = new PageBean<>(deviceTypes, PageInfo.getTotal());
        return pageBean;
    }

    /**
     * 模糊查询之根据设备名称查询
     */
    @RequestMapping("deviceType/search_deviceType_by_deviceTypeName")
    @ResponseBody
    public PageBean<DeviceType> searchDeviceTypeByDeviceTypeName(String searchValue,int page,int rows) {
        List<DeviceType> deviceTypes = deviceTypeService.searchDeviceTypeByDeviceTypeName(searchValue,page,rows);
        PageInfo<DeviceType> PageInfo = new PageInfo<>(deviceTypes);
        PageBean<DeviceType> pageBean = new PageBean<>(deviceTypes, PageInfo.getTotal());
        return pageBean;
    }
}
