package com.bta.crm.settings.dao;

import com.bta.crm.settings.domain.User;

import java.util.Map;

public interface UserDao {
    public User login(Map<String, String> map);
}
