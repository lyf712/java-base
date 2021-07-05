package learn.base.core.chapter6;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Timer;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/29
 * @VERSION 1.0
 * @DESC
 */
public class CallBack implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.print("TEST"+new Date());
        Toolkit.getDefaultToolkit().beep();
    }
    public static void main(String[]args)
    {
        CallBack callBack = new CallBack();
        Timer timer = new Timer();
       // callBack.actionPerformed();
    }
}
