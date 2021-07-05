package nju.base.chapter2;

import java.util.Arrays;

import static java.lang.System.exit;

/**
 * @AUTHOR LYF
 * @DATE 2021/7/4
 * @VERSION 1.0
 * @DESC
 */
public class SeqList<E> implements LinearList<E>{
    // 顺序表

    int size=10;//容量(最大)
    int length=0;//当前长度
    Object[] elements ;// 数据

    public <E> SeqList(){
        elements = new Object[size];//所有类的父类
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public <E> E getData(int i) {

        return null;
    }

    @Override
    public <E> void setData(int i, E e) {
        if(i>=length){
           elements= Arrays.copyOf(elements,i+1);
           System.out.println("进行扩容...");
        }
        elements[i]=e;
    }

    /*
    主要思想：
   （1）从表的第一个数据元素起，依次和x进行比较，
     若存在某个表项的值和x相等，则查找成功，并返回该表项的位置。
   （2）如果查遍整个顺序表，都没有找到其值和x相等的表项，则查找不
     成功，并返回-1。
     时间复杂度分析
     最好： 1
     最坏： n
     平均： (n+1)/2
     O(n)
     */
    @Override
    public <E> int search(E e) {
        int i=0;
        while(i<length&&elements[i++]!=e);//一直寻找直到匹配

        if(i>=length)
            return -1;
        else
            return i-1;
    }

    @Override
    public int locate(int i) {


        return 0;
    }


    /*
主要思想：

（1）检查插入操作要求的有关参数的合理性；
（2）将顺序表最后位置加1
（3）将第i至第n-1个表项依次往后移动一个位置；
（4）把新的表项插入在顺序表的第i个位置

 时间复杂度分析
 最好： 0
 最坏： n
 平均： n/2
 O(n)
 */
    @Override
    public <E> int insert(int i, E e) {
          // 插入,-1非法插入，0 插入失败  1插入成功
        if(i<0||i>length){
            return -1;// 非法插入
            //exit(0);
        }
        length++;//插入之后,长度加一
        if(length>size){
            grow();//此处要么处理为动态扩容或者处理为不合法
        }
        for(int k=length-1;k>i;k--){//注意数组与长度指标对应
            elements[k]=elements[k-1];
        }
        elements[i]=e;
        return 1;
    }

    public boolean grow(){
        System.out.println(System.currentTimeMillis()+"进行扩容...");
        size +=10;//扩容十个
        elements=Arrays.copyOf(elements,size);
        return true;
    }

    /*
主要思想：
(1) 在顺序表中查找x,如果x在表中不存在，则不能删除；
 (2)如果x在表中存在，设x在顺序表中的位置为i;
 (3) 将顺序表的最后位置减1；
 (4)把顺序表中原来第i+1至第n-1个表项依次向前移动一个表项位置


 时间复杂度分析
 最好： 0
 最坏： n-1
 平均： (n-1)/2
 O(n)
 */

    @Override
    public void delete(int i) {

    }

    @Override
    public <E> void update(int i, E e) {

    }

    @Override
    public void sort() {

    }

    @Override
    public <E> void add(E e) {
        length++;
        if(length>size){
            grow();
        }
        elements[length-1]=e;
    }

    @Override
    public void outPut() {
       for(int i=0;i<length;i++)
           System.out.println(elements[i]);
    }

    @Override
    public void copy(LinearList list) {

    }
}
