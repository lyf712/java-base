package com.lyf.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lyf.controller.vomain.UserVo;
import com.lyf.dao.domain.User;
import com.lyf.dao.mapper.UserMapper;
import com.lyf.sdk.Sms;
import com.lyf.service.LoginService;
import com.lyf.utils.authority.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.Random;

/**
 * @AUTHOR LYF
 * @DESC
 * 登录service层
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    StringRedisTemplate redisTemplate;
    private Logger logger = LoggerFactory.getLogger(LoginService.class);


    /*用户名密码登录服务层*/
    @Override
    public JSONObject loginService(String userName, String password, String deviceType) {

        logger.info("用户密码登录服务层接受到数据为:"+userName+" "+password+" "+deviceType);

        /*loginService的处理结果返回*/
        JSONObject handleResult = new JSONObject();

        User user = userMapper.queryByUserName(userName);

        if(user!=null){
            // 检查登录设备？
            if(password.equals(user.getPassword())){//密码正确
                logger.info(userName+"成功登录");

                UserVo userVo = new UserVo(); // 返回用户信息
                userVo.setRealName(user.getRealName());
                userVo.setAge(user.getAge());
                userVo.setJob(user.getJob());
                userVo.setWeight(user.getWeight());
                userVo.setHeight(user.getHeight());
                userVo.setBirthday(user.getBirthday());
                userVo.setIdNumber(user.getIdNumber());
                userVo.setHeadImg(user.getHeadImg());

                handleResult.put("msg","登录成功");
                handleResult.put("code",1);
                handleResult.put("userInfo",userVo);  // 密码正确才返回用户信息

                // 放入token
                try{

                    String tokenStr = JwtUtil.createToken(userName,password);
                    handleResult.put("token",tokenStr);
                   // redisTemplate.opsForValue().set(String.valueOf(user.getId()),user.getPassword());

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else{

                handleResult.put("msg","密码错误");
                handleResult.put("code",0);
            }
        }else{// 无此用户
            handleResult.put("msg","无此用户");
            handleResult.put("code",0);
        }
        return  handleResult;
    }

    @Override
    public boolean loginBySMS(String number) {
        Random random = new Random();

        String code = ""+random.nextInt(9)+
                random.nextInt(9)+ random.nextInt(9)
                + random.nextInt(9);

        Sms sms  = new Sms();

        redisTemplate.opsForValue().set("code:"+number,code);
        // 60s有效期
        redisTemplate.expire("code:"+number, Duration.ofSeconds(60));

        // 发送登录信息
        logger.info(number+"发验证码"+code);

        sms.sendSms(number,code);

        return true;//返回验证码
    }

    @Override
    public String checkCode(String number,String inputCode) {
        String realCode = redisTemplate.opsForValue().get("code:"+number);
        if(realCode==null){
            return "验证码失效";
        }
        if(realCode.equals(inputCode)){
            return "验证成功";
        }
        return "验证码有误";
    }


}
