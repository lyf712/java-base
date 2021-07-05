package practice.os.practice.threadmodule.sync;

import java.util.Date;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/17
 * @VERSION 1.0
 * @DESC
 */
public class SyncTask1 implements Runnable{
    @Override
    public void run() {
        System.out.println(new Date()+"SyncTask1正在工作...");
        System.out.println(new Date()+"SyncTask1工作结束...");
        // V操作,表面通知另外一个进程可以进行操作了,保证了同步。顺序执行
        SyncDemo.flag = true;
    }
}
