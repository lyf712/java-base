package learn.base.core.chapter6;

import javax.swing.*;
import java.awt.event.ActionListener;


/**
 * @AUTHOR LYF
 * @DATE 2021/4/29
 * @VERSION 1.0
 * @DESC
 */
public class Main {
    public static void main(String[]args){
        ActionListener event = new CallBack();
        Timer timer = new Timer(1000,event);
        timer.start();
        Timer t2 = new Timer(2000,e->{
            System.out.print("TEST");
        });
        t2.start();

    }
}
