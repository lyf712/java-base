package learn.base.core.chapter10;

import javax.swing.*;
import java.awt.*;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/30
 * @VERSION 1.0
 * @DESC
 */

class FrameWin extends JFrame{
    private Integer WIDTH=200;
    private Integer HEIGHT=200;
    public FrameWin(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        WIDTH = kit.getScreenSize().width/2;
        HEIGHT = kit.getScreenSize().height/2;
        setSize(WIDTH,HEIGHT);

        Image image = new ImageIcon("E:\\IdeaProjects\\java-base\\src\\main\\resources\\test.png").getImage();
        setIconImage(image);

        setVisible(true);
    }



}

public class SwingTest {
    public static void main(String[]args){
      FrameWin frameWin = new FrameWin();

    }

}
