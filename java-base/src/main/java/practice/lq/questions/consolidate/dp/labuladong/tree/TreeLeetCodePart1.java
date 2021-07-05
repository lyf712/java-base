package practice.lq.questions.consolidate.dp.labuladong.tree;

import java.util.Arrays;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/14
 * @VERSION 1.0
 * @DESC
 * 一、Part1
 * 注:DP、二叉树基本入门学习
 * 1.斐波那数
 *
 * 2.
 */
public class TreeLeetCodePart1 {

    // 斐波那数
    // https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/submissions/

    //法1:最原始的方法直接递归求解
    // //到40求解时就跑不动了
    int method1(int n){
        if(n<2){
            return n;
        }else{
            return method1(n-1)+method1(n-2);
        }
    }
    static int []cache= new int[1000];
    static {
        Arrays.fill(cache,-1);
    }
    // 法2:记忆优化
    int method2(int n){
        if(n<2){
            cache[n]=n;
            return n;
        }else{
            if(cache[n-1]!=-1&&cache[n-2]!=-1){
                return cache[n-1]+cache[n-2];
            }else if(cache[n-2]!=-1){
                return cache[n-2]+method1(n-1);
            }else if(cache[n-1]!=-1){
                return cache[n-1]+method1(n-2);
            }
            return method1(n-1)+method1(n-2);
        }
    }

    // 法3:动规划方法，使用数组进行存储就无需再重复递归

    // 动态方程
    int method3(int n){
        if(n<2){
            cache[n]=n;
        }else {
            cache[0]=0;
            cache[1]=1;
            for(int i =2;i<=n;i++){
                cache[i]=cache[i-1]+cache[i-2];
            }
//            cache[n]=cache[n-1]+cache[n-2];
        }
        return cache[n];
    }

    //空间继续优化，，迭代思想
    int method4(int n){

        int a=0,b=1,sum;
        for(int i=0;i<n;i++){
            sum = a+b;// 如果需要进行数据处理，取余则从此处开始取余这样也肯定让后面的数小于该数，
            a=b;//a进一步迭代到b(即第二数，第一个数废弃）
            b=sum;//b迭代到第三个数
        }
        return a;// 返回第一个数，看起点 n=0不用迭代就为本身，n=1时迭代一次到b也就是第二个数
    }

    void test(){
        for(int i=10;i<1000;i*=2){
            long start = System.currentTimeMillis();
            int val = method4(i);
            long end = System.currentTimeMillis();
            System.out.println(i+"所用时间为:"+(end-start)+";结果为"+val);
        }

    }



    public static void main(String[]args){
        TreeLeetCodePart1 treeLeetCodePart1 = new TreeLeetCodePart1();
        treeLeetCodePart1.test();
    }


}
