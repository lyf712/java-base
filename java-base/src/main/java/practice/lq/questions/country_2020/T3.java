package practice.lq.questions.country_2020;

import java.util.Arrays;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/22
 * @VERSION 1.0
 * @DESC
 * 3.阶乘约数
 * 使用约数定理
 * 若将数分解为两两素数，那么约数个数为 (1+a1)*..(1+an) a1为其指数
 * 例如2*3*5 约数个数为(1+1)*(1+1)*(1+1)=8
 *
 * 解决步骤
 * 1.转换为全部素数,使用散列表进行记录对应指数数字
 * 2.遍历散列表进行求解
 *
 * 39001250856960000
 */
public class T3 {
    static private int[] hash=new int[101];//0空位
    static {
        Arrays.fill(hash,0);
    }

    static void handle(){
//        hash[2]++;//2
//        hash[3]++;//3
        for (int i = 2; i <= 100; i++){
            //分解成素数
            int temp =i;
            for (int j =2;j*j<=temp;j++){
                if(temp%j==0){
                    hash[j]++;
                    temp=temp/j;//继续分解
                    j=1;//从2开始继续分解
                }
            }
            hash[temp]++;//分解到最后肯定为素数
        }
    }

    static void print(){
        System.out.println("hash situation...");
        long sum = 1;
        for (int i=0;i<101;i++){
            System.out.print(hash[i]+" ");
            if(hash[i]!=0)
             sum*=(1+hash[i]);
        }
        System.out.println("rs:"+sum);
    }
    public static void main(String[]args){
        handle();
        print();
    }



}
