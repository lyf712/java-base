package practice.offer;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/25
 * @VERSION 1.0
 * @DESC
 * No.3 二维数组寻找目标值
 * 题目描述：在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列
 * 都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一
 * 个整数，判断数组中是否含有该整数。
 *
 */
public class T1 {

    // 折半查找回顾
    void binarySearch(){
        int target=10;
        int[] arr ={1,2,4,8,9,10,23,34,40};
        int left = 0,right = arr.length-1;
        int targetI = (left+right)/2;
        while(arr[targetI]!=target){
            if(target>arr[targetI]){
                left = targetI+1;
            }else {
                right = targetI-1;
            }
            targetI = (left+right)/2;
        }
        if(arr[targetI]==target){
            System.out.println("targetI:"+targetI+";target:"+target);
        }else{
            System.out.println("cant find");
        }
    }

    // 二维数组寻找目标值
    // 转换为一维
    //  row = num/n ,col = num%n


    public static void main(String[]args){
        T1 t1 = new T1();
        t1.binarySearch();
    }





}
