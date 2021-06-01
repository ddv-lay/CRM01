package com.bta.crm.settings.service;

import com.bta.crm.exception.loginException;
import com.bta.crm.settings.domain.User;

public interface UserService {
    User login(String loginAct, String loginPwd,String ip) throws loginException;
}
