package com.lyf.task;


import com.lyf.controller.data.RecordDataController;
import com.lyf.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 *
 * https://blog.csdn.net/weixin_37591536/article/details/82150933
 */
@Component
@EnableScheduling
public class RecordDataTask {

    public static  int count = 0;
    public static  int timeOut = 60;
    public static  int command = 0;//控制位 1为正在传输数据控制位 其他不计数

  //  private static RecordService recordService;
//
    @Autowired
    RecordService recordService;


    // 一旦计数成功timeOut则为超时断开连接flag为表示未在记录,且数据保存在Redis中
    @Bean
    @Scheduled(fixedRate = 2000)
     public void checkTimeOut(){

        if(command == 1){
            count+=2;
            System.out.println("计数:"+count);
        }//计数
        if(count >= timeOut) { //
            System.out.println("超时设置flag=0！！");
            RecordDataController.flag =0;

            command = 0;
            count = 0;
            recordService.UnConnection();

           // return;
        }

    }

}
