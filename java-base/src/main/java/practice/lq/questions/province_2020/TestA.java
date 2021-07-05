package practice.lq.questions.province_2020;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/3
 * @VERSION 1.0
 * @DESC
 * 一、目录
 *
 * 二、问题记录
 *
 * 1.map 的遍历使用计数（lambda引用外部的变量—）
 * 2.精度的使用
 * 3.时间类的使用（Date,Calendar,DF)
 *
 *
 * 待解决：
 * 最后两道编程题+
 * 七段码
 * 字符排序！
 * 几何划分
 *
 * 三、基础知识
 *
 * 1.同一个类的函数之间可以互调数据
 *
 *
 *
 */
public class TestA {

    // 门牌制作（求1-2020，含有多少2） 5分  == 遍历枚举
    void test(){
        int count =0;
        for(int i =1;i<=2020;i++)
        {
            String str = i+"";
            for(int j = 0;j<str.length();j++){
                if(str.charAt(j)=='2')
                    count++;
            }

        }
        System.out.println(count);

    }// 624

    // 既定公约数，5分  == 数学公约数问题
    // 分子分母最大公约数是1则满足，遍历所有组合，满足的则+1
    // 需要注意颠倒分子分母会不会算一种情况

    boolean judge(int a,int b){

        if(a==1||b==1){
            return true;
        }

        int min = a>b?b:a;
        for(int i =min;i>1;i--){
            if(a%i==0&&b%i==0){ // 在被除数大于1的情况满足都整除则不符合
                return false;
            }
        }

        return true;
    }
    void test2(){

        int count =0;
        for(int i = 1;i<=2020;i++){ //枚举
            for(int j = 1;j<=2020;j++){
                    if(judge(i,j))
                        count++;
            }
        }
        System.out.println(count);
    }//2481215

    // 蛇形填数 10 分 == 矩阵问题（数组）
    // 思路：寻找i,j变化规则，填入的数据为 i
    // 寻找20 行，二十列 需要填写 39 行
    // 终止条件为row+1 == 20 && col+1 ==20

    /*

    1 2 6 7
    3 5 8
    4 9
    10

    (0,0) - > (0,1) -> (1,0) -> (2,0)

    从2开始，， i = 0, j = 1; j--，i++只到j等于0则只i++;然后反向j++,i--只到i等于0则只j++然后反向到第一步
    => 判断i是否等于0，若是则j++,然后while(j>0) j--,i++; 再然后 i++,while(i>0) i--,j++;

    //1426  10分
     */
    void test3(){

        int num = 1,row=0,col=0;

        int[][] test = new int[50][50];
        test[row][col]=num;

        while(true){

//            if(row==0){
//                col++;
//                num++;
                test[0][++col]=++num;
//            }

            while(col>0)
            {
                col--;
                row++;
                num++;
                test[row][col]=num;
                if(col==19&&row==19){
                    System.out.println("res:"+num);
                    for (int i =0;i<50;i++)
                    {
                        for(int j=0;j<50;j++)
                        {
                            System.out.print(test[i][j]+" ");
                        }
                        System.out.println();
                    }
                    return;
                }
            }

            test[++row][0] =++num;

            while(row>0)
            {
                row--;
                col++;
                num++;
                test[row][col]=num;
                if(col==19&&row==19){
                    System.out.println("res:"+num);
                    for (int i =0;i<50;i++)
                    {
                        for(int j=0;j<50;j++)
                        {
                            System.out.print(test[i][j]+" ");
                        }
                        System.out.println();
                    }
                    return;
                }
            }
        }
     //   System.out.println("TEST:");
    }




    // 七段码
    // 思路：枚举 1-7的情况排除重合的部分
    // 试用编程方法：测试连通性！
    void test4(){

    }
    // 平面分割
    // 思路：
    // 先考虑直线分割，第n条线需要和第n-1条线都相交，才能保证分割最大，因此 n项与n-1项的关系为 a(n-1) +n
    // 1条：2  2条：4 ，3条：7
    // 解决了直线，再考虑圆，一个圆保证与所有相交则*2即可

    // 递归

    int lineVal(int n){
        if(n==1){
            return 2;
        }else {
            return lineVal(n-1)+n;
        }
    }

    //221249536   15分
    void test5(){

        int val = lineVal(20);
        for(int i =1;i<=20;i++){
            val*=2;
        }
        System.out.println("res:"+val);
//        Math.m Math有幂乘法吗

    }

    // 成绩分析
    // 统计最高分和最低分，以及平均分四舍五入保留两位小数！！，关键在于平均的精准度考虑
    // 15分
    void test6(){

        int n;
        List<Integer> grade = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        int i =0;
        while(i<n){
            grade.add(scanner.nextInt());
            i++;
        }

        Collections.sort(grade);

        System.out.println("max:"+grade.get(0)+";min:"+grade.get(grade.size()-1));

        // stream求平均？？
        IntSummaryStatistics iss = new IntSummaryStatistics();

        iss =grade.stream().mapToInt(Integer::intValue).summaryStatistics();

        System.out.println("average:"+iss.getAverage()+";保留两位：");

        double res = iss.getAverage();

        BigDecimal bd = new BigDecimal(res);
        double res1 = bd.setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue();

        // 使用df
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println(bd.floatValue()+";"+df.format(res)+";"+res1);

    }

