package learn.design.singleton;

import javax.swing.*;
import java.awt.*;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/1
 * @VERSION 1.0
 * @DESC
 * 应用
 */
class Win extends JPanel{
      private static Win instance = new Win();

      private Win(){
          JLabel l1 = new JLabel(new ImageIcon("E:\\IdeaProjects\\java-base\\src\\main\\resources\\test.png"));
          this.add(l1);
      }

      public static Win getInstance(){
          return instance;
      }
}

class WinPanel extends JPanel{
    public WinPanel(){
        JLabel l1 = new JLabel(new ImageIcon("E:\\IdeaProjects\\java-base\\src\\main\\resources\\test.png"));
        this.add(l1);
    }
}

public class SingletonApplication {

    public static void main(String[]args){
         // 框架
         JFrame jf = new JFrame("饿汉单例模式");
         // 设置布局
         jf.setLayout(new GridLayout(1,2));

         // 容器
         Container containerPane = jf.getContentPane();

         // 设置面板
         Win win1 = Win.getInstance();
         Win win2 = Win.getInstance();

         // 不同实例
         WinPanel panel1 = new WinPanel();
         WinPanel panel2 = new WinPanel();

         containerPane.add(panel1);
         containerPane.add(panel2);

  //---------------------------------------

         containerPane.add(win1);
         containerPane.add(win2);

         if(win1==win2){
             System.out.println("是同一个");
         }else {
             System.out.println("不是同一个");
         }

         jf.pack();
         jf.setLocation(500,300);
         jf.setVisible(true);
         jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
