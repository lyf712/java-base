package practice.os.practice.threadmodule.sync;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/17
 * @VERSION 1.0
 * @DESC
 */
public class Thread1 implements Runnable{

    @Override
    public void run() {
        // 当其他进程正在访问时,等待 =>
        while (MutexDemo.resourceMutex==false);
        // P操作，进行访问临界资源前的加锁
        MutexDemo.resourceMutex = false;
        for(int i =0;i<10;i++){
            System.out.println("Thread1 第"+i+"次工作");
            Resources.dataPool.add("产品"+i);
            //Resources.IoResources.;
        }
        // 释放锁,V操作
        MutexDemo.resourceMutex = true;

        // V操作进行提醒其他进程操作，达到同步
    }
}
