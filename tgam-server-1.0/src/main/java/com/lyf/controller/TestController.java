package com.lyf.controller;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lyf.sercurity.LoginToken;
import com.lyf.sercurity.PassToken;
import com.lyf.sercurity.SecurityParameter;
import com.lyf.sercurity.TestAnnotation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;


import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


@Controller
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @PassToken
    @PostMapping("testParam")
    @ResponseBody
    JSONObject testParam(@RequestBody JSONObject jsonObject){
        System.out.println("打印数据"+jsonObject);
        logger.error("test_error:"+jsonObject);
        logger.trace("test_trace");
        logger.debug("test_debug");
        logger.info("记录数据"+jsonObject);
        return jsonObject;
    }

    @LoginToken
    @PostMapping("testHttp")
    @ResponseBody
    JSONArray testHttp(HttpServletRequest request){

        JSONArray jsonArray = new JSONArray();
        System.out.println(request.getContentType());
        request.getAttribute("name");
        jsonArray.add(request.getAttribute("name"));
        jsonArray.add(request.getAttribute("data"));
        System.out.println("param:"+request.getParameter("command"));
        System.out.println("attribute:"+request.getAttribute("command"));

        return jsonArray;
    }


    @PostMapping("testRecordData")
    @ResponseBody
    JSONObject testRecordData(@RequestBody JSONObject jsonObject){

        logger.info("测试recordData:"+jsonObject);
        System.out.println("接受到的数据为:"+jsonObject);

        return jsonObject;
    }


    @PostMapping(value = "testPost",produces = "application/json")
    @ResponseBody
    public String testPost(@RequestBody JSONObject jsonObject){
        String userId = jsonObject.getString("userId");
        String password = jsonObject.getString("password");
        logger.info("userId and password"+userId+password);
        System.out.println(userId+";"+password);
        return "ok";
    }

    @RequestMapping("/testParam")
    @ResponseBody
    public String testParam(){
        System.out.println("yes");
        return "OK";
    }

    @RequestMapping("/testAnnotation")
    @ResponseBody
    @TestAnnotation
    public String testAnnotation(Object o){

        HandlerMethod handlerMethod = (HandlerMethod) o;
        Method method = handlerMethod.getMethod();
        if(method.isAnnotationPresent(TestAnnotation.class)){
            return "yes";
        }
        return "no";
    }

    @RequestMapping("/testInCode")
    @ResponseBody
    @SecurityParameter
    public  String testInCode(@RequestBody Object o,@RequestHeader Object o2){
        System.out.println("InCode:"+o);
        return o.toString();
    }


    /**
     * shiroTest
     * https://www.infoq.com/articles/apache-shiro/
     */

    @RequiresRoles("admin")
    @LoginToken
    @ResponseBody
    @GetMapping("/admin")
    public String admin() {
        return "admin success!";
    }

    @RequiresPermissions("query")
    @LoginToken
    @ResponseBody
    @GetMapping("/index")
    public String index() {
        return "index success!";
    }

    @RequiresPermissions("addUser")
    @LoginToken
    @ResponseBody
    @GetMapping("/add")
    public String add() {
        return "add success!";
    }

    @RequiresPermissions("deleteUser")
    @GetMapping("/delete")
    @ResponseBody
    public String delete() {
        return "add success!";
    }

}
