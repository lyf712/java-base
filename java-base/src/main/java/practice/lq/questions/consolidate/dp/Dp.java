package practice.lq.questions.consolidate.dp;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/13
 * @VERSION 1.0
 * @DESC
 */
public class Dp {

    // 动态规划基础练习（最长公共子序列，打劫家舍，爬楼梯）
    // <=====前置条件

    // 总体思路
    // 1.确定起点（从小问题进行出发考虑）
    // 2.确定最优解是?,如何存储？如何递推（状态转移方程），从小问题类推到n
    // 3.确定终点，递推结束
    // 4.获取最优解，在DP存储中获最优
    // 最大子段和
    // 1 -1 3 -1 3 -2 -4
    // 暴力方式: n!+(n-1)!+..+1

    // dp[]存储以i结尾最大值，当dp[i]为负数，开始转移到该数
    // 可以得到该状态方程为 dp[i+1]=dp[i]>0?dp[i]+arr[i+1]:arr[i];// 若前面为负转移到从本身开始
    void maxSub1(){
        int []arr = new int[10];
        int []status = {-1,1};
        for(int i =0;i<10;i++){
            Random random = new Random();
            arr[i]=random.nextInt(20)*status[random.nextInt(2)];
        }

        int[]dp = new int[10];

        dp[0]=arr[0];//起点

        for(int i =1;i<10;i++){
            dp[i]=dp[i-1]>0?dp[i-1]+arr[i]:arr[i];
        }

     //   int maxVal = dp[0];

        Arrays.sort(dp);
        System.out.println("最大值为:"+dp[dp.length-1]);



    }
    public static void main(String[]args){
        Dp dp =new Dp();
        dp.maxSub1();
    }





    // 最长子序列


    // 最大子矩阵


    // 最大公共子序列





    // 数字三角形
    // 求最大和，且左右相差不超过1

    /**
     *
     * 3
     * 1 2
     * 4 5 6
     * 2 3 4 1
     *
     */
    // 如果没有相差限制，直接倒退贪心即可
    // 但如果有限制则
    private Scanner scanner = new Scanner(System.in);
    private int n =scanner.nextInt();
    private int[][] map = new int[n][n];

    public void inputData(){
        for(int i=0;i<n;i++){
            for(int j =0;j<i+1;j++){
                map[i][j]=scanner.nextInt();
            }
        }

        System.out.println("=========TEST==========");

        for(int i=0;i<n;i++){
            for(int j =0;j<i+1;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
    // 未进行限制（贪心即可）
    public void test1(){
        for(int i=n-2;i>=0;i--){//从倒数第二开始递推，选择最大的
            for(int j=0;j<i+1;j++){
                map[i][j]+=Math.max(map[i+1][j],map[i+1][j+1]);
            }
        }

        System.out.println("RESULT:"+map[0][0]);
    }

    // 进行相差限制小于等1=>
    // 偶数层 :向左和右一样多  奇数层: 向左或右为n/2，另外为n/2+1
    // dp[n]==每层最大
    // 最原始的思考，遍历所有并记录满足情况的，再选取最大的
    // dp:动态规划，
    //注意，这不是普通的数塔问题，因为向左走的次数与向右走的次数差值不超过1，所以当到达最后一层时
    // ，一定是落在中间位置，如果层数是奇数，那么最后一定落在最后一层的第个元素上，如果层数是偶数，
    // 最后一定是落在第或第个元素上。所以dp只要从最后一层的中间开始向上递推就可以了。
    //作者：yo1ooo
    //链接：https://www.jianshu.com/p/c20b6b9a178a
    //来源：简书

    // 相差的可以从最后的推出。最左边节点即全部往左走，往右走一步，则+1
    // 使用map同时代表DP方程记录
    public void test2(){

        // 讨论奇偶数，确定start,end
        int start,end;// n层范围

        if(n%2==0){
            start=n/2-1;// 0坐标开始
            end = n/2;
        }else{
            start = end = n/2;
        }

       int start0,end0=end;// n-1层范围
        for(int i =n-2;i>=0;i--){

             start0=start-1<0?0:start-1;
             //end0=end+1>n-2?n-2:end+1;
              end0=end>n-2?n-2:end;

            for(int j = start0;j<=end0;j++){
                if(j<start0||j>end0){
                    continue;
                }

                if(j==start0){//左边
                    map[i][j]+=map[i+1][j+1];
                    start--;
                }else if(j==end0){
                    map[i][j]+=map[i+1][j];
                    //end++;//无需加
                }else{
                    map[i][j]+=Math.max(map[i+1][j],map[i+1][j+1]);
                }
            }
        }
        System.out.println("RESULT:"+map[0][0]);
    }



    // 字符排序

    // 装饰珠




}
