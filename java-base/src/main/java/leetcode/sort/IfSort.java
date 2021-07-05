package leetcode.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @AUTHOR LYF
 * @DATE 2021/6/6
 * @VERSION 1.0
 * @DESC
 * 数组条件排序
 */
public class IfSort {
    static void arrIfSort(){
        String [] strings = {"0","10","1","1010"};
        // ::  类直接方法引用
        Arrays.sort(strings, Comparator.comparing(String::length));
        for(int i=0;i<strings.length;i++)
        {
            System.out.print(strings[i]+" ");
        }


    }
    public static void main(String[]args){
        arrIfSort();
    }
}
