package practice.lq.questions.consolidate.dp.labuladong.tree;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/15
 * @VERSION 1.0
 * @DESC
 */
public class Main {
    public static void main(String[]args){

        TestFab1 testFab1 = new TestFab1(1);
//        testFab1.run();

        TestFab1 testFab2 = new TestFab1(2);
//        testFab2.run();

        TestFab1 testFab3 = new TestFab1(3);
//        testFab3.run();

        TestFab1 testFab4 = new TestFab1(4);
//        testFab4.run();

        Thread thread = new Thread(testFab1);
        thread.start();
        Thread thread2 = new Thread(testFab2);
        thread2.start();
        Thread thread3 = new Thread(testFab3);
        thread3.start();
        Thread thread4 = new Thread(testFab4);
        thread4.start();

    }
}
