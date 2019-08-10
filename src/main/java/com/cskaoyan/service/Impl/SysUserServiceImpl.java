package com.cskaoyan.service.impl;

import com.cskaoyan.bean.ActiveUser;
import com.cskaoyan.bean.SysUser;
import com.cskaoyan.bean.SysUserExample;
import com.cskaoyan.mapper.SysUserMapper;
import com.cskaoyan.service.SysUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    SysUserMapper sysUserMapper;
    @Override
    public String loginCheck(String username, String password) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andUsernameEqualTo(username);
        List<SysUser> list = new ArrayList<>();
        list= sysUserMapper.selectByExample(sysUserExample);

        if(list.size()==0){
            return "account_error";
        }
        SysUser sysUser = list.get(0);
        if(!password.equals(sysUser.getPassword())){
            return "password_error";
        }
        if(sysUser.getLocked()=="0"){
            return "authentication_error";
        }
        return "true";
    }

    @Override
    public ActiveUser getUser(String username, String password) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andUsernameEqualTo(username);
        List<SysUser> list =  sysUserMapper.selectByExample(sysUserExample);
        SysUser sysUser = list.get(0);
        ActiveUser activeUser = new ActiveUser();
        activeUser.setUsername(sysUser.getUsername());
        activeUser.setRolename(this.getRloename(sysUser.getId()));
        return activeUser;
    }

    private String getRloename(@Param("id") String id) {
        return sysUserMapper.getRolenameByUserId(id);
    }
}
