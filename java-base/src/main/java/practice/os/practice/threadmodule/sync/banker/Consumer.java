package practice.os.practice.threadmodule.sync.banker;

import java.util.Date;
import java.util.Random;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/17
 * @VERSION 1.0
 * @DESC
 */
public class Consumer implements Runnable{
    @Override
    public void run() {
        while(true){
            while(Main.full<=0||Main.mutex);

            Main.mutex = true;
            System.out.println(new Date()+"准备消费产品;空buffer区数量为"+Main.empty+";满buffer区数量为" +
                    ""+Main.full);
            Main.full--;
            Main.empty++;
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(new Date()+"消费产品结束;空buffer区数量为"+Main.empty+";满buffer区数量为" +
                    ""+Main.full);
            Main.mutex = false;

            try {
                Thread.sleep(2000+new Random().nextInt(2)*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
