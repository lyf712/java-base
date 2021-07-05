package practice.alibaba;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @AUTHOR LYF
 * @DATE 2021/3/29
 * @VERSION 1.0
 * @DESC
 * 阿里巴巴笔试题2
 *
 * 图论
 *
 * 题目描述：
 * n,m,w（城市数量，路径数据，距离的情况下），问通过最少城市数量的城市（编号）
 *
 * 采用数组进行存储
 * arr[n][n] (应该考虑应用链接。！
 * 暴力进行查看每个城市的可行数量？
 *
 */
public class Test2 {

    public static void main(String[]args){

        Scanner sc = new Scanner(System.in);
        // T
        int t = sc.nextInt();
        int count = 0;
        while(count<t){

            int n,m,d;//城市数量，道路数量，距离
            n = sc.nextInt();
            m = sc.nextInt();
            d = sc.nextInt();

            int [][]arr = new int[n][n];
//            Arrays.fill(arr,-1);

            for(int l=0;l<n;l++){
                for(int p=0;p<n;p++){
                    arr[l][p] =-1;
                }
            }

            //构造图
            int l,r,w;
            for(int i=0;i<m;i++){
                l = sc.nextInt();
                r = sc.nextInt();
                w =sc.nextInt();
                arr[l][r] =w;
            }

            int []record = new int[n];

            Arrays.fill(record,0);

            for(int i =0;i<n;i++){

                int flag = 0;

                Set<Integer> set = new HashSet<>();
                set.clear();

                for(int j=0;j<n;j++){

                    for(int k=0;k<n;k++){
                       if(i==j){
                           if(arr[j][k]!=-1&&record[i]+arr[j][k]<d){
                               record[i]+=arr[j][k];
                               flag++;
                               set.add(k);//到达的地方
                           }
                       }else if(arr[j][k]!=-1&&set.contains(j)&&record[i]+arr[j][k]<d){
                           record[i]+=arr[j][k];
                           flag++;
                           set.add(k);//到达的地方
                       }


                    }
                }
            }

//            Arrays.sort(record);

            int min = record[0];

            int goal = 0;

            for(int s=1;s<record.length;s++){
                if(record[s]<=min){
                    min =record[s];
                    goal = s;
                }
            }


            System.out.println(goal);



            count++;
        }



    }
}
