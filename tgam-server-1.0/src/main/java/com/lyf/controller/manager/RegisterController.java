package com.lyf.controller.manager;

import com.alibaba.fastjson.JSONObject;
import com.lyf.dao.domain.User;
import com.lyf.dao.domain.UserRole;
import com.lyf.dao.mapper.RecordDataTableMapper;
import com.lyf.service.UserManageService;
import com.lyf.service.UserRoleService;
import com.lyf.utils.result.Result;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * 注册控制类
 *
 * 注册用户 ：
 * （1）user表添加信息
 * （2）user_role进行权限关联
 * （3）创建用户数据表 z_record_data_userId  <=数据表名
 */

@RestController
@RequestMapping("/userManager")
public class RegisterController {


    @Autowired
    UserManageService userManageService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    RecordDataTableMapper recordTableMapper;

    @RequestMapping("/register")
    JSONObject userRegister(@RequestBody JSONObject req){
        JSONObject resp;
        JSONObject data = new JSONObject();
        System.out.println(req);

        User user = JSONObject.parseObject(req.toJSONString(),User.class);
        System.out.println("user:"+user);
        String result= null;

        if(!StringUtils.isNullOrEmpty(user.getUserName())){ // 不为空才进行插入此用户
            result   =  userManageService.insertUser(user);
            if("该用账号名已存在".equals(result)){  //userManageService.insertUser(user)

              data.put("detail","该账户名已注册");
              data.put("code","0");
              resp = Result.OK(data);
              return resp;
            }else { // 此时已添加进用户表

                /*添加权限*/
                User newUser = userManageService.getUserByUserName(user.getUserName());

                Long userId = newUser.getId();

                UserRole userRole = new UserRole(null,userId+"",1002+"");//先默认为普通用户，

                //考虑权限分配成功与否
                userRoleService.addUserRole(userRole);

                /*分配新数据表，考虑分配成功与否*/
                recordTableMapper.createTable("z_record_data_"+userId);

                data.put("detail","注册成功！");
                data.put("code","1");
                resp = Result.OK(data);
                return resp;

            }
        }else{
            data.put("detail","无效插入");
            data.put("code","0");
            resp = Result.ERROR(data);
            return resp;
        }
        //return resp;
    }
}
