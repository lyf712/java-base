package practice.lq.completation.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/30
 * @VERSION 1.0
 * @DESC
 * 1.简单递归题
 * (1)求子集（即组合问题）,无先后顺序
 * 见 题型 2的异或
 * (2)求排列
 * **
 * 全排列
 *
 * (3)回溯法
 * N皇后,棋盘限定递归
 *
 * 2.
 */
public class SimpleT {

     /** 2 的 幂
     *  https://leetcode-cn.com/problems/power-of-two/
      *  判断是否为2的幂
     */
     // 超时
     boolean isTowPower(int n){
         int sum=1;
         for(int i=0;;i++){
             if(n==sum)
                 return true;
             if(n<sum)
                 return false;
             sum*=2;
         }
     }
    boolean isPowerOfTwo2(int n) {
        if(n<=0)
            return false;// 考虑负数
        while(n>1){// 等于0跳出
            if(n%2!=0)
                return false;
            n/=2;
        }
        return true;
    }
    // 递归办法
    boolean isP(int n){
         if(n<=0)
             return false;
         else if(n==1)// 若除2直到n为1则说明是2的倍数
             return true;
         else if(n%2!=0)// 满足条件则进行递归
             return false;
         else return isP(n/2);
    }

    // 位运算
    // 2的倍数
    // 解释说明: n&n-1 移除最低位1 -> 因此若是2的幂则仅有的1则被移除,且运算后则为0
    boolean isPowerOfTwo3(int n){
         return n>0&&(n&(n-1))==0;
    }

    // 异或所有子集求和

    // dfs深搜（回溯）求子集 bfs广搜索堆栈

    int res = 0;
    List<List<Integer>> record = new ArrayList<>();
    //List<Integer> tempRecord = new ArrayList<>();
    List<String> record2 = new ArrayList<>();
    /**
     * 思考递归与树 结构以及回溯算法的应用
     *
     * @param val 异或的值
     * @param nums 数组
     * @param idx 递归的深度当前位置
     * @return
     */
    void dfs(List<Integer> tempRecord,int val,int []nums,int idx){

         if(idx == nums.length){// 递归终止，在该有的长度都进行了选择

             record.add(tempRecord);

             AtomicReference<String> temp = new AtomicReference<>("");
             tempRecord.stream().forEach(
                     e->{
                         temp.updateAndGet(v -> v + e);
                     }
             );
             record2.add(temp.get());
//             System.out.println("------record1----");
//             for(List<Integer> list:record){
//                 for(Integer integer:list){
//                     System.out.print(integer+"");
//                 }
//                 System.out.println();
//             }
//             System.out.println("------record2----");

             //tempRecord.clear();
             tempRecord.stream().forEach(System.out::print);
             System.out.println();

             res += val;
             return;
         }

        // 选择当前位置
        tempRecord.add(nums[idx]);
        dfs(tempRecord,val^nums[idx],nums,idx+1);

        // 不选择当前位置
        tempRecord.remove((Object)nums[idx]);
        dfs(tempRecord,val,nums,idx+1);// 计数 idx进行递推,val进行递归计算
    }


    /**
     * 关键在于求子集
     * 递归求子集
     * int[] nums//@param nums
     */
    int subsetXORSum() {
        int sum=0;
        int[] nums1 = {1,2,3,4};

        List<Integer> tempRecord = new ArrayList<>();
        dfs(tempRecord,sum,nums1,0);

        System.out.println("recordSize:"+record.size());
        for(List<Integer> list:record){
            for(Integer integer:list){
                System.out.print(integer+"");
            }
            System.out.println();
        }
        System.out.println("recordSize2:"+record2.size());
        record2.stream().forEach(e-> {
                    System.out.print(e + "->");
                }
            );
        return sum;
    }

    int subsetXORSum(int[] nums) {
        int res = 0;
        int n = nums.length;
        for (int i = 0; i < (1 << n); ++i){   // 遍历所有子集
            int tmp = 0;
            for (int j = 0; j < n; ++j){   // 遍历每个元素

                if ((i & (1 << j))!=0){
                    tmp ^= nums[j];
                }
            }
            res += tmp;
        }
        return res;
    }



     public static void main(String[]args){

         SimpleT simpleT = new SimpleT();
         simpleT.subsetXORSum();

         System.out.println("record:");
         simpleT.record.stream().forEach(e->{
             e.stream().forEach(e1->{
                 System.out.print(e1);
             });
             System.out.println();
         });
         System.out.println("XOR:"+simpleT.res);


//         System.out.println(5^1);
//         System.out.println(~3);
//         System.out.println(5<<1);
         // System.out.println();
         //System.out.println(simpleT.isPowerOfTwo3(5));
     }

}
