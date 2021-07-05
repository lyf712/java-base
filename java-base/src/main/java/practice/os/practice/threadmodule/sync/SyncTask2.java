package practice.os.practice.threadmodule.sync;

import java.util.Date;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/17
 * @VERSION 1.0
 * @DESC
 */
public class SyncTask2 implements Runnable{

    @Override
    public void run() {
        // SyncTask2,它执行的前置动作还未完成,就一直等待,P操作进行检测上个动作是否完成
        while(!SyncDemo.flag);
        System.out.println(new Date()+"SyncTask2开始工作...");
        System.out.println(new Date()+"SyncTask2结束工作...");
    }
}
