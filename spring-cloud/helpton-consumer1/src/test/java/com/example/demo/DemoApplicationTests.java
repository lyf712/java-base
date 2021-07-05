package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.Request;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    RestTemplate restTemplate;

    @Test
    void contextLoads() {
     //   restTemplate.postForObject("http://www.baidu.com", ,"","");

    }

}
