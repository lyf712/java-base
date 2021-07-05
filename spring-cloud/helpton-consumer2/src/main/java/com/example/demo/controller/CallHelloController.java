package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/16
 * @VERSION 1.0
 * @DESC
 */
@RestController
public class CallHelloController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("call/hello")
    String hello(){
        System.out.println("进入consumer2..");
        return restTemplate.getForObject("http://provider/hello",String.class);} //localhost:8082


}
