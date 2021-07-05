package practice.lq.questions.consolidate.simulate;

import java.util.*;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/14
 * @VERSION 1.0
 * @DESC
 *
 * 外卖优先级
 *
 * 题目描述:
 * n家店，t时刻中有m个订单，每走一时刻则优先级进行下降1个优先级，若对应优先级为0则无法下降
 * 若有订单则优先级+2，当优先级》5则加入缓存，小于等3则清除优先级
 *
 * 问题解决
 * 1.抽象数据结构
 * n,t,m
 * 优先级:数组 下标为店家编号,val为优先级，每过一时刻调整一下优先级在调整过程中 进行判断优先级的大小是否加入缓存或者清除缓存
 * 缓存:数组 下标为对应的店家编号，val为Boolean值是否加入缓存。（方便处理，但是空间销毁略大，若店家多不太实用？）
 * 考虑使用map,满足的则加入，不满足的remove
 * 实际上，使用一个list即可，因为无需知道缓存中店家的优先级，有优先级数组在维护优先级大小，因此缓存只需存储店家编号
 *
 * 时间流:时刻对应店家编号具有的订单量
 * 采用类存储，map存储只能存储一个key，同一时间可以多家有外卖单
 * class{int no,t;}
 *
 *
 * 2.输入
 * n,t,m
 * ...
 * 3.解决规则
 * 遍历时间流（）,判断该时刻每个店家具有的订单量，若无则则判断优先级是否为0，为0继续保持，不为零则-1
 * 有则进行判断订单数量，优先级+订单数量*2
 *
 *
 *
 *
 */

class TimeNode{
    int t,no;

    public TimeNode() {
    }

    public TimeNode(int t, int no) {
        this.t = t;
        this.no = no;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "TimeNode{" +
                "t=" + t +
                ", no=" + no +
                '}';
    }
}

public class SellerPrior {


    //private Map<Integer,Integer> cache = new HashMap<>();// 缓存
    private List<Integer> cache = new ArrayList<>();
    private int n;//店家数量
    private int t;//时间
    private int m;//外卖订单量
    private int [] sellerPrior = new int[n];//店家优先级
    private List<TimeNode> timeStream = new ArrayList<>();//时间流

    // 输入数据
    void inputData(){
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        t = scanner.nextInt();

        for(int i =0;i<m;i++)
        {
            timeStream.add(new TimeNode(scanner.nextInt(),scanner.nextInt()));
        }
        //初试优先级
        Arrays.fill(sellerPrior,0);
    }

    // 比较笨的方法
    // 每一个时间去统计该时间各个店家的外卖数量再去维护缓存
    void handle1(){

        for(int i =1;i<=t;i++){//时间
            for(int j = 1;j<=n;j++){//店家
                int finalJ = j;
                int finalI = i;// 三重循环？
                long num = timeStream.stream().filter(tn->tn.t== finalI &&tn.no== finalJ).count();
                System.out.println(i+"时刻"+j+"店家具有"+num+"个外卖");

                if(num==0){//无订单，只需考虑是否清除缓存
                    if(sellerPrior[j-1]!=0){// ==0无需管，保持0
                        sellerPrior[j-1]--;//若该店家优先级非0则 -1
                        // 判断是否在缓存
                        if(sellerPrior[j-1]<=3){
                            int no = j-1;
                            if(cache.contains(no)){// 若该店家在缓存中则清除 map: cache.get((Object)no)!=null
                                 cache.remove((Object)no);
                            }
                        }
                    }
                }else{// 考虑有订单
                    int no = j-1;
                    sellerPrior[no]= (int) (sellerPrior[no]+num*2);

                    // 判断是否加入缓存
                    if(sellerPrior[no]>5){
                        cache.add(no);
                    }

                }

            }
        }


    }
    // 使用双指针，先进行时间按时间排序，再逐一

    void handle2(){

    }

    void outPutData(){
        System.out.println(cache.size());
    }

    void testData(){
        n =2;
        t =6;
        m =6;

        timeStream.add(new TimeNode(1,1));
        timeStream.add(new TimeNode(5,2));
        timeStream.add(new TimeNode(3,1));
        timeStream.add(new TimeNode(6,2));
        timeStream.add(new TimeNode(2,1));
        timeStream.add(new TimeNode(6,2));
        sellerPrior = new int[n];// 需要再构造一下对象，刚开始声明时n默认为0
        //初试优先级
        Arrays.fill(sellerPrior,0);

    }
    void testPrint(){
        System.out.println("n,t,m:"+n+";"+t+";"+m);
        timeStream.stream().forEach(System.out::print);
        System.out.println(sellerPrior.length);
        System.out.println();
        for(int i=0;i<sellerPrior.length;i++){
            System.out.print(sellerPrior[i]+" ");
        }
    }




    static void testMap(){
        Map<Integer,Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        list.add(3);
        System.out.println(list.size());
        list.remove((Object)3);
        System.out.println(list.size());
        if(map.get((Object)10)!=null){
            System.out.println("非空");
        }else{
            System.out.println("空");
        }

        long t = 12;
        int m = 0;
        m = (int) (t*2+1);
        System.out.println(m);


    }


    public static void main(String[]args){
         SellerPrior sellerPrior = new SellerPrior();
         sellerPrior.testData();
         sellerPrior.testPrint();
         sellerPrior.handle1();
         sellerPrior.outPutData();
    }




}
