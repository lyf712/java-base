package com.lyf.controller.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lyf.dao.domain.User;
import com.lyf.sercurity.LoginToken;
import com.lyf.service.UserManageService;
import com.lyf.utils.result.Result;
import com.mysql.cj.util.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * @AUTHOR LYF
 * @DATE 2021 2-03
 * @DESC 用户管理
 *
 * 1.添加用户：（1）注册用户（2）管理员添加
 * 2.修改用户（管理员和用户）： （1）用户修改基本信息 （2）管理员修改权限、角色
 * 3.删除用户（管理员）
 * 4.
 *
 */


@RestController
@RequestMapping("userManager")
public class ManageUserController {

    @Autowired
    UserManageService userManageService;
    Logger logger= LoggerFactory.getLogger(ManageUserController.class);


    /*查询所有用户*/
//    @RequiresRoles("admin")
    @LoginToken// 需要管理员用户才能查询所有用户信息
    @CrossOrigin
    @RequestMapping(value = "queryAll",method = RequestMethod.POST,produces = "application/json")
    JSONObject queryAllUsers(){

        /*声明响应JSONObject*/
        JSONObject resp;
        JSONObject data = new JSONObject();

        // 所有用户信息
        JSONArray usersArr = userManageService.getAllUsers();
        data.put("detail",usersArr);

        resp = Result.OK(data);
        return resp;
    }



//    @RequiresRoles("admin")
    /*通过姓名查询用户，前端其实可以在查询完所有之后查询某个*/
    @LoginToken
    @RequestMapping(value = "queryUserByName",method = RequestMethod.POST,produces = "application/json")
    @CrossOrigin
    JSONObject getUserByUserName(@RequestBody JSONObject jsonObject){ //String userName


        /*声明响应JSONObject*/
        JSONObject resp;
        JSONObject data = new JSONObject();
        String userName = jsonObject.getString("username");

        if(StringUtils.isNullOrEmpty(userName)){resp = Result.parameterError(); return resp;}

        data.put("detail",userManageService.getUserByUserName(userName));

        resp = Result.OK(data);
        return resp;
    }

//    @RequiresRoles("admin")
    @LoginToken
    @RequestMapping(value = "queryUserById",method = RequestMethod.POST,produces = "application/json")
    @CrossOrigin
    JSONArray getUserByUserId(@RequestBody JSONObject jsonObject){ //String userId

        String userId = jsonObject.getString("userID");

        JSONArray jsonArray = new JSONArray();
        Map<String,User> map = new HashMap<>();

        map.put("user",userManageService.getUserByUserId(Long.valueOf(userId)));

        jsonArray.add(map);
        return jsonArray;
    }



    @LoginToken
    @RequestMapping(value = "insertOne",method = RequestMethod.POST,produces = "application/json")
    @CrossOrigin
    JSONArray insertUser(@RequestBody JSONObject jsonObject){ //User user

        User user = JSON.parseObject(jsonObject.toJSONString(),User.class);
        String result= null;
        JSONArray jsonArray = new JSONArray();
        Map<String,Integer> map = new HashMap<>();
        Map<String,String> map1 = new HashMap<>();


        if(user.getUserName()!=null){ // 不为空才进行插入此用户
            result   =  userManageService.insertUser(user);

            if("已注册".equals(result)){  //userManageService.insertUser(user)
                map.put("code",0);
            }else {
                map.put("code",1);
            }

        }else{

            map.put("code",0);
            result = "无效插入,未输入用户名";
        }

        map1.put("message",result); //userManageService.insertUser(user)
        jsonArray.add(map);
        jsonArray.add(map1);

        return jsonArray;
    }

    @LoginToken
    @RequestMapping(value = "deleteUser",method = RequestMethod.POST,produces = "application/json")
    @CrossOrigin
    JSONArray deleteUserByUserName(@RequestBody JSONObject jsonObject){
        JSONArray jsonArray = new JSONArray();
        User user = JSONObject.parseObject(String.valueOf(jsonObject),User.class);
        if(user.getUserName()!=null){
            jsonArray = userManageService.deleteByUserName(user.getUserName());
        }else {
            Map<String,String> map = new HashMap<>();
            Map<String,String> map1 = new HashMap<>();
            map.put("code","0");
            map1.put("message","无效用户名");
            jsonArray.add(map);
            jsonArray.add(map1);
        }
        return jsonArray;
    }

    @LoginToken
    @RequestMapping(value = "updateUser",method = RequestMethod.POST,produces = "application/json")
    @CrossOrigin
    JSONArray update(@RequestBody JSONObject jsonObject){

        User user = JSONObject.parseObject(String.valueOf(jsonObject),User.class);

        JSONArray jsonArray =  userManageService.updateUser(user);

        return jsonArray;
    }


}
