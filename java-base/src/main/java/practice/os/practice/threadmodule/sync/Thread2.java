package practice.os.practice.threadmodule.sync;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/17
 * @VERSION 1.0
 * @DESC
 */
public class Thread2 implements Runnable{
    @Override
    public void run() {
        while (!MutexDemo.resourceMutex);
        MutexDemo.resourceMutex = false;
        for(int i =0;i<10;i++){
            System.out.println("Thread2 第"+i+"次工作");
            Resources.dataPool.remove("产品"+i);
            //Resources.IoResources.;
        }
        // 释放锁,V操作
        MutexDemo.resourceMutex = true;
    }
}
