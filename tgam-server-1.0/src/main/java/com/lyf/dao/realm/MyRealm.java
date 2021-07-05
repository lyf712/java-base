package com.lyf.dao.realm;

import com.lyf.dao.domain.User;
import com.lyf.service.impl.UserManageServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * @AUTHO LYF
 * @DATE 2021-01-31
 * @DESC shiro realm
 *
 */

    // 权限认证自定义Realm用于查询用户的角色和权限信息并保存到权限管理器
public class MyRealm extends AuthorizingRealm {


        @Autowired
        private UserManageServiceImpl loginService;

        @Override
        protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

            // 查询用户名
            String name = (String)principalCollection.getPrimaryPrincipal();
            System.out.println("Realm=>"+name);

            // 根据用户名查询用户
            User usr = loginService.getUserByUserName(name);

            System.out.println("MyRealm授权处:"+usr);

            // 添加角色和权限
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            // 添加角色（名称）



//            for(Role role: usr.getRoles()){

              //  simpleAuthorizationInfo.addRole(role.getRoleName());

                // 添加权限 (暂时不考虑)
//                for(Permissions permissions:role.getPermissions()){
//                    // 添加的权限名称（也可权限对象）
//                    simpleAuthorizationInfo.addStringPermission(permissions.getPermissionName());
//                }

//            }
            return simpleAuthorizationInfo;
        }

        @Override
        protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
            System.out.println("Authentication"+authenticationToken.getPrincipal());

            if(StringUtils.isEmpty(authenticationToken.getPrincipal())){
                return null;
            }

            String name = authenticationToken.getPrincipal().toString();
            User user = loginService.getUserByUserName(name);

            System.out.println("Authentication"+user);

            if(user == null){
                return  null;
            }else{
                // 验证
                //  SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(name,user.getPassword());
                SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name,user.getPassword(),getName());
                return simpleAuthenticationInfo;
            }
        }
}


