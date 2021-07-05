package practice.lq.questions.province_2019;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/7
 * @VERSION 1.0
 * @DESC
 * 2019年B组
 */
public class TestB {

    // 组队
    // 求最大首发队员和
    // 直接计算，无需
    // 490
    void test1(){
    }
    // 不同子串
    // 遍历
    // 88
    void test2(){
        // 15位
        String str = "010011000101001";

        // 遍历组合，加入set
        Set<String> set = new HashSet<>();

        for(int  i=0;i<str.length();i++){
            for(int j = i;j<str.length();j++){
                set.add(str.substring(i,j+1));
            }
        }

        System.out.println("res:"+set.size());

        set.stream().forEach(System.out::println);

    }

    // 数列求值

    //4659
    void test3(){

        int a=1,b=1,c=1;

        for(int i =4;i<=20190324;i++){
            int temp1=c; // c = a+b;
            c = (a+b+c)%10000;// a=b;
            a=b%10000; // b =c;
            b=temp1%10000;
        }

        System.out.println(c);

//      /*  System.out.println(f(2019));*/
    }

    boolean judge(int n){
        String str = n+"";
        for(int i = 0;i<str.length();i++){
            if (str.charAt(i)=='2'||str.charAt(i)=='4')
                return false;
        }
        return true;
    }

    // 数的分解
    // 2019进行3个
    //   40785 暴力+排列组合
    void test4(){

        int count= 0;
        for(int i =1999;i>=1009;i--){

            int sum = 2019-i;
            //分解sum
            for(int j =1;j<sum;j++){
                if(judge(j)&&judge(sum-j)){
                    count++;
                }
            }
        }
        System.out.println(count);
    }


    boolean f6(int n){
        String str = n+"";
        Set<Character> set = new HashSet<>();
        set.add('2');
        set.add('0');
        set.add('1');
        set.add('9');
        for(int i =0;i<str.length();i++){
            if(set.contains(str.charAt(i))){//
                return true;
            }

        }

        return false;
    }

    // 特别数之和
    void test6(){

        Scanner scanner = new Scanner(System.in);
        int val=scanner.nextInt();

        int sum=0;
        for(int i =1;i<=val;i++){
            if(f6(i)){
                sum+=i;
            }
        }

        System.out.println(sum);

    }

    // 外卖优先级
    // 模拟题

    // N （店家数量，时间，编号有一个订单）
    // 考虑会不会存在同时具有订单，肯定可以的啊
    // 进行时间遍历，排序

    class TimeNode implements Comparator<TimeNode>{

        private int time;//时刻
        private int no;//序号

        @Override
        public int compare(TimeNode o1, TimeNode o2) {
            return o1.time-o2.time;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getTime() {
            return time;
        }
    }


    void test7(){
        int n,m,t;// n 编号，m 订单信息，t时间

        Map<Integer,Integer> cache = new HashMap<>();//店家编号以及优先级
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();

        int [] merchant = new int[n];// 编号-1才是对应的

        Arrays.fill(merchant,0); // 优先级

        m = scan.nextInt();
        t = scan.nextInt();

        List<TimeNode> list = new ArrayList<>();

        for(int i =0;i<m;i++){
          TimeNode node = new TimeNode();
          node.setTime(scan.nextInt());
          node.setNo(scan.nextInt());
          list.add(node);
        }

        Collections.sort(list, new Comparator<TimeNode>() {
            @Override
            public int compare(TimeNode o1, TimeNode o2) {

                return o1.getTime()- o2.getTime();
            }
        });

        System.out.println("test:");
        list.stream().forEach(System.out::print);

        for(TimeNode node:list){



        }

    }

    // 人物相关性分析
    void test8(){

        Scanner scanner = new Scanner(System.in);

                String str2 =scanner.nextLine();
        int k = Integer.valueOf(str2);
                String str = scanner.nextLine();

        // 法1直接使用find方法记录位置

        // regex

        String regexAlice = "Alice";
        String regexBob = "Bob";

        Pattern pattern = Pattern.compile(regexAlice);
        Pattern pattern1 = Pattern.compile(regexBob);

        Matcher matcher = pattern.matcher(str);
        Matcher matcher1 = pattern1.matcher(str);


        List<Integer> listAlice = new ArrayList<>();
        List<Integer> listBob = new ArrayList<>();


        while(matcher.find()){
            listAlice.add(matcher.start()); //开始index

//            System.out.print(matcher.start());
//            System.out.print(matcher.group()+"->");
        }

        while(matcher1.find()){
            listBob.add(matcher1.start()); //开始index
//            System.out.print(matcher1.group()+"->");
        }

        int count=0;
        for(int i =0;i<listAlice.size();i++){

            // 寻找邻近是否有Bob
            int index= listAlice.get(i);

            for(int j = 0;j<listBob.size();j++){
                if(Math.abs(listBob.get(j)-index)<=k){
                    count++;
                }
            }

        }

        System.out.println(count);

    }

    // 后缀表达式
    // 大数问题？？ 看测评数据
    void test9(){
        int n,m;
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();

        List<Integer> numbers = new ArrayList<>();

        for(int i = 0;i<n+m+1;i++){
            numbers.add(scan.nextInt());
        }

        numbers.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });

//        System.out.println("测试");
//        numbers.stream().forEach((e)->{
//            System.out.print(e+"->");
//        });

        int sum  = 0;
        for(int i = 0 ;i<numbers.size();i++){
            if(i<=n){
                sum+=numbers.get(i);
            }else {
                sum-=numbers.get(i);
            }
        }

        System.out.println(sum);//"res:"+

    }


    public static void main(String[]args){
        TestB testB = new TestB();
        testB.test9();
    }

}