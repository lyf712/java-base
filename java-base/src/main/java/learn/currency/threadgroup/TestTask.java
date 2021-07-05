package learn.currency.threadgroup;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/3
 * @VERSION 1.0
 * @DESC
 */
public class TestTask implements Runnable{

    //volatile
    private  int shareResource = 100;

    private String MUTEX="lock";

    @Override
    public void run() {
//        synchronized (MUTEX){

            while (shareResource>0){
                System.out.println(Thread.currentThread().getName()+"获取一个资源"+";当前资源为"+--shareResource);
            }

//        }


    }

}
