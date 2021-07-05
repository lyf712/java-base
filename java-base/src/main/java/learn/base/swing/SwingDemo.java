package learn.base.swing;

import javax.swing.*;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/3
 * @VERSION 1.0
 * @DESC
 */
public class SwingDemo extends JFrame {

    private JFrame container = new JFrame();

    void init(){
        container.setTitle("frame1");
        container.setName("MySwing");
        container.setSize(400,400);
        container.show();
        container.setLocation(300,200);

    }

    // 界面设计
    void menu(){
        JMenuBar jMenuBar = new JMenuBar();

        JMenu jMenu = new JMenu();
        jMenu.add(Action.NAME);

        jMenuBar.add(jMenu);

        container.setJMenuBar(jMenuBar);
    }

    void connect(){

    }

    void socket(){

    }

    public static void main(String[]args){
        SwingDemo swingDemo = new SwingDemo();
        swingDemo.init();
        swingDemo.menu();
    }

}
