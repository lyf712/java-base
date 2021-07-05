package com.lyf.service.impl;

import com.lyf.dao.domain.UserRole;
import com.lyf.dao.mapper.UserRoleMapper;
import com.lyf.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleMapper userRoleMapper;
    @Override
    public boolean addUserRole(UserRole userRole) {
        userRoleMapper.addUserRole(userRole);
        return true;
    }

    @Override
    public List<UserRole> getRolesByUserId(String userId) {
        return null;
    }
}
