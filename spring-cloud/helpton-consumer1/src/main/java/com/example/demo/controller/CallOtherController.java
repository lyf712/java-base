package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/14
 * @VERSION 1.0
 * @DESC
 */

@RestController
public class CallOtherController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/call/hello")
    String callHello(){
       return restTemplate.getForObject("http://localhost:8082/hello",String.class);
    }

}
