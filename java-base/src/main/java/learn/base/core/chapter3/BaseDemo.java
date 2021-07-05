package learn.base.core.chapter3;

import java.time.LocalDate;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/29
 * @VERSION 1.0
 * @DESC
 *
 * 一、结构目录
 * 1.注释
 * 2.数据类型（八大类型和String，数组，大数值）
 * 3.变量、运算符、控制语句
 * 4.输入输出
 */
public class BaseDemo {
    // 简单的测试
    private static void test(){
        long l;
        l = 100L;
        l = 100l;

        int num = 0b1010_1011;// 0b二进制，0x十六进制
        System.out.println(num);

        // Unicode
        String str = "\u2122";
        System.out.println(str);

        LocalDate localDate = LocalDate.of(2000,10,21);


    }

    public static void main(String[]args){
        test();
    }
}
