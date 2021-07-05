package learn.base.ipad.advance.txt.text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/18
 * @VERSION 1.0
 * @DESC
 *
 * 1.调用XML、JSON、图形等高级文件
 *
 *
 */
public class FileReadDemo {
    private String path = "E:\\IdeaProjects\\java-base\\src\\main\\java\\learn.base.pb\\advance\\txt\\text\\JsonTest.json";

    // 参考：https://blog.csdn.net/rocling/article/details/81784388
    void test(){
        File file = new File(path);
        try{
            // FileReader,BufferedReader，来源于抽象 先输入流进来然后File流读取在缓存，，再读取器读
            FileReader fileReader = new FileReader(file);

            char[]arr = new char[1024];
            fileReader.read(arr);
            System.out.println(String.valueOf(arr));


            // 读进数组，使用read,直接读入呢？？
//            JSONReader jsonReader = new JSONReader(fileReader);
//
//            while(jsonReader.hasNext())
//            {
//                System.out.println(jsonReader.readObject());
//            }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[]args){

        FileReadDemo fileReadDemo = new FileReadDemo();
        fileReadDemo.test();
    }

}
