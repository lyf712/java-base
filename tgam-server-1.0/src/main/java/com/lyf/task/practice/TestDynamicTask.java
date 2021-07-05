package com.lyf.task.practice;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @AUTHOR LYF
 * @DESC
 *
 */

class task implements Runnable{

    int count=0;
    @Override
    public void run() {
        count++;
        System.out.println(count);
    }
}

@EnableScheduling
public class TestDynamicTask {

    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    // 检查  key (redis key),val (status)
    private HashMap<String,String> checkMap = new HashMap<>();

    @Scheduled(fixedRate = 1000)
    public void recordDataCheck(String username){
        task task1 = new task();

    }







}
