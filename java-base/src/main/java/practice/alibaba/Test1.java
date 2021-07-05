package practice.alibaba;

import java.util.Scanner;

/**
 * @AUTHOR LYF
 * @DATE 2021/3/29
 * @VERSION 1.0
 * @DESC
 * 阿里巴巴笔试题1
 *
 * // 题目描述
 * 输入 n, a[i]代表投给班长的票数，k-a[i]为小明的票数
 * 求k最小满足 k-a[i]的和大于 a[i]的和
 *
 * 思路：
 * 1.暴力
 * 老班长票数： S = a[i]  <=遍历一趟
 * 小明的为 n*k- S > S => k>2*S/n 向上取整
 * 还应该考虑 k大于 a[i]中任意一个数（max)
 * 若k<a[i] 则k = max(a[])
 *
 *
 *
 * 用例验证
 * S = 9 error
 *
 * a[i] 的范围是100000， n的范围也是100000
 *
 * S的范围问题？
 *
 *
 */
public class Test1 {

    //测试long的范围
    public static void test(){
        long sum = 1;
        while (sum>0){
            System.out.println(sum++);
        }
    }


    public static void main(String[]args){

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];// 测试a

        for(int i=0;i<n;i++)
            arr[i]=sc.nextInt();

        long k = 0;
        long sum=0;
        int max=arr[0];//设置一个max


        for(int i =0;i<arr.length;i++){
            sum+=arr[i];
            if(arr[i]>max){
                max = arr[i];
            }
        }

        if(2*sum%n==0){
            k = 2*sum/n;
            if(k<max){
                k = max;
            }
        }else {
            k = 2*sum/n+1;
            if(k<max){
                k = max;
            }
        }

        System.out.println(k);

    }
}