    // 回文日期
    // 日期遍历+是关键，然后转换成字符串请进行一个回文判断即可
    // 分值：20分

    // 简单回文
    boolean j1(String date){

       for(int i =0;i<date.length()/2;i++){
           if(date.charAt(i)!=date.charAt(date.length()-i-1)){
               return false;
           }
       }

        return true;
    }

    // ABABBABA型 (满足简单的回文的情况下再只需要慢前面四个满足 ,charAt(0) ==
    boolean j2(String date){

        if(date.charAt(0)==date.charAt(2)&&date.charAt(1)==date.charAt(3)){
            return true;
        }
        return false;
    }


    // 采用clendar

    void test7() throws ParseException {
        // 日期从输入的日期开始一天天递增？？
        String inp = new String();

        Scanner scan = new Scanner(System.in);

        inp = scan.nextLine();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // MM才是月
        Date date=sdf.parse(inp);

        System.out.println("inp:"+date);
//        String str = sdf.format(date);
//        long val = date.getTime(); //time值秒？？
        // 年月日
        Calendar calendar = Calendar.getInstance();
        calendar.set(date.getYear()+1900,date.getMonth(),date.getDate());

        System.out.println("input:"+calendar.getTime());

        String temp = new String();

        temp = inp;

        boolean flag = true;

        int i =0;
        while(true){

            if(flag&&j1(temp)){
                System.out.println(temp);
                flag=false;
            }

            if(j1(temp)&&j2(temp)){
                System.out.println(temp);
                break;
            }

            calendar.add(Calendar.DATE,1);//增加一天
            //temp  = (calendar.getTime().getYear()+1900)+""+(calendar.getTime().getMonth()+1)+""+calendar.getTime().getDate()+"";
            // 转换
            temp = sdf.format(calendar.getTime());
//            System.out.println("temp:"+temp);


//
//            i++;

        }

    }

    void test7_() throws ParseException {

        Calendar cc =null;

        String str = "20201202";
        Date d = new Date();
        DateFormat df = new SimpleDateFormat("yyyymmdd");

        d = df.parse(str);
        cc.setTime(d);


    }

    // 子串分值
    // 思路：f()求值，进行判断val ，然后设置区间遍历求和
    // 20分
    int f(String str){
        // 使用map空间词频，然后遍历map发现val等于1的则加一

        Map<Character,Integer> counter = new HashMap<>();

        for(int i = 0;i<str.length();i++) {

            counter.compute(str.charAt(i),(k,v)->{
                if(v==null){
                    v=1;
                }else {
                    v++;
                }
                return v;
            });
        }

        AtomicInteger sum = new AtomicInteger();
        counter.forEach((k,v)->{
            if(v==1){
                sum.getAndIncrement(); //
            }
        });
        return sum.get();
    }


    void test8(){

        String inp = new String();
        Scanner scan = new Scanner(System.in);
        inp = scan.nextLine();//输入的字符串

        int sum = 0;

        for(int i = 0;i<inp.length();i++){
            for(int j =i;j<inp.length();j++){ // 切分，
                String temp = new String();

                temp = inp.substring(i,j+1); //subString(0,0) =>  ""
                sum+=f(temp);
                System.out.println("temp="+temp+";res="+sum);
            }
        }

        System.out.println("res:"+sum);

    }

    // 荒岛探测
    // 几何题 求面积，涉及精度
    // 25分
    void test9(){

    }

    // 字符排序
    // 25分
    // 描述: 输入冒泡排序的次数，找出最短的那个
    // 思考？
    // 暴力:遍历的起点与终点？
    // aaa*（只需要交换0次） 开始，，a*ba* （交换a的个数次）
    // cba (n*(n-1)/2)

    // 字母数量
    void test10(){



    }

    public static void main(String []args) throws ParseException {
        TestA gapFill = new TestA();
        System.out.println("rest:"+gapFill.f("a"));
        System.out.println("rest:"+gapFill.f("ab"));
        System.out.println("rest:"+gapFill.f("aba"));

        gapFill.test3();
    }


}

//
//
//    int num = 1,row=0,col=0;
//
//    int[][] test = new int[50][50];
//        test[row][col]=num;
//
//                while(true){
//
////            if(row==0){
////                col++;
////                num++;
//                test[0][++col]=++num;
////            }
//
//                while(col>0)
//                {
//                col--;
//                row++;
//                num++;
//                test[row][col]=num;
//                if(col==19&&row==19){
//                System.out.println("res:"+num);
//                for (int i =0;i<50;i++)
//        {
//        for(int j=0;j<50;j++)
//        {
//        System.out.print(test[i][j]+" ");
//        }
//        System.out.println();
//        }
//        return;
//        }
//        }
//
//        test[++row][0] =++num;
//
//        while(row>0)
//        {
//        row--;
//        col++;
//        num++;
//        test[row][col]=num;
//        if(col==19&&row==19){
//        System.out.println("res:"+num);
//        for (int i =0;i<50;i++)
//        {
//        for(int j=0;j<50;j++)
//        {
//        System.out.print(test[i][j]+" ");
//        }
//        System.out.println();
//        }
//        return;
//        }
//        }
//
//
//        }
//
////   System.out.println("TEST:");
//
