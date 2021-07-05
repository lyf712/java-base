package com.lyf.utils.result;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lyf.utils.TimeUtil;

import java.io.Serializable;


/**
 * @AUTHOR LYF
 * @DATE 2021-01-28
 * @param <T>
 *
 * @DESCRITION
 * 返回前端数据对象
 *
 * [
 * "code":0
 * "msg":""
 * "data":{
 *
 * }
 * ]
 *
 *
 */


public class Result<T> implements Serializable {

    //序列化问题:https://blog.csdn.net/u014750606/article/details/80040130
    private static final long serialVersionUID = 5152036363051050007L;

    /**
     * 状态码
     */
    private String code  = "0";
    /**
     * 信息
     */
    private String msg ;

    /**
     * 返回数据
     * 泛型编程！！查
     */
    private T data;

    public <T> Result(){}

    public Result(String code,String msg){this.code =code;this.msg=msg;}

    public Result(String code,String msg,T data){
        this.code =code;
        this.msg=msg;
        this.data=data;
    }

    public static JSONArray ok(){
        JSONArray resOk = new JSONArray();
        return resOk;
    }

    // 通用
    public static JSONObject resp(String code,String msg,JSONObject data){
        JSONObject resOk = new JSONObject();
        resOk.put("code",code);
        resOk.put("msg",msg);
        resOk.put("data",data);
        return resOk;
    }
    // 操作成功
    public static JSONObject OK(JSONObject data){
        JSONObject resOk = new JSONObject();
        resOk.put("responseTime", TimeUtil.getAllTime());
        resOk.put("code","1");
        resOk.put("msg","操作成功");
        resOk.put("data",data);
        return resOk;
    }

    // 操作失败
    public static JSONObject ERROR(){
        JSONObject resOk = new JSONObject();
        resOk.put("responseTime", TimeUtil.getAllTime());
        resOk.put("code","0");
        resOk.put("msg","操作失败");
        return resOk;
    }

    // 操作失败
    public static JSONObject ERROR(JSONObject data){
        JSONObject resOk = new JSONObject();
        resOk.put("responseTime", TimeUtil.getAllTime());
        resOk.put("code","0");
        resOk.put("msg","操作失败");
        resOk.put("data",data);
        return resOk;
    }

    // 内部异常
    public static JSONObject EXCEPTION(){
        JSONObject resOk = new JSONObject();
        resOk.put("responseTime", TimeUtil.getAllTime());
        resOk.put("code","-1");
        resOk.put("msg","系统内部异常");
        return resOk;
    }

    // 内部异常
    public static JSONObject EXCEPTION(String detail){
        JSONObject resOk = new JSONObject();
        resOk.put("responseTime", TimeUtil.getAllTime());
        resOk.put("code","-1");
        resOk.put("msg","系统内部异常");
        resOk.put("detail",detail);
        return resOk;
    }


    // 参数错误
    public static JSONObject parameterError(){
        JSONObject resOk = new JSONObject();
        resOk.put("responseTime", TimeUtil.getAllTime());
        resOk.put("code","1001");
        resOk.put("msg","参数错误");
        return resOk;
    }

    // 内部异常
    public static JSONObject noParameter(){
        JSONObject resOk = new JSONObject();
        resOk.put("responseTime", TimeUtil.getAllTime());
        resOk.put("code","1002");
        resOk.put("msg","参数缺失");
        return resOk;
    }

    // 没有权限
    public static JSONObject noAuth(){
        JSONObject resOk = new JSONObject();
        resOk.put("responseTime", TimeUtil.getAllTime());
        resOk.put("code","1003");
        resOk.put("msg","没有权限");
        return resOk;
    }




}
