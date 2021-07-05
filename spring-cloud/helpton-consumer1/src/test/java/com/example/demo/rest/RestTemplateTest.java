package com.example.demo.rest;


import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/14
 * @VERSION 1.0
 * @DESC
 * https://www.cnblogs.com/54chensongxia/p/11414923.html
 */
@SpringBootTest
public class RestTemplateTest {
    @Autowired
    RestTemplate restTemplate;
    @Test
    public void test(){

        // 1.普通接口

        // (1) GET方法
        Map<String,String> vars = new HashMap<>();
        vars.put("param","1");
        // getForObject  (url,
        String rs = restTemplate.getForObject("http://www.baidu.com",String.class);//,vars
        //JSONObject jsonRs = restTemplate.getForObject("", JSONObject.class);

        //

        //(2) POST方法
         JSONObject params = new JSONObject();
         ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity("",params,JSONObject.class);
         responseEntity.getStatusCode();//getStatus,Code,Body,Header

        // 通过request请求

        RequestEntity requestEntity = RequestEntity.get("").header("").build();
        restTemplate.exchange(requestEntity,JSONObject.class);

       // restTemplate.execute("","POST");


        System.out.println(rs);

    }
}
