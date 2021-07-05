package com.lyf.dao.mapper;

import com.lyf.dao.domain.RolePermission;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionMapper {

    boolean addRolePermission(RolePermission rolePermission);
    boolean deleteRole(RolePermission rolePermission);
}
