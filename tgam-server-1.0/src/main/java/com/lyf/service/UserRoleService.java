package com.lyf.service;

import com.lyf.dao.domain.UserRole;

import java.util.List;

public interface UserRoleService {

    boolean addUserRole(UserRole userRole);

    List<UserRole> getRolesByUserId(String userId);

}
