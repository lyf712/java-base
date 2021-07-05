package com.lyf.dao.mapper;

import com.lyf.dao.domain.Permission;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionMapper {

    boolean addPermission(Permission permission);
    boolean deletePermissionById(Permission permission);

}
