package practice.lq.test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/1
 * @VERSION 1.0
 * @DESC
 */
public class IoDemo {


    // 五个核心类，File ,InputStream,OutputStream,Reader,Writer
    void operateFile() throws IOException {

//        File.createTempFile("test",".txt");
//        String  filePath = "E:\\IdeaProjects\\java-base\\resources\\files";
//        File.createTempFile(filePath,"test.txt");

        String fileName = "E:\\IdeaProjects\\java-base\\resources\\files\\test.txt";
        File file = new File(fileName);
        if(!file.exists()){
            if(file.createNewFile()){
                System.out.println("创建成功！！");
            }
        }else{
            System.out.println("进行写入数据..");
            OutputStream outputStream = new FileOutputStream(file);
            byte[] bytes= new byte[1024];
            for(int i =0;i<10;i++){
                for(int j=0;j<10;j++){
                    String str = new Random().nextInt(100)+" ";
                    bytes=str.getBytes(StandardCharsets.UTF_8);
                    outputStream.write(bytes);
                }

                outputStream.write("\n".getBytes(StandardCharsets.UTF_8));
            }

            outputStream.close();

            System.out.println("输出：");

            InputStream inputStream = new FileInputStream(file);

            for(int i=0;i<inputStream.available();i++){
                inputStream.read(bytes);
                System.out.println(bytes.toString()+";"+new String(bytes));
            }

            inputStream.close();

        }

    }

    public static void main(String[]args) throws IOException {
        IoDemo ioDemo = new IoDemo();
        ioDemo.operateFile();
    }
}
