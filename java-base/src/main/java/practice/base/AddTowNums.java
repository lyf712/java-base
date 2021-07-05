package practice.base;

/**
 * @AUTHOR LYF
 * @DATE 2021/6/24
 * @VERSION 1.0
 * @DESC
 */
public class AddTowNums {
    static class  Solution2 {
        public ListNode rotateRight(ListNode head, int k) {
            //
            int c=0;
            ListNode rs = new ListNode();
            rs.next = head;
            while(c<k){
                ListNode p  = rs.next;
                while(p!=null){
                    //ListNode temp = p;//保留原来位置
                    //p.next=rs.next;// 会导致无限 val为2的。。
                    ListNode insertNode = new ListNode(p.val);
                    insertNode.next = rs.next;
                    rs.next = insertNode;
                   // rs.next = p;
                   // p=temp.next;
                    p=p.next;
                }
                c++;
            }
            return rs.next;

        }
    }



       static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

            ListNode rs = new ListNode();//Cannot assign field "val" because "r" is null
            //ListNode rs = new ListNode(0,new ListNode());
            //rs.val =0;

            ListNode p = l1;
            ListNode q = l2;

            ListNode r = rs;// Java引用，指针？

            while(p!=null||q!=null){
                if(p!=null && q!=null){
                    r.val = p.val+q.val;
                    p=p.next;
                    q=q.next;
                }else if(p!=null){
                    r.val = p.val;
                    p=p.next;
                }else{
                    rs.val = q.val;
                    q=q.next;
                }
                rs.next= new ListNode();// 声明一个对象,否则为空
                rs=rs.next;////Cannot assign field "val" because "r" is null
            }

            // 处理进位
            r = rs;
            while(r!=null){
                if(r.val>9){
                    r.val%=10;
                    r.next.val++;
                }
                r=r.next;
            }
            return rs;
        }

    }
    public static void main(String[]args){
           ListNode l1 = new ListNode();
           ListNode l2 = new ListNode();
           l1.val = 2;
           l1.next = new ListNode(3);
           l1.next.next = new ListNode(5);

           l2.val =3;
           l2.next = new ListNode(4);
           l2.next.next = new ListNode(6);
           new Solution().addTwoNumbers(l1,l2);

           new Solution2().rotateRight(l1,3);
    }
}
