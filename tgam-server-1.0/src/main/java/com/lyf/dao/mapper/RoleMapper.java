package com.lyf.dao.mapper;

import com.lyf.dao.domain.Role;
import org.springframework.stereotype.Repository;


/**
 * @AUTHOR LYF
 *
 * roleMapper
 * @DESC
 * （1）添加角色
 * （2）删除角色
 *
 *
 *
 */

@Repository
public interface RoleMapper {
    boolean addRole(Role role);
    boolean deleteRoleById(String roleId);
}
