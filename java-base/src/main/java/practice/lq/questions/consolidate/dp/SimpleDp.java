package practice.lq.questions.consolidate.dp;

import java.util.Arrays;
import java.util.Random;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/16
 * @VERSION 1.0
 * @DESC
 */
public class SimpleDp {

    // 最大子段和
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
    void maxSub(){
        int []arr = new int[10];
        int[]dp = new int[10];
        int []status = {-1,1};
        for(int i =0;i<10;i++){
            Random random = new Random();
            arr[i]=random.nextInt(20)*status[random.nextInt(2)];
            System.out.print(arr[i]+"->");
        }
        System.out.println();

        dp[0]=arr[0];
        for(int j =1;j<10;j++){
            dp[j]=dp[j-1]>0?dp[j-1]+arr[j]:arr[j];
        }

        for(int i =0;i<10;i++){
            System.out.print(dp[i]+">");
        }
        Arrays.sort(dp);
        System.out.println("最大值为:"+dp[dp.length-1]);
    }

    // 最长不降子序列
    //  1 3 2 4 5 3 1 0 3 4
    //  https://blog.csdn.net/liu16659/article/details/104091629
    // 1.按照最大子段思路，dp[i]存储在i最长的不降个数
    // 2.确定状态转移，，如何递推？ 由于不连续因此，需要在确定dp[i]时，需要遍历i之前符合要求的然后再选取最大的值进行存储
    // dp[0]=arr[0]  ,dp[i]=max(dp,0,i-1)&&arr[i]>=..  // 如果使用list可以使用stream进行条件筛选再进行排序，但是复杂度会多排序，
    // 3.最优解 max dp
    // 时间复杂度n^2
    // 若暴力需要 Cn 1, Cn 2+...Cn n +加上遍历

    void maxSub2(){

        int N =  20;
        int []arr = new int[N];
        // 模拟数据
        for(int i =0;i<N;i++){
            arr[i]=new Random().nextInt(20);
            System.out.print(arr[i]+"->");
        }

        int []dp = new int[N];
        dp[0]=1;

        // 确定DP
        for(int i =1;i<N;i++){

            int maxDpVal = 1;//如该数小于前面所有数则只能为该数起点 个数则为1

            for(int j=0;j<i;j++){
                if(arr[i]>=arr[j]&&dp[j]+1>maxDpVal){// 满足要求，可以考虑是否从该处对接,此处可以进行记录
                    maxDpVal = dp[j]+1;
                }
            }

            dp[i]=maxDpVal;
        }

        Arrays.sort(dp);
        System.out.println();
        System.out.println("最优解:"+dp[dp.length-1]);
    }
    // 最长公共子序列
    //  https://www.cnblogs.com/fengziwei/p/7827959.html
    // 由于是两个字符串比较，因此需要二维数组进行存储DP
    // 行和列分别代表一个字符串

    void maxSub3(){
        String str1=" fjlsdjfklajsdfj";
        String str2=" ofyljfsreoewirwq";//需要进行空位

        int[][]dp = new int[str1.length()][str2.length()];
        //dp[0][0]=0;// 空位

        String str = "";
        for(int i =0;i<str1.length();i++)
        {
            Arrays.fill(dp[i],0);
        }


        // 比较暴力的DP，存储全部
        for(int i =1;i<str1.length();i++){
            for(int j = 1;j<str2.length();j++){

                if(str1.charAt(i)==str2.charAt(j)){
                   dp[i][j]=dp[i-1][j-1]+1;
                   // 有发生变化转移的,记录路径
//                    str=str+str1.charAt(i)+"";
                }else{
                    dp[i][j]=Math.max(dp[i][j-1],dp[i-1][j]);
                }

//                if(i==1){//初始
//                    if(str1.charAt(i)==str2.charAt(j)){
//                        dp[i][j]=1;
//                    }
//                }else{//递推
//
//                    int max=dp[i-1][1];//上一层最大值
//
//                    for(int k=2;k<str2.length();k++){// 时间复杂度n^3
//                        if(dp[i-1][k]>max){
//                            max = dp[i-1][k];
//                        }
//                    }
//                    // 如匹配相等，则dp[i][j]在此之前的最大值+1
//                    // 若不匹配则等于该值
//                    dp[i][j]=str1.charAt(i)==str2.charAt(j)?max+1:max;
//                }
            }
        }

        // 最后一行最大值则是最优解
        Arrays.sort(dp[str1.length()-1]);

        System.out.println("++++++++++");

        for(int i =0;i<str1.length();i++){
            for(int j=0;j<str2.length();j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }


//        int min = Math.min(str1.length(),str2.length());
//        for(int i=1;i<min;i++){
//            if(dp[i][i]>dp[i-1][i-1]){
//                str=str+str2.charAt(i)+"";
//            }
//        }
        for(int i =1;i<str2.length();i++){
            if(dp[str1.length()-1][i]>dp[str1.length()-1][i-1]){
                str=str+str2.charAt(i);
            }
        }

        System.out.println("最优解:"+dp[str1.length()-1][str2.length()-1]+";"+str);




    }



    // 最大子矩阵和

    public static void main(String[]args){
        SimpleDp dp =new SimpleDp();
        dp.maxSub3();
    }



}
