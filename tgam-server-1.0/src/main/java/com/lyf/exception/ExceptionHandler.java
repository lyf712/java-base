package com.lyf.exception;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ServerException;
import com.lyf.utils.result.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

//@Slf4j
@ControllerAdvice
public class ExceptionHandler {

    Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseBody
    public String ErrorHandler(AuthorizationException e){
        logger.error("没有通过权限验证",e);
        return "没有权限";
    }


    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseBody
    public String TimeStampHandler(JSONException e){
        logger.error("JSON解析异常",e);
        return "字符分解ERROR";
    }

    //MyBatisSystemException
    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseBody
    public JSONObject MybatisHandler(MyBatisSystemException e){
        JSONObject res = Result.EXCEPTION("数据插入异常");
        return res;
    }


    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseBody
    public String SmsHandler(ServerException e){
        logger.error("发送短信服务异常",e);
        return "发送短信服务异常";
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseBody
    public JSONObject RuntimeHandler(RuntimeException e){
        logger.error("捕获异常",e.getMessage());
        JSONObject resp;
        resp = Result.ERROR();
        resp.put("detail",e.getMessage());
        return resp;
    }

}
