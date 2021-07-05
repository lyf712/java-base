package nju.base.chapter2.base;

/**
 * @AUTHOR LYF
 * @DATE 2021/7/4
 * @VERSION 1.0
 * @DESC
 */
public  interface  LinearList<E> {
    // 线性表接口,类属性应有size、length
    //-----基本属性类方法---
    int size();// 线性的最大容量
    int length();// 线性表的长度,
    boolean isEmpty();
    boolean isFull();
    <E> E getData(int i);// 取值
    <E> void setData(int i,E e);//赋值


    //------查询类方法-----
    <E> int search(E e);// 搜索某个值的位置
    int locate(int i);// 定位i的取值？？


    //------增删改查更-----
    <E> int insert(int i,E e);//在i插入
    void delete(int i);//删除位子i的值
    <E> void update(int i,E e);//更新i位子的值,对应setData
    void sort();//排序

    <E> void add(E e);//末尾添加,线性表特性 首尾
    void outPut();

    void copy(LinearList list);//赋值
}
