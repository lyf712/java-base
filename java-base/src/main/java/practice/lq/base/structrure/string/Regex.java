package practice.lq.base.structrure.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/3
 * @VERSION 1.0
 * @DES
 *
 *
 * 一、基本类的使用
 * 1. Pattern （模式类，定义规则）
 *
 * 2. Matcher 匹配类，进行匹配
 *
 * 3.
 *
 * 二、正则的表示
 * 1. 转义字符   \n,\等，但在Java中需要\\才能表示，\\(就相当于\( 进行匹配（   \\d匹配数字
 * 2. * 与 +    匹配0个或多个   1个与多个
 * 3. .
 * 4. ？
 *
 * 5. {n,m}  匹配n到m次数
 *
 * 6.[abc] 包含字符集中的某个
 *
 * 7. ^非
 *
 * 总结：
 *
 * 基本字符:   . ? [a-z] [^a-z] \\( 转义字符
 * 进行扩展：  {n,m}  * +
 *
 *
 * 参考：https://www.runoob.com/java/java-regular-expressions.html
 *
 */
public class Regex {

    // 直接使用静态方法匹配
    void test1(){

        // 匹配数字
        String pattern  = "[a-z]*[\\d]*";
        String line = "hello";
        String line1 = "hello123";

        boolean isNumber = Pattern.matches(pattern,line);
        boolean isNumber1 = Pattern.matches(pattern,line1);

        if(isNumber){
            System.out.println("line存在");
        }else{
            System.out.println("line不存在number");
        }

        if(isNumber1){
            System.out.println("line1存在");
        }else{
            System.out.println("line1不存在number");
        }

    }

    // 先编译对象，在通过Matcher对象进行查找、匹配
    void test2(){

        String regex1 = "";
        String regex2 = "";
        String line1 = "";
        String line2 = "";
        String line3 = "";

        Pattern pattern1 = Pattern.compile(regex1);
        Pattern pattern2 = Pattern.compile(regex2);

        Matcher matcher11 = pattern1.matcher(line1);
        Matcher matcher12 = pattern1.matcher(line2);
        Matcher matcher13 = pattern1.matcher(line3);

        Matcher matcher21 = pattern2.matcher(line1);
        Matcher matcher22 = pattern2.matcher(line2);
        Matcher matcher23 = pattern2.matcher(line3);

        while(matcher11.find()){
            System.out.println(matcher11.group(0));
        }


    }




    public static void main(String[]args){
        Regex r = new Regex();
        r.test1();

    }



}
