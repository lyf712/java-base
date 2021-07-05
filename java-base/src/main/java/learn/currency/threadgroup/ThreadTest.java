package learn.currency.threadgroup;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/3
 * @VERSION 1.0
 * @DESC
 */
public class ThreadTest {
    public static void main(String[]args){

        TestTask testTask = new TestTask();
        new Thread(testTask).start();
        new Thread(testTask).start();
        new Thread(new TestTask()).start();


    }
}
