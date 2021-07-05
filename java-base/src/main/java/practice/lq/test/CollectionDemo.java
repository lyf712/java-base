package practice.lq.test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/1
 * @VERSION 1.0
 * @DESC
 */
public class CollectionDemo {

    static void collectionP(){

        int[]arr = new int[10];

        Vector<Integer> v;
        Queue<Integer> q;

        List<Integer> list = new ArrayList<>();

        Set<Integer> set = new HashSet<>();

        Map<Integer,String> map = new HashMap();


        // stream、lambda?

        //stream的使用

        // https://blog.csdn.net/yihuaiyan/article/details/101050144
        // stream的对象可以是 collection、集合，数组，I/O channel， 产生器generator
        // 1.forEach 遍历产生动作（哪些动作

        // 2.filter 过滤不需要的元素  ->{}

        // 3.map 映射，发生改变产生结果

        // 4.统计  count

        // 5.collect 收集，Collectors.


        list = Arrays.asList(4,1,2,3,2);

       List<Integer> list1 =  list.parallelStream().map(i->i*i).collect(Collectors.toList());

       list1.stream().forEach(System.out::println);

        List<Integer> list2 =  list.parallelStream().map(i->i*i).distinct().collect(Collectors.toList());



        list2.stream().forEach(System.out::println);

       long count =list.stream().filter(i->i!=1).count();
        System.out.println(count);


        System.out.println(list.stream().max(Comparator.naturalOrder()));

        IntSummaryStatistics is = list.stream().mapToInt((x)->x).summaryStatistics();



    }

    public static void main(String[]args){
        CollectionDemo.collectionP();
    }



}
