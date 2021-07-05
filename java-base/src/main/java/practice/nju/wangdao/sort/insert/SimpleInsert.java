package practice.nju.wangdao.sort.insert;

import practice.nju.wangdao.sort.RandomData;


/**
 * @AUTHOR LYF
 * @DATE 2021/5/5
 * @VERSION 1.0
 * @DESC
 * 简单插入排序
 */
public class SimpleInsert {
    static private int[] testData = RandomData.data;

    /**
     *思路:
     * 将待排序序列分为两个子列，前面子列为已排序，后面为未排序，进行遍历未排序，然后插入已排序
     * 直到终点
     *
     */
    static void simpleInsert(){
        // 默认长度大于1,等于小于1无需排序
        for(int i =1;i< testData.length;i++)
        {
            int temp = testData[i];
            // 进行未排序子列插入
            int k = i;//从i-1,也就待插入点的前一个点进行遍历
            while(k>0&&temp<testData[--k])
            {
                    testData[k+1]=testData[k];//向后移动
            }

            testData[k] =temp;
        }

        System.out.println("排序之后:");
        for(int i =0;i<100;i++)
        {
            System.out.print(testData[i]+" ");
        }
    }

    public static void main(String[]args){
        SimpleInsert.simpleInsert();
    }


}
