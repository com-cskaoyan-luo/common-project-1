package com.cskaoyan.service;

import com.cskaoyan.bean.ActiveUser;
import com.cskaoyan.bean.SysUser;

public interface SysUserService {
    String loginCheck(String username, String password);

    ActiveUser getUser(String username, String password);
}
