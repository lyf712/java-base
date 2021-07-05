package practice.lq.base.alg.sort;

import java.util.Random;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/5
 * @VERSION 1.0
 * @DESC
 * 参考文章：https://www.cnblogs.com/onepixel/articles/7674659.html
 *
 * 一、说明
 * 1.十大排序以及时间空间复杂度说明
 *
 *
 * 二、分类说明
 * 1.比较类
 *
 * （1）交换类
 *  冒泡、快速
 *  (2)插入
 *  简单插入、希尔
 *  （3）选择
 *  简单选择、堆排序
 *  （4）归并
 *  二路归并、多路
 *
 * 2.非比较类
 * （1）计数排序
 * （2）桶排序
 * （3）基排序
 *
 * 重点掌握：快速排序和插入排序
 *
 * 二、问题记录
 *
 *
 *
 */
public class TenSort {


    private static int[] testData = new int[100];

    static {

        for(int i =0;i<100;i++)
        {
            Random r = new Random();
            testData[i] =r.nextInt(1000);
        }

        System.out.println("TestDaTa:");
        for(int i = 0;i<100;i++)
        {
            System.out.println(testData[i]);
        }

    }

    // 冒泡排序,时间复杂度: O(n^2) ,n*n/2
    // 思想:将大的或者小的移动到一边（在遍历时就进行交换了，定向移动）
    void bubbleSort() {

        for (int i = 0; i < testData.length - 1; i++) {
            for (int j = 0; j < testData.length-i-1; j++) { // 排到后面的就无需再进行比较
                if (testData[j] > testData[j + 1]) // 大的就往后面移
                {
                    int temp;
                    temp = testData[j];
                    testData[j] = testData[j + 1];
                    testData[j + 1] = temp;
                }
            }
        }

        System.out.println("处理的结果为:");
        for (int i = 0; i < 100; i++) {
            System.out.println(testData[i]);
        }
    }


    // 简单选择
    // 思路类似冒泡，但是采用的方法不同，遍历时选择最小的值，最后将最小值放在前面
    // n^2
    void simpleChooseSort(){

        for(int i =0;i<testData.length-1;i++){
            //int min = testData[i];
            // 应该记录位置，否则无法进行交换
            int minIndex = i;
            for(int j = i+1;j< testData.length;j++){
              if(testData[j]<testData[minIndex]){
                  int temp = testData[j];
                  testData[j] = testData[minIndex];
                  testData[minIndex]=temp;
              }
            }
        }

        System.out.println("处理的结果为:");
        for (int i = 0; i < 100; i++) {
            System.out.println(testData[i]);
        }
    }


    // 插入排序
    // 思路:将未排序的进行在排好序中进行比较插入，需要设立一个额外的值，类似打牌
    void insertSort(){

        for(int i =1;i<testData.length;i++){ // 遍历未排序
            int insertVal = testData[i]; //待排序点

            for(int j = i-1;j>=0;j--){ // 遍历已排序，在其中进行插入
               // 发现比左边的大，右边的小即可插入
                // =》只需要考虑是否大于即可
                // 使用临时遍历存储了第i个位置，前i-1个位置则是未排序的,然后进行比较后移，
                // 若比其小则后移，比其大则可进行插入，当最后到0的边界时候，需要考虑进行直接插入


//                if(j==0){//考虑边界，如果遍历到0时插入点更小则插入在0位置，否则插入在1位置
//
//                  if(insertVal<testData[0]){
//                      testData[i]=testData[0];
//                      testData[0]=insertVal;
//                  }else{
//                      testData[i]=testData[1];
//                      testData[1]=insertVal;
//                  }
//                    break;
//
//                }else {

//                    if(insertVal<testData[j]&&insertVal>testData[j-1]){
//                        //
//                    }
                    if(insertVal<testData[j]){
                        testData[j+1] = testData[j];// 将其后移
                        if(j==0){
                            testData[j]=insertVal;
                        }
                    }else {
                        // 大于，则插入
                        testData[j+1]=insertVal;// j+1这个位置是空出来的
                        break;
                    }

//                }

            }
        }

        System.out.println("处理的结果为:");
        for (int i = 0; i < 100; i++) {
            System.out.println(testData[i]);
        }

    }
    // 希尔排序
    // 思路: 设定一定间隔进行插入排序，直到间隔为1
    void shellSort(){

        for(int gap = testData.length/2;gap>=1;gap/=2){

            for(int i = 0;i< testData.length;i+=gap){



            }

        }
    }


    // 快速排序 !!
    void quickSort(){


    }

    //二路归并
    // 思想: 将序列分为两个子序列，然后堆子序列进行归路排序，再合并

    void mergeSort(){

    }





    public static void main(String[]args){

        TenSort ts=new TenSort();
        ts.insertSort();


    }




}
