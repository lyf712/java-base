package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@SpringBootTest
class DemoApplicationTests {

    @Resource
    RestTemplate restTemplate;

    @Test
    void contextLoads() {
        System.out.println(">>>>>>starting gather>>>>");
        String rs = restTemplate.getForObject("http://www.baidu.com",String.class);

        String url = "http://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=hello" +
                "&fenlei=256&oq=%2526lt%253Bould%2520not%2520autowire.%2520No%2520beans%2520of%2520%2526%252339%253BRestTemplate%2526%252339%253B%2520type%2520found&rsv_pq=ec2aa0d4001a6987&rsv_t=3e1cy8qN6gUefwlKK0wnwNA23%2FwpTQ9L17MJNqXfJs1fU2eCyJ7F9M3B0tA&rqlang=cn&rsv_enter=1&rsv_dl=tb&rsv_btype=t&inputT=752&rsv_sug3=98&rsv_sug1=28&rsv_sug7=101&bs=Could%20not%20autowire.%20No%20beans%20of%20%27RestTemplate%27%20type%20found";


        System.out.println(restTemplate.getForObject(url,String.class));
    }

}
