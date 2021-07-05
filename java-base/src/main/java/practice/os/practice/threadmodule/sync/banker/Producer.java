package practice.os.practice.threadmodule.sync.banker;

import java.util.Date;
import java.util.Random;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/17
 * @VERSION 1.0
 * @DESC
 */
public class Producer implements Runnable{
    @Override
    public void run() {
        while(true){
            // 同步P操作
            while (Main.empty<=0||Main.mutex);// 当空的buffer区小于等于0时进行等待（忙则等待？）,负数代表有进程正在等待（消费者消费过度）？
            // 互斥P操作，加锁
            Main.mutex=true;
            System.out.println(new Date()+"准备生产产品;空buffer区数量为"+Main.empty+";满buffer区数量为" +
                    ""+Main.full);
            Main.full++;// full加一，进行生产产品，导致buffer区满（一次性就生产满》）
            Main.empty--;
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(new Date()+"生产产品结束;空buffer区数量为"+Main.empty+";满buffer区数量为" +
                    ""+Main.full);
            // 释放锁(V操作)
            Main.mutex=false;
            //
            try {
                Thread.sleep(20+new Random().nextInt(2)*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
