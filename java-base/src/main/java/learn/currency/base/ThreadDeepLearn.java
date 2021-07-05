package learn.currency.base;

import java.util.stream.IntStream;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/28
 * @VERSION 1.0
 * @DESC
 *
 * 一、深入理解Thread构造函数
 * // 关于命名
 * 1. new Thread(Runnable,name,)
 * 2. new Thread(ThreadGroup,name)
 *
 * // 关于线程组
 * 线程必须由其他线程进行创建，main由JVM线程进行创建，线程组未进行命名，那么将会加入父线程组
 *
 *
 */
public class ThreadDeepLearn {


    public static void main(String[]args)
    {
        // stream的写法，boxed??
        IntStream.range(0,5).boxed().map(i->new Thread(
                ()-> System.out.println(Thread.currentThread().getName()+";"+Thread.currentThread().getThreadGroup().getName())
        )).forEach(Thread::start);// map映射成该数据对象,forEach进行执行

        ThreadGroup threadGroup = new ThreadGroup("group1");
        new Thread(threadGroup, new Runnable() {
            @Override
            public void run() {

            }
        },"test1").start();



    }


}
