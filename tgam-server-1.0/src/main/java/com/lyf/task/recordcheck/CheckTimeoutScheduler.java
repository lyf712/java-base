package com.lyf.task.recordcheck;

import org.springframework.stereotype.Component;

import java.util.HashMap;


/**
 * @AUTHOR LYF
 * @DATE 2021/2/22
 * @DESC
 */
@Component
public class CheckTimeoutScheduler {

  //  private ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(8);

    private HashMap<String,Thread> pool = new HashMap();

    public static HashMap<String,String> command = new HashMap();

   public boolean addTask(String userName,int timeOut){
        command.put(userName,"开启");
     //   threadPoolExecutor.submit(new CheckTimeOutTask(userName,timeOut));

        Thread thread = new Thread(new CheckTimeOutTask(userName,timeOut),userName);
        thread.start();
        pool.put(userName,thread);

        return true;
    }

    // 强制停下计时（结束记录数据）
   public void stopTask(String userName){

      this.pool.get(userName).interrupt();

    }


}
