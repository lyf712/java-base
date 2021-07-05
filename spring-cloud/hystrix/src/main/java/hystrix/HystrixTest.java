package hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/16
 * @VERSION 1.0
 * @DESC
 * 隔离策略
 * 1.信号量机制
 * 适用于并发量不是很大时
 * 2.线程隔离机制
 *
 *
 */

public class HystrixTest extends HystrixCommand<String> {
    private String name;
    protected HystrixTest(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("myHystrixGroup")

        );
        this.name = name;
    }

    // 运行
    @Override
    protected String run() throws Exception {

//        String result = new HystrixTest("zhangsan").execute();
//        System.out.println(result);
        try{
            Thread.sleep(100*new Random().nextInt(20));
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.name+":"+Thread.currentThread().getName();
    }

    // 回调
    @Override
    protected String getFallback(){
        return this.name+"调用失败";
    }

    public static void main(String[]args) throws ExecutionException, InterruptedException {
        // 同步调用
        String rs  = new HystrixTest("I am a test1").execute();
        String rs2  = new HystrixTest("I am a test2").execute();
        System.out.println(rs+";"+rs2);

        // 异步调用
        Future<String> future1 = new HystrixTest("I am a test1").queue();
        Future<String> future2 = new HystrixTest("I am a test2").queue();
        System.out.println(future1.get()+";"+future2.get());


    }
}
