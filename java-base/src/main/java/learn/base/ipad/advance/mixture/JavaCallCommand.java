package learn.base.ipad.advance.mixture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/5
 * @VERSION 1.0
 * @DESC
 * 调用命令行
 *
 * Runtime机制
 * Process调用进程，exec进行执行
 */
public class JavaCallCommand {

    public static void main(String[]args) throws IOException {

        Process p;

        String cmd="ipconfig /all";

        p = Runtime.getRuntime().exec(cmd);

        InputStream ips = p.getInputStream();
        InputStreamReader isr = new InputStreamReader(ips,"UTF-8");

        char[] bytes = new char[1024];

        while (isr.read(bytes)>0){
            System.out.println(bytes.toString()+";"+new String(bytes)+";");
        }

        BufferedReader br = new BufferedReader(isr);
        String line = new String();
        line.getBytes(StandardCharsets.UTF_8);

        while((line= br.readLine())!=null){
            System.out.println(line);
        }



        int exitVal = p.exitValue();
        System.out.println("status:"+exitVal);



    }
}
