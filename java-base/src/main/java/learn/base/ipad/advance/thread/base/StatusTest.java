package learn.base.ipad.advance.thread.base;

import learn.currency.threadgroup.TestTask;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/14
 * @VERSION 1.0
 * @DESC
 */

class StatusTask1 implements Runnable{
    private int counter = 100;
    @Override
    public void run() {

    }
}

class StatusTask2 implements Runnable{

    @Override
    public void run() {

    }
}

public class StatusTest {
    static ThreadPoolExecutor executor;
    public static void main(String[]args){
        ThreadFactory factory = executor.getThreadFactory();
        factory.newThread(new TestTask()).start();


        Thread thread = new Thread();

    }
}
