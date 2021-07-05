package practice.lq.base.structrure.table;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/3
 * @VERSION 1.0
 * @DESC
 * 一、实现链表的基本操作
 * 1.创建
 * 2.CRUD
 *
 * 二、典型题目
 *
 * 三、问题记录
 * 1.逆置（普通算法，赋值时）
 * 2.合并链表
 *
 */
public class OperateList {

//    private Node node = new Node();
    private Node list = null;// 应该为空？

    // 其实无需？直接new 就OK了？？
    void createList(Node list){
       // list = new Node();// 需要提前分配,传入为空不能直接再分配
        list.setData("I am head");// 在类中使用private，因此需要使用setter，
        list.setNext(null);// 下一个为空
    }

    // 头插法
    Node headInsert(Node head,String[]data){

        for(int i =0;i<data.length;i++)
        {
            Node insertNode = new Node(data[i],null);// 分配新节点

            insertNode.setNext(head.getNext());// 先让插入节点连接上头结点的next,再让头结点指向此节点
            // C : insertNode->next = head->next;  head->next = insertNode;
            head.setNext(insertNode);
        }

        return head;
    }

    // 尾插法
    Node tailInsert(Node head,String[]data){

        Node point = null;
        point = head;
        for(int i=0;i< data.length;i++)
        {
             Node insertNode = new Node(data[i],null);
             point.setNext(insertNode);
             point  = point.getNext();// 指针后移
        }

        return head;
    }

    // 指定位置i（后面）插入（查找，查找方法类似，只是action为输出，以及查找固定值(max,min需要设定一个值进行比较)的POS也是一样，，）
    Node insertNodeByPos(Node list,int pos,Node insertNode){

        int i = 0;

        // 若采用顺序表进行存，则可提前进行POS的合法判断
        Node p = list;// 指针进行遍历
        while(p!=null){

            if(i==pos){// 插入 ,和头插入类似，先连接插入点的后面一个节点，再断开，进行插入点连接此节点
                insertNode.setNext(p.getNext());
                p.setNext(insertNode);
            }

            i++;
            p = p.getNext();
        }
        return list;
    }

    // 逆置(数组逆置直接双指针，变形还可以范围移动），
    // 链逆置 需要一趟遍历到尾部然后头插入，2n?还有其他办法吗 当然有
    // (1)空间换时间，设定一个新头结点，进行遍历头插入
    // (2)递归算法
    Node reserve1(Node list){

        OperateList opl = new OperateList();

        if(list.getNext()==null||list.getNext().getNext()==null){
            return list;// 为空链表或只有一个元素，无需逆置，为本身
        }

        Node head = new Node("new Head",null);

        Node p = list.getNext();

        while (p!=null){
            // p.getNext()待插入点
            //Node insertNode = p;
            Node insertNode = new Node(p.getData(),p.getNext());//？？为什么直接赋予p不行？

            insertNode.setNext(head.getNext());
            head.setNext(insertNode);
//            p.getNext().setNext(head.getNext());
//            head.setNext(p.getNext());
            p = p.getNext();
//            System.out.println("test:"+p.getData()+";");

        }

        // list = head;// 能带出吗？
        return head;// 传参不能带出，只能公共变量或者返回，，

    }

    // 递归逆置，利用回推时打印(返回值型，不返回值型）
    void reserve2(Node list){
        if (list.getNext() != null ) { //&& list.getNext().getNext() != null
            reserve2(list.getNext());// 不为空就进行递推，只到为空，则开始打印。然后回退时也进行打印！！理解回退的过程，结合栈考虑
        }
        System.out.print(list.getData()+"->"); // 为空或者1时
//        System.out.println();
        /*
        自己的逻辑
        if(list.getNext() == null){
        S.o.p()
        }else{
          reserve2(list.next);
          s.o.p()
        }
         以上系统修改后

         */
    }

    // 合并链表




    // 打印输出
    void printList(Node list){

        Node p = list;
        while(p.getNext()!=null){
            System.out.print(p.getData()+"->");
            p = p.getNext();
        }

        // 最后一个元素
        System.out.println(p.getData());
    }

    void test(){

        OperateList opl = new OperateList();
        Node listHead = new Node();// 必须预先分配？？  Cannot invoke "think.lq.base.structrure.table.Node.getNext()" because "p" is null
        opl.createList(listHead);
        opl.printList(listHead);
        String[] data = new String[10];
        for(int i=0;i<10;i++)
        {
            data[i] = "data"+i;
        }
        System.out.println("测试尾插:");
        opl.tailInsert(listHead,data);
        opl.printList(listHead);
        System.out.println("测试头插:");

        listHead = new Node("I am head",null);// 重新分配,结合OS的内存管理，内存分配
        opl.headInsert(listHead,data);
        opl.printList(listHead);


        // 测试插入
        opl.insertNodeByPos(listHead,1,new Node("I am test data",null));
        opl.printList(listHead);


        // 测试普通逆置
        System.out.println("测试普通逆置:");
        Node reserveList = opl.reserve1(listHead);
        opl.printList(reserveList);

        System.out.println("测试递归逆置:");
        opl.reserve2(reserveList);

    }


    public static void main(String[]args){
        OperateList opl = new OperateList();
        opl.test();
    }


}
