package com.bta.crm.settings.service.impl;

import com.bta.crm.exception.loginException;
import com.bta.crm.settings.dao.UserDao;
import com.bta.crm.settings.domain.User;
import com.bta.crm.settings.service.UserService;
import com.bta.crm.utils.DateTimeUtil;
import com.bta.crm.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

public  class UserServiceImpl implements UserService {

    private UserDao userDao= SqlSessionUtil.getSqlSession().getMapper(UserDao.class);


    @Override
    public User login(String loginAct, String loginPwd,String ip) throws loginException {
        Map<String,String> map=new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        User user=userDao.login(map);
        if(user==null)
        {
            throw new loginException("账号密码错误");
        }
        //验证失效时间
        String expireTime=user.getExpireTime();
        String currentTime= DateTimeUtil.getSysTime();
        if(currentTime.compareTo(expireTime)>0)
        {
            throw new loginException("账号已失效");
        }
        //判断锁定状态
        String lockState=user.getLockState();
        if("0".equals(lockState))
        {
            throw new loginException("账号已锁定");
        }
        String allowIps=user.getAllowIps();
        if(!allowIps.contains(ip))
        {
            throw new loginException("ip地址受限");
        }
        return user;
    }
}
