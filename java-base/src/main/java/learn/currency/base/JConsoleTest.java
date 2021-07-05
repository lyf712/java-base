package learn.currency.base;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/28
 * @VERSION 1.0
 * @DESC
 * // JDK中的bin里面打开进行连接
 *
 */
public class JConsoleTest {


    public static void main(String[]args)
    {
       new Thread(JConsoleTest::listening).start();
        new Thread(JConsoleTest::reading).start();
    }

    private static void listening()
    {
        while (true)
        {
            System.out.println("I am listening!!");
            try {
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private static void reading()
    {
        while (true)
        {
            System.out.println("I am reading!!");
            try {
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
