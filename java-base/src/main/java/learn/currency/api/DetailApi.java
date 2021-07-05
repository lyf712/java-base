package learn.currency.api;

import java.util.stream.IntStream;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/28
 * @VERSION 1.0
 * @DESC
 *
 * 一、Thread API 详细介绍
 * 1.暂停执行\休眠
 * sleep:静态方法
 * TimeUnit:对sleep进行封装
 *
 * 2.yield
 * 提醒调度器我愿意放弃当前的CPU资源，若资源不紧张则忽略。
 * 对比sleep，yield并没有完全释放CPU资源
 *
 * 3.设置优先级（资源不紧张情况，无作用）
 *
 * 4.设置上下文加载器
 *
 * 5.获取ID（启动会JVM、main等线程，因此并不是0）
 *
 * 6、获取当前线程
 *
 * 7.interrupt!!
 *
 */
public class DetailApi
{
    public static void main(String[]args)
    {
        IntStream.range(0,5).mapToObj(DetailApi::create).forEach(Thread::start);
    }

    //
    private static Thread create(int index)
    {
        return new Thread(
                ()->
                {
                    System.out.println(index);
                }
        );
    }
}
