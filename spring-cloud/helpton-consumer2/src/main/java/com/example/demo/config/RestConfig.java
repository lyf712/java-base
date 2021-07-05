package com.example.demo.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/16
 * @VERSION 1.0
 * @DESC
 */
@Configuration
public class RestConfig {

    @Bean
    @LoadBalanced // 负载均衡，，已经封装
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
