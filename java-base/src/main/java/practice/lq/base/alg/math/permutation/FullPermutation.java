package practice.lq.base.alg.math.permutation;

import java.util.Arrays;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/6
 * @VERSION 1.0
 * @DESC
 * 全排列
 * 一、排列组合问题
 *
 * 全排列参考文章：https://blog.csdn.net/u013309870/article/details/68941284
 *
 */
public class FullPermutation {

    // nums排列的字符,start排列的起点
    void fullPermutation(String nums,int start,int end){

        if(start==end)
        {
            System.out.println(nums.charAt(end));
        }
        else
            {
            for(int i = end;i>=start;i--)
            {
                fullPermutation(nums,i,end);
            }
        }

    }

    // 暴力法
    void fullPV(String nums){
        nums="12345";
    }

    // 递归办法
    void permutation(int[]nums,int start){

        // 前面已经排好序，直接输出
        if(start==nums.length-1){
            System.out.println(Arrays.toString(nums));
        }

        for(int i =start;i<nums.length;i++){
           swap(nums,start,i);// 将第一个与后面的依次交换
           permutation(nums,start+1);// 排列下一个
           swap(nums,start,i);// 交换回来
        }

    }

    void swap(int[] nums,int i,int j ){
        int temp = nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }

    // 字典排序



    public static  void main(String[]args){
        FullPermutation fp = new FullPermutation();
        int[] nums={1,2,3,4};
        fp.permutation(nums,0);

    }





}
