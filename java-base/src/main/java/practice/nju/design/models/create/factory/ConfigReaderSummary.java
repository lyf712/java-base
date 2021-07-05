package practice.nju.design.models.create.factory;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Properties;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/13
 * @VERSION 1.0
 * @DESC
 */
public class ConfigReaderSummary {

    //https://www.cnblogs.com/sebastian-tyd/p/7895182.html
    static void readProperties() throws IOException {
        String path = "E:\\IdeaProjects\\" +
                "java-base\\src\\main\\java\\practice\\nju\\design\\models\\create\\factory\\PropertyFile.properties";

        String path2="E:\\IdeaProjects\\java-base\\src\\main\\resources\\test.properties";

        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = ConfigReaderSummary.class.getClassLoader().
                getResourceAsStream(path2);

        InputStream inputStream = new FileInputStream(path);
        // 使用properties对象加载输入流
        //properties.load(in);
        properties.load(inputStream);
        properties.load(in);
        //获取key对应的value值
        System.out.println(properties.getProperty("application"));
    }


    // 思路进行fileStream读取JSON文件然后进行序列化
    //https://www.cnblogs.com/yuhuiqing/p/12627937.html
    static void  readJson() throws IOException {


        String path ="E:\\IdeaProjects\\java-base\\src\\main\\java\\practice\\nju\\design\\models\\create\\factory\\JsonFile.json";
        // file ,也可以自己进行InputStream进行包装生成String,
        String str = FileUtils.readFileToString(new File(path));
        JSONObject jsonObject=JSONObject.parseObject(str);
        System.out.println(jsonObject.getString("name"));
    }


    public static void main(String[]args) throws IOException {
      //  readProperties();
        readJson();
    }

}
