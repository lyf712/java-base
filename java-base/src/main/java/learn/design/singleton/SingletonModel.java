package learn.design.singleton;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/1
 * @VERSION 1.0
 * @DESC
 *
 * 1.关于static的生命周期
 * 结论：想要用static存一个变量，使得下次程序运行时还能使用上次的值是不可行的。
 * 因为静态变量生命周期虽然长（就是类的生命周期），但是当程序执行完，也就是该
 * 类的所有对象都已经被回收，或者加载类的ClassLoader已经被回收，那么该类就会从jvm的方法区卸载，即生命期终止
 * https://www.cnblogs.com/hf-cherish/p/4970267.html
 */

class LazySingleton{
    private volatile static LazySingleton lazySingleton =null;//运行时加载
    private LazySingleton(){
    }

    public static synchronized LazySingleton getInstance(){
        if(lazySingleton==null){
            System.out.println("未构造..");
            lazySingleton = new LazySingleton();
        }else{
            System.out.println("已构造..");
        }
        return lazySingleton;
    }

    public void method(){
        System.out.println("call method..");
    }
}

class HungrySingleton{
    private final static HungrySingleton hungrySingleton = new HungrySingleton();

    private HungrySingleton(){

    }

    public static HungrySingleton getInstance(){
        return hungrySingleton;
    }

    public void method(){
        System.out.println("call method....");
    }

}

public class SingletonModel {

    public static void main(String[]args){
        // 第一次构造
        LazySingleton lazySingleton = LazySingleton.getInstance();// Calendar就采用该模式
        lazySingleton.method();
        // 第二次构造 ，，static的生命周期》？？
        LazySingleton lazySingleton2 = LazySingleton.getInstance();
        lazySingleton2.method();



        // 饿汉单例模式
        HungrySingleton hungrySingleton = HungrySingleton.getInstance();
        hungrySingleton.method();

        if(lazySingleton==lazySingleton2){
            System.out.println("相同");
        }else {
            System.out.println("不同");

        }

//        for(int i =0;i<200;i++){
//            LazySingleton.getInstance().method();
//            try {
//                Thread.sleep(2000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }

    }
}
