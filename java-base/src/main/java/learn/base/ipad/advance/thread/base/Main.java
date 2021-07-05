package learn.base.ipad.advance.thread.base;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/22
 * @VERSION 1.0
 * @DESC
 */
public class Main {

    public static void main(String[]args){
       Thread thread = new Thread(new Task1(10));

    }
}
