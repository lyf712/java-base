package nju.base.chapter2.base;

import java.util.Scanner;

/**
 * @AUTHOR LYF
 * @DATE 2021/7/4
 * @VERSION 1.0
 * @DESC
 * 链表基本操作
 */
public class LinkList {
    ListNode head=new ListNode();

    // 头插法创建
    void createList(){
        Scanner scan =new Scanner(System.in);
        int val;
        while((val=scan.nextInt())!=-1){
            ListNode newNode = new ListNode(val,null);
            newNode.next = head.next;
            head.next = newNode;
        }
    }

    // 尾插入创建
    void creatListRail(){
        Scanner scan =new Scanner(System.in);
        int val;
        ListNode rail = head;// 需要.next进行连接起来,不能直接rail=head.next 再等于这样，因为为连接起来
        while((val=scan.nextInt())!=-1){
           rail.next = new ListNode(val,null);
           rail = rail.next;
        }
    }


    // 插入节点

    /**
     * 插入思路：
     * 先插入该节点的Next节点，在该点的next指向该节点
     * @param val
     * @param pos
     */
    void insertNode(int val,int pos){
        //LinkNode p = head;


    }


    void outPut(){
        ListNode p = head.next;
        while(p!=null){
            System.out.println(p.data);
            p=p.next;
        }
    }






}
