package com.lyf.task;

import com.lyf.service.WebSocketServer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@EnableScheduling
public class PushToWeb {

    int count=0;

    /*测试向前推送消息*/
//    @Bean
//    @Scheduled(fixedRate = 3000)
//    void test1() throws IOException {
//        count++;
//        WebSocketServer.sendInfo("第"+count+"次推送给您！","Tom22");
//    }

}
