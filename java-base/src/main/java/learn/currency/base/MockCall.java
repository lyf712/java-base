package learn.currency.base;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/28
 * @VERSION 1.0
 * @DESC
 *
 * 模拟营业厅叫号程序
 *
 */

class TicketWin2 implements Runnable{
    private String name;
    // 所有的号码
    private static final int MAX  = 50;
    // 当前号码
    private int index= 1;//使用static或实现runnable接口实现资源共享，但是在数据量很大的情况下都会会出现线程安全问题、数据同步
    public TicketWin2(String name)
    {
        this.name = name;
    }
    @Override
    public void run() {
        while (index <= MAX)
        {
            System.out.println("柜台"+name+"当前号码为"+index++);
        }
    }
}

class TicketWin extends Thread{
    // 柜台机的名称
    private String name;
    // 所有的号码
    private static final int MAX  = 50;
    // 当前号码
    private int index= 1;//使用static或实现runnable接口实现资源共享，但是在数据量很大的情况下都会会出现线程安全问题、数据同步

    public TicketWin(String name)
    {
        this.name = name;
    }

    @Override
    public void run() {
        while (index <= MAX)
        {
            System.out.println("柜台"+name+"当前号码为"+index++);
        }
    }
}



public class MockCall {

    public static void main(String[]args)
    {
//        TicketWin t1 = new TicketWin("1号机器");
//        t1.start();
//        TicketWin t2 = new TicketWin("2号机器");
//        t2.start();
//        TicketWin t3 = new TicketWin("3号机器");
//        t3.start();
//        TicketWin t4 = new TicketWin("4号机器");
//        t4.start();
        Thread t1 = new Thread(new TicketWin2("1号机器"));
        t1.start();
        Thread t2 = new Thread(new TicketWin2("2号机器"));
        t2.start();
        Thread t3 = new Thread(new TicketWin2("3号机器"));
        t3.start();
        Thread t4 = new Thread(new TicketWin2("4号机器"));
        t4.start();
    }
}
