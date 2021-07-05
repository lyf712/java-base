package practice.lq.completation.recursion;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/31
 * @VERSION 1.0
 * @DESC
 */

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

public class T4 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        // 将链表2合并到链表1上面
        ListNode p1 = l1,p2 = l2;

        // 双指针
        while(p1!=null&&p2!=null){// 当都不空时进行合并处理,其中p1先空则直接指向l2,若p2先空则不用管
            if(p2.val>=p1.val&&p2.val<=p1.next.val){
                ListNode temp = new ListNode(p2.val);
                temp.next = p1.next;
                p1.next = temp;

                p1=p1.next;
                p2=p2.next;

            }else{
                p1=p1.next;
            }

        }

        if(p2!=null){// p2还没合并完
            p1.next = p2;
        }

        return l1;
    }
    public static void main(String[]args){
        T4 t4 = new T4();
        ListNode l1= new ListNode(1);
        ListNode l2 = new ListNode(1);

        l1.next= new ListNode(2);
        l1.next.next= new ListNode(4);
        l1.next.next.next= null;

        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        l2.next.next.next=null;
        ListNode listNode =t4.mergeTwoLists(l1,l2);
        ListNode p = listNode;

        while (p!=null){
            System.out.print(p.val+"->");
            p=p.next;
        }
    }
}
