package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/14
 * @VERSION 1.0
 * @DESC
 */
@RestController
public class TestController {
    @GetMapping("/hello")
    String test(){
        System.out.println("进入Provider");
        return "hello";}

}
