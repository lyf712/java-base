package nju.base;

import org.apache.poi.hssf.record.formula.functions.T;

import java.util.Comparator;
import java.util.List;

import static java.lang.System.exit;

/**
 * @AUTHOR LYF
 * @DATE 2021/7/3
 * @VERSION 1.0
 * @DESC
 */
public class Chapter1 {

    void handle(List<T> data){
      exit(0);
    }
    <Q> void hello(Q[]arr){
        Q q=arr[1];
        System.out.println(q);
    }

    // 模板
    <E> void sort(E[] arr){
        for(E t:arr){
            System.out.printf("%s",t);
            System.out.println(t);
        }
    }

    <E> void selectSort(E[] arr ,Comparator<E> comparator){
        // 选择排序,数据为数值型
        for(int i=0;i<arr.length;i++){
            // 对第i个位置进行选取数
            int temp = i;
            for(int j=i;j<arr.length;j++){
                if(comparator.compare(arr[j], arr[temp])<0)//arr[j]<arr[temp]
                    temp=j;
            }
            E t = arr[temp];
            arr[temp]=arr[i];
            arr[i]=t;
        }
    }

    int MAX_INT = Integer.MAX_VALUE;
    void compute(int []arr,int n){
        arr = new int[n];
        arr[0]=1;
        for(int i=1;i<n;i++){
            if(arr[i-1]*2*i>MAX_INT||arr[i-1]*2*i<0)
                exit(-1);
            else
                arr[i]=arr[i-1]*2*i;
        }
        for(int i=0;i<n;i++)
            System.out.println(arr[i]);
    }

    public static void main(String[]args){

        int[]arr = new int[10000];
        new Chapter1().compute(arr,10000);
        System.out.println(arr[9999]);

//        Integer[] arr = {4,2,3,9,1,7,0,12};
//        new Chapter1().selectSort(arr, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1.intValue()-o2.intValue();
//            }
//        });
//        for(Integer i:arr)
//            System.out.println(i);

    }


}
