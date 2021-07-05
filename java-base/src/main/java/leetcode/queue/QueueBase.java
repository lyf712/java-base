package leetcode.queue;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * @AUTHOR LYF
 * @DATE 2021/6/7
 * @VERSION 1.0
 * @DESC
 * 1.源码 queue 实现的方法
 * 2.几种队列 (blocking阻塞队列
 *
 * 参考：https://www.cnblogs.com/yuandluck/p/9476123.html
 *
 * 1、没有实现的阻塞接口的LinkedList： 实现了java.util.Queue接口和java.util.AbstractQueue接口
 * 　　内置的不阻塞队列： PriorityQueue 和 ConcurrentLinkedQueue
 * 　　PriorityQueue 和 ConcurrentLinkedQueue 类在 Collection Framework 中加入两个具体集合实现。
 * 　　PriorityQueue 类实质上维护了一个有序列表。加入到 Queue 中的元素根据它们的天然排序（通过其 java.util.Comparable 实现）或者根据传递给构造函数的 java.util.Comparator 实现来定位。
 * 　　ConcurrentLinkedQueue 是基于链接节点的、线程安全的队列。并发访问不需要同步。因为它在队列的尾部添加元素并从头部删除它们，所以只要不需要知道队列的大 小，　　　　    　　ConcurrentLinkedQueue 对公共集合的共享访问就可以工作得很好。收集关于队列大小的信息会很慢，需要遍历队列。
 *
 *
 * 2)实现阻塞接口的：
 * 　　java.util.concurrent 中加入了 BlockingQueue 接口和五个阻塞队列类。它实质上就是一种带有一点扭曲的 FIFO 数据结构。不是立即从队列中添加或者删除元素，线程执行操作阻塞，直到有空间或者元素可用。
 * 五个队列所提供的各有不同：
 * 　　* ArrayBlockingQueue ：一个由数组支持的有界队列。
 * 　　* LinkedBlockingQueue ：一个由链接节点支持的可选有界队列。
 * 　　* PriorityBlockingQueue ：一个由优先级堆支持的无界优先级队列。
 * 　　* DelayQueue ：一个由优先级堆支持的、基于时间的调度队列。
 * 　　* SynchronousQueue ：一个利用 BlockingQueue 接口的简单聚集（rendezvous）机制。
 *
 *
 */
public class QueueBase {
    public static void main(String[]args){
        //--------------非阻塞----------------
        // queue集合,其方法为必须实现的
        Queue<Integer> arrayDeque = new ArrayDeque<>();
        Queue<Integer> priorityQueue = new PriorityQueue<>();
        Queue<Integer> linkedQueue = new LinkedList<>();

        //------------------阻塞-----------------------
        Queue<Integer> synchronousQueue = new SynchronousQueue<>();
        Queue<Integer> concurrentLinkedDeque = new ConcurrentLinkedDeque<>();
        BlockingDeque<Integer> deque = new LinkedBlockingDeque<>();
        Queue<Integer> deque1 = new ArrayBlockingQueue<Integer>(10);

         //https://www.cnblogs.com/myseries/p/10944211.html
        DelayQueue delayQueue = new DelayQueue();//DelayQueue是一个无界的BlockingQueue，用于放置实现了Delayed接口的对象，其中的对象只能在其到期时才能从队列中取走
//        delayQueue.offer()

        int front ,rear ;
        front = rear = 0;

    }
    /**/
    private int[]arr;
    public QueueBase(){
        arr = new int[10];
    }
}
