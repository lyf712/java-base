package nju.base.chapter2;

import java.util.Random;

/**
 * @AUTHOR LYF
 * @DATE 2021/7/4
 * @VERSION 1.0
 * @DESC
 */

public class Main {
    public static void main(String[]args){
        LinkList list = new LinkList();
        list.creatListRail();
        list.outPut();


        //--------------测试线性表--------------
//        LinearList<Integer> list = new SeqList<>();
//        list.add(2);
//        list.add(3);
//        for(int i=0;i<13;i++){
//            list.add(new Random().nextInt(33));
//        }
//        System.out.println("search"+list.search(3));
//        list.outPut();
    }
}
