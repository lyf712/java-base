package learn.design.prototype;

import javax.swing.*;
import java.awt.*;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/1
 * @VERSION 1.0
 * @DESC
 */

class ProtoTest extends JPanel implements Cloneable {
    public ProtoTest(){
        JLabel jLabel = new JLabel(new ImageIcon("E:\\IdeaProjects\\java-base\\src\\main\\resources\\test.png"));
        this.add(jLabel);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        ProtoTest protoTest= null;
        try {
            protoTest =(ProtoTest) super.clone();
            System.out.println("复制成功");
        }catch (Exception e){
            System.out.println("复制失败");
        }
        return protoTest;
    }
}


public class ProtoTypeApplication {

    public static void main(String[]args) throws CloneNotSupportedException {
        ProtoTest protoTest = new ProtoTest();
        ProtoTest protoTest1 =(ProtoTest) protoTest.clone();


        JFrame jFrame = new JFrame("复制测试");
        jFrame.setLayout(new GridLayout(1,2));
        Container container = jFrame.getContentPane();

        container.add(protoTest);
        container.add(protoTest1);
        container.add((ProtoTest) protoTest1.clone());

        jFrame.pack();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocation(300,200);
        jFrame.setVisible(true);
    }

}
