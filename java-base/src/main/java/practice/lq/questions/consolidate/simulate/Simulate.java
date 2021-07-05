package practice.lq.questions.consolidate.simulate;

import java.util.*;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/13
 * @VERSION 1.0
 * @DESC
 * 模拟题
 *
 * 务必先进行抽象，分析完整的需求之后再选取数据结构
 * 数结构的选取，根据集合特性以及数组或者动态分配
 *
 *
 */
public class Simulate {

    // 外卖店优先级

    static int n;//n家外卖店
    // 存储优先级
    static int [] seller = new int[n];// 编号为下标+1
    static {
        Arrays.fill(seller,0);//初试优先级为0
    }

    // 缓存 <no,prior>
    Map<Integer,Integer> cache = new HashMap<>();

    class node{
        int t;//时刻
        int no;//某个店家获取一个订单
        public node() {
        }

        public node(int t, int no) {
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
            return "node{" +
                    "t=" + t +
                    ", no=" + no +
                    '}';
        }
    }


    void test(){
        int t,m;
        Scanner scan = new Scanner(System.in);
        n =scan.nextInt();
        m =scan.nextInt();
        t =scan.nextInt();

        List<node> list = new ArrayList<>();

        for(int i =0;i<m;i++){
           list.add(new node(scan.nextInt(),scan.nextInt()));//添加时间序列
        }

//        list.sort(new Comparator<node>() {
//            @Override
//            public int compare(node o1, node o2) {
//                return o1.t-o2.t;
//            }
//        }); // 需要使用属性获取，使用getter会出问题》》？

//        list.sort(new Comparator<node>() {
//            @Override
//            public int compare(node o1, node o2) {
//                return o1.getT()-o1.getT();
//            }
//        });

        list.sort((o1, o2) -> o1.t-o2.t);
//        AtomicInteger i = new AtomicInteger();
//        i.set(list.get(0).t);//当前时刻
//
//        list.stream().forEach(
//                (node)->
//
//
//        );//遍历时间流

//        int curT = list.get(0).t;
//
//        for(int i=0;i< list.size();i++){
//        }

        // 不考虑时间复杂度先
        for(int i =0;i<t;i++){//应遍历时刻去，在时刻统计每个店家的外卖量，然后进行cache维护

            Long []nums = new Long[n];//记录每个店的该时间数量

            for(int j = 0;j<n;j++){

                int finalI = i;
                int finalJ = j;
                nums[j]=list.stream().filter(node -> node.t== finalI &&node.no== finalJ).count();
            }

            System.out.println("第"+i+"时刻,每个店的外卖数量");

            for(int k = 0;k<n;k++){
                System.out.print(nums[k]+"->");
            }
        }

        // 测试统计
//        System.out.println("test:"+list.stream().collect());


    }

    public static void main(String[]args){
        Simulate s= new Simulate();
        s.test();
    }







}
