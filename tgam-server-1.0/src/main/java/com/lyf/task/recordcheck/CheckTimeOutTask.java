package com.lyf.task.recordcheck;

import org.apache.tomcat.jni.Time;

/**
 * @AUTHOR LYF
 * @DATE 2021/2/22
 * @DESC
 * 检查是否超时
 */
public class CheckTimeOutTask implements Runnable{

    private String userName;// 检查的用户名
    private int timeout;// 超时时间
    private int count=0;// 计数

    public CheckTimeOutTask(String userName, int timeout) {
        this.userName = userName;
        this.timeout = timeout;
    }

    @Override
    public void run() {

        while(count<=timeout){

            // 盘是否超时
            if(count==timeout){
                System.out.println("连接超时");
                // 中断
                CheckTimeoutScheduler.command.replace(userName,"超时连接中断");
                System.out.println(CheckTimeoutScheduler.command.get(userName));
                return;
            }

            if("开启".equals(CheckTimeoutScheduler.command.get(userName))){
                count++;
                System.out.println(Thread.currentThread().getName()+";"+userName+"计数为"+count);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if("暂停".equals(CheckTimeoutScheduler.command.get(userName))){
                System.out.println(userName+"计数为"+count);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
             // 查看指令是否为暂停或者停止，停
         }

    }
}
