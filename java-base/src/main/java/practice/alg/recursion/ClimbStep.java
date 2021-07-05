package practice.alg.recursion;

import java.util.Date;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/29
 * @VERSION 1.0
 * @DESC
 * 1.台阶(爬楼梯)问题
 * 一次只能往上爬一个或两个
 * 初始条件：
 * 递推条件：当选择某种方案递推到上一个或者两个状态
 * 结束条件：
 *
 *
 * 2.递归阶乘
 *
 */
public class ClimbStep {

    int climb(int n){
        if(n==1||n==2)
            return n;
        else
            return climb(n-1)+climb(n-2);
    }

    int initP(int n){
        if(n==1)
            return 1;
        else
            return n*initP(n-1);
    }


    int print(int n){
        if(n==1)
            return 1;

        //int sum;
        int sum=0;
        System.out.println(new Date()+":"+sum+"*"+n);
        // 开始准备乘积过程
        sum = n*print(n-1);
        // 回退过程
        System.out.println(new Date()+":"+sum+"*"+n);
        return sum;
    }


    public static void main(String[]args){
        ClimbStep c= new ClimbStep();
        System.out.println(new ClimbStep().climb(10));
        System.out.println(c.initP(10)+";"+c.print(10));

    }
}
