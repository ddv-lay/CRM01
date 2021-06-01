package com.bta.crm.settings.web.controller;

import com.bta.crm.settings.domain.User;
import com.bta.crm.settings.service.UserService;
import com.bta.crm.settings.service.impl.UserServiceImpl;
import com.bta.crm.utils.MD5Util;
import com.bta.crm.utils.PrintJson;
import com.bta.crm.utils.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入到用户控制器");
        String servletPath = req.getServletPath();
        if("/settings/user/login.do".equals(servletPath))
        {
            login(req,resp);
        }
        else if("/settings/user/xxx.do".equals(servletPath))
        {

        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("进入到验证登录操作");
        String loginAct=req.getParameter("loginAct");
        String loginPwd=req.getParameter("loginPwd");
        //loginPwd= MD5Util.getMD5(loginPwd);
        String ip=req.getRemoteAddr();
        System.out.println("------ip:"+ip);
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
        try {
            User user = us.login(loginAct, loginPwd,ip);
            req.getSession().setAttribute("user",user);
            PrintJson.printJsonFlag(resp,true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            String msg=e.getMessage();
            Map<String,Object> map=new HashMap<>();
            map.put("msg",msg);
            map.put("success",false);
            PrintJson.printJsonObj(resp,map);
        }
    }
}
