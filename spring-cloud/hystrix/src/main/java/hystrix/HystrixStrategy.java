package hystrix;

import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/17
 * @VERSION 1.0
 * @DESC
 * Hystrix 策略和缓存机制
 */
public class HystrixStrategy extends HystrixCommand<String> {


    String name;//该名称
    //通过setter进行传参。
    private static HystrixCommandKey COMMAND_KEY = HystrixCommandKey.Factory.asKey("myKey");

    // 信号量机制
    protected HystrixStrategy(String name) {//Setter setter
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("group1"))
                .andCommandKey(COMMAND_KEY) // 为什么需要static,需要先写入？，constructor构造先于非静态属性
        );

        //HystrixCommandGroupKey.Factory.asKey("group1")


//        HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("group1"))
//                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationStrategy(
//                        HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE))  ??main线程？

        this.name = name;//传入名称。。？服务名称

    }

    @Override
    protected String run() throws Exception {
        System.err.println("has gotten "+this.name+" data!");
        return this.name+":"+Thread.currentThread().getName();
    }

    // 如无缓存，run就需要调用n次，如有缓存就会减少上文以及调用过的重复次数
    @Override
    protected String getCacheKey(){
        return this.name;
    }

    // 清除缓存
    public static void flushCache(String name){
        HystrixRequestCache.getInstance(COMMAND_KEY, HystrixConcurrencyStrategyDefault.getInstance()).clear(name);
    }

//    has gotten service1 data!
//    has gotten service2 data!
//    has gotten service1 data!
//    has gotten service2 data!
//    service1:main;service2:main
//    service1:main;service2:main

    public static void main(String[]args) throws ExecutionException, InterruptedException {

        HystrixRequestContext context = HystrixRequestContext.initializeContext();// ??请求上下文
        // 同步调用
        String syncRs1 = new HystrixStrategy("service1").execute();
        String syncRs2 = new HystrixStrategy("service2").execute();
        System.out.println(syncRs1+";"+syncRs2);

        HystrixStrategy.flushCache("service1");

        // 再进行异步调用一次
        Future<String> future1 = new HystrixStrategy("service1").queue();
        Future<String> future2 = new HystrixStrategy("service2").queue();
        System.out.println(future1.get()+";"+future2.get());

        context.shutdown();

    }


}
