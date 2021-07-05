package com.lyf.service;

import com.alibaba.fastjson.JSONObject;

public interface LoginService {
     JSONObject loginService(String userId, String password, String deviceType);
     boolean  loginBySMS(String number);
     String checkCode(String number,String inputCode);
}
