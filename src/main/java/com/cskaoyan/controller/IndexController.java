package com.cskaoyan.controller;

import com.cskaoyan.bean.ActiveUser;
import com.cskaoyan.bean.SysUser;
import com.cskaoyan.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    SysUserService sysUserService;
    @RequestMapping(value = {"index","/"})
    public String index(){
        return "login";
    }

    @RequestMapping(value = "home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value = "ajaxLogin")
    @ResponseBody
    public HashMap login(HttpServletRequest request,String username,String password,String randomcode){
        String realCode = (String)request.getSession().getAttribute("validateCode");
        HashMap map = new HashMap();
        String msg = sysUserService.loginCheck(username,password);
        if("true".equals(msg)&&!randomcode.equals(realCode)){
            msg="randomcode_error";
        }
        map.put("msg",msg);
        if("true".equals(msg)) {
            ActiveUser user = sysUserService.getUser(username,password);
            request.getSession().setAttribute("activeUser", user);
        }
        return map;
    }
    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest request){
        request.getSession().setAttribute("activeUser", null);

        return "login";
    }
}
