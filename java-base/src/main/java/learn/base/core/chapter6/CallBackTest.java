package learn.base.core.chapter6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/29
 * @VERSION 1.0
 * @DESC
 */

class TimerCall implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("TEST"+new Date());
        Toolkit.getDefaultToolkit().beep();
    }
}


public class CallBackTest {
    public static void main(String[]args){
        ActionListener actionListener = new TimerCall();
        Timer t = new Timer(3000,actionListener);
        t.start();

        try {
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null,"Quit Win??");
        System.exit(0);
    }
}
