package com.lyf.dao.mapper;

import com.lyf.dao.domain.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleMapper {

    boolean addUserRole(UserRole userRole);

    boolean deleteUserRoleByUserId(String userId);
}
