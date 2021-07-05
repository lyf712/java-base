package practice.os.practice.threadmodule.sync.banker;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/17
 * @VERSION 1.0
 * @DESC
 */
public class Main {
    // 信号量
    static private final int N = 10;
    // 互斥锁,false代表无进程访问临界区
    volatile static public boolean mutex=false;
    // 空的buffer区
    volatile static public int empty=N;
    // 满的buffer区
    volatile static public int full=0;

    public static void main(String[]args){
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
    }
}
