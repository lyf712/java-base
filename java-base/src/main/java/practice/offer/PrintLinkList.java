package practice.offer;

import java.util.Stack;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/25
 * @VERSION 1.0
 * @DESC
 * 题目描述：输入一个链表，从尾到头打印链表每个节点的值。
 * 思路：借助栈实现，或使用递归的方法。
 */
class Node{
    int val;
    Node next;//不赋值则为空
    public Node(int val) {
        this.val = val;
    }
}
public class PrintLinkList {

    /**
     * @PARAM 链表
     */
    private Node linkList = new Node(1);

    void init(){
        Node p;
        p = linkList;
        for(int i=2;i<10;i++){
            p.next = new Node(i);
            p = p.next;
        }

        System.out.println("init linkList is...");
        p = linkList;
        while(p!=null){
            System.out.print(p.val+" ");
            p = p.next;
        }
        System.out.println();

    }

    // 采用栈的方法
    void f1(){
        init();
        Stack<Node> nodeStack = new Stack<>();
        Node p = linkList;//指针
        while(p!=null){
           nodeStack.push(p);
           p = p.next;
        }
        while(!nodeStack.isEmpty()){
            System.out.print(nodeStack.peek().val+" ");
            nodeStack.pop();
        }
        System.out.println();
    }

    // 采用递归
    void f2(Node node){
        if(node!=null){//非空一直递归,直到为空则出来f2就开始打印（也就是回退过程）
            f2(node.next);
            System.out.print(node.val+" ");
        }
    }

    public static void main(String[]args){
        PrintLinkList printLinkList = new PrintLinkList();
        //printLinkList.init();
        printLinkList.f1();
        printLinkList.f2(printLinkList.linkList);
    }
}
