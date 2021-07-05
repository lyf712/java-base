package practice.os.practice.threadmodule.sync;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/17
 * @VERSION 1.0
 * @DESC
 */
public class SyncDemo {
    // 同步PV操作的判断检测flag
    //volatile
    static public boolean flag = false;
    public static void main(String[]args){
        // 为演示效果,还故意将SyncTask2放在前面进行启动
        new Thread(new SyncTask2()).start();
        new Thread(new SyncTask1()).start();
    }

}
