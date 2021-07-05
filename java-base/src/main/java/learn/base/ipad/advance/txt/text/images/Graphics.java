package learn.base.ipad.advance.txt.text.images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/18
 * @VERSION 1.0
 * @DESC
 *
 * 一、概念
 * 1.图形（矢量图，线性画出来的）与图像（像素点，图片等）
 * 2.对应包 图形 awt包，， 图像 javax.imageio
 *
 */
public class Graphics {

    public static void readAndWrite() throws MalformedURLException {

              File file =new File("E:\\IdeaProjects\\java-base\\src\\main\\java\\learn.base.pb\\advance\\txt\\text\\images\\test1.jpg");

              URL url = new URL("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3604740148,1174736003&fm=26&gp=0.jpg");
                try{
                    BufferedImage image = ImageIO.read(url);
                    System.out.println(image.getHeight()+";"+image.getWidth());
                    ImageIO.write(image,"png",new File("E:\\IdeaProjects\\java-base\\src\\main\\java\\learn.base.pb\\advance\\txt\\text\\images\\test3.png"));
                }catch (Exception e){
                    e.printStackTrace();
                }

    }

    public static void main(String[]args) throws MalformedURLException {
        readAndWrite();
    }

}
