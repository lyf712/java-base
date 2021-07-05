package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


// DiscoveryClient与 Eureka的区别？？
//@EnableDiscoveryClient
//@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient
public class Provider {

    public static void main(String[] args) {
        SpringApplication.run(Provider.class, args);
    }

}
