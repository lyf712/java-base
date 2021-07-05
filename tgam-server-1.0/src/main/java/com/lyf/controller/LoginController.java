package com.lyf.controller;

import com.alibaba.fastjson.JSONObject;
import com.lyf.service.LoginService;
import com.lyf.utils.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;


/***
 * @Author lyf
 * @Date 2020-12-12
 * @Version 1.0
 * @DESC
 * 一、登录控制类
 * 1.账号密码登录
 * 2.短信验证登录
 *
 * 二、其他说明
 */


@Controller
public class LoginController {

    @Autowired
    LoginService loginService;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);// 若引入lombok 就可直接在注解即可

    /**
     * 用户名和密码登录
     * @param req
     * @return
     */

    @RequestMapping(value = "login",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    @CrossOrigin //, @RequestHeader HttpHeaders headers
    public JSONObject login(@RequestBody JSONObject req, @RequestHeader("User-Agent") String type){

        /*日志记录*/
        logger.info("用户密码登录控制层获取数据"+req);

        /*声明响应JSONObject*/
        JSONObject resp;
        JSONObject data = new JSONObject();

        /*获取相应数据*/
        String userName = req.getString("username");
        String password = req.getString("password");

        // 代理设备应该放在请求头中...
        String deviceType = type;

        System.out.println("请求设备为:"+deviceType);

        /*检查参数缺失*/
        if(userName==null||password==null||deviceType==null){
            data.put("detail","参数缺失");
            resp = Result.ERROR(data);
            return resp;
        }

        data = loginService.loginService(userName,password,deviceType);
        resp = Result.OK(data);
        return resp;
    }


    /**
     * 短信登录验证
     * 1.发送手机号过来
     * 2.返回验证码，60s有效，redis设置60s有效期
     * @param req
     * @return
     */


    /*点击获取验证码接口*/
    @RequestMapping("loginBySMS")
    @ResponseBody
    @CrossOrigin
    public JSONObject loginBySMS(@RequestBody JSONObject req){

        JSONObject resp;
        JSONObject data = new JSONObject();

        String phone = req.getString("number");
        boolean flag = loginService.loginBySMS(phone);

        if(flag){
            data.put("msg","已成功发送");
            data.put("status","1");
            resp = Result.OK(data);
        }else {
            resp = Result.ERROR();
        }
        return resp;
    }

    /*登录确认验证*/
    @PostMapping("verifyCode")
    @ResponseBody
    @CrossOrigin
    public JSONObject verifyCode(@RequestBody JSONObject jsonObject){

        JSONObject resp;
        JSONObject data = new JSONObject();
        String number = jsonObject.getString("number");
        String code = jsonObject.getString("code");

        String result=  loginService.checkCode(number,code);

        if("验证码失效".equals(result))
        {
            data.put("msg",result);
            data.put("code","0");
        }else if("验证成功".equals(result)){
            data.put("msg","验证成功");
            data.put("code","1");
        }else if("验证码有误".equals(result)){
            data.put("message","验证码有误");
            data.put("code","1");
        }
        resp = Result.OK(data);
        return resp;
    }
}
